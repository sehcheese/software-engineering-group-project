package org.covenantcollege.safetyandsecurityapp;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class controls the tip submission page and has a listener for the button
 */
public class tipSubmission_Fragment extends Fragment {
    View rootview;
    Button mButton;
    EditText mEdit;
    String tip;
    private static String ConfirmationText = "Thank you for submitting a tip";
    private static String ProblemText = "Error, please try submitting your tip again";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.tip_submission_layout, container, false);


        //grabs the submit button so I can create an onClick listener for it
        mButton = (Button)rootview.findViewById(R.id.tip_button);

        //grabs the edit text box that holds the text the user enters
        mEdit = (EditText) rootview.findViewById(R.id.tip_editText);


        //mEdit.setBackgroundResource(R.layout.tip_background);


        //this click listener reacts when the submit button is clicked on the tip_submission_layout page
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tip = mEdit.getText().toString();
                Log.v("tip text", tip);  //this prints info to the log console

                String[] sub = new String[1];
                sub[0] = tip;

                //create and run new task, which will post the tip text to a url
                tipSubmittedTask task = new tipSubmittedTask();
                task.execute(sub);
            }
        });

        return rootview;
    }


    //this is more or less from https://github.com/codepath/android_guides/wiki/Creating-and-Executing-Async-Tasks
    //types specified here are the input data type, the progress type, and the result type
    private class tipSubmittedTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... str) {
            // do POST here
            if (str.length != 1) {
                return "Error in tip text submission";
            }

            try {
                // Setup URL and parameters
                String urlString = "https://selfsolve.apple.com/wcResults.do";  //test url


                String info = str[0];  //info is the tip text, passed in thru the execute method

                Log.d("params",info);  //these are interspersed debuggers that print to logcat

                // Setup and open HTTP connection
                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("charset", "utf-8");
                con.setRequestProperty("Content-Length", "" + Integer.toString(info.getBytes().length));
                con.setUseCaches(false);

                // Write parameters, flush and close connection
                DataOutputStream wr = new DataOutputStream(con.getOutputStream ());
                wr.writeBytes(info);
                wr.flush();
                wr.close();

                // Read results
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String urlResponse = in.readLine();
                in.close();
                con.disconnect(); // We have all we need from this HTTP request, close it

                Log.d("update3", "here");
                if (urlResponse != null) {
                    return urlResponse;
                } else {
                    Log.d("URL response error", "error!");
                    return "Server Error";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Error";
            } catch (IOException e) {
                e.printStackTrace();
                return "Error. Are you connected to the Internet?";
            }

            //return urlResponse;
        }

        protected void onPostExecute(String result) {
            // This method is executed in the UIThread
            // with access to the result of the long running task (tipSubmittedTask)
            Log.v("post", result+" finished");

            EditText mEdit = (EditText) rootview.findViewById(R.id.tip_editText);
            mEdit.setText("");

            Context context = getActivity();
            String text;
            if (result.contains("Error")) {
                text = ProblemText;
            }
            else {
                text = ConfirmationText;
            }
            Toast alert = Toast.makeText(context,text,Toast.LENGTH_SHORT);  //this shows a small alert on the page to confirm submission
            alert.show();
            Log.v("after commit", "after commit?");

        }

    }
}
