package org.covenantcollege.safetyandsecurityapp;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * this class controls the tip submission page and has a listener for the button
 */
public class tipSubmission_Fragment extends Fragment {
    View rootview;
    Button mButton;
    EditText mEdit;
    String tip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.tip_submission_layout, container, false);

        mButton = (Button)rootview.findViewById(R.id.tip_button);
        mEdit = (EditText) rootview.findViewById(R.id.tip_editText);


        //this click listener reacts when the submit button is clicked on tip_submission_layout
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                tip = mEdit.getText().toString();
                Log.v("tip text", tip);  //this prints info to the log console so you can see it, it also has to be directly from mEdit for some reason

                String[] sub = new String[1];
                sub[0] = tip;

                tipSubmittedTask task = new tipSubmittedTask();
                task.execute(sub);

                //perhaps open a confirmation page here? "thanks for submitting, etc."

            }
        });

        return rootview;

    }


    //this is more or less from https://github.com/codepath/android_guides/wiki/Creating-and-Executing-Async-Tasks
    //types specified here are the input data type, the progress type, and the result type
    private class tipSubmittedTask extends AsyncTask<String, Void, String> {
        protected void onPreExecute() {
            //runs on UI thread before doINBackground
            //Good for toggling visibility of a progress indicator

        }

        protected String doInBackground(String... str) {
            // do POST here
            if (str.length != 1) {
                return "Error in tip text submission";
            }

            try {
                // Setup URL and parameters
                String urlString = "https://selfsolve.apple.com/wcResults.do";  //test url


                String urlParameters = str[0];
                //String urlParameters = tip;

                Log.d("params",urlParameters);  //these are interspersed debuggers that print to logcat
                // Setup and open HTTP connection
                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true); //I added this, it was not in original
                con.setRequestMethod("POST");
                con.setRequestProperty("charset", "utf-8");
                con.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
                con.setUseCaches(false);

                Log.d("update", "i am here");
                // Write parameters, flush and close connection
                DataOutputStream wr = new DataOutputStream(con.getOutputStream ());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                Log.d("update2", "now i am here");

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
                Log.d("malform", "malform error");
                e.printStackTrace();
                return "Error";
            } catch (IOException e) {
                Log.d("io", "io exception");
                e.printStackTrace();
                return "Error. Are you connected to the Internet?";
            }

            //return urlResponse;
        }

        protected void onPostExecute(String result) {
            // This method is executed in the UIThread
            // with access to the result of the long running task
            Log.d("post", result+" finished");
        }

    }
}
