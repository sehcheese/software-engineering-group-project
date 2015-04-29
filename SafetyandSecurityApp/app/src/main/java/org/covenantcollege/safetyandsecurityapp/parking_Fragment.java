package org.covenantcollege.safetyandsecurityapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;

/**
 *
 */
public class parking_Fragment extends Fragment {
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.parking_regulations_layout, container, false);

        // Query for parking notifications on startup
        new queryParkingNotifications().execute();

        // Query on refresh
        Button refreshButton = (Button) rootview.findViewById(R.id.parking_notifications_refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new queryParkingNotifications().execute();
            }
        });

        Button button = (Button) rootview.findViewById(R.id.parking_regulations);
        button.setOnClickListener(new View.OnClickListener() {

            /**
             * Opens the a PDF document
             * @param v
             */
            public void onClick(View v)
            {
                Uri uri = Uri.parse("http://www.covenant.edu/pdf/safety/Parking_Regulations.pdf");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                /**
                 *
                 *//*
                //File pdf_file = new File("regulations.pdf"); // filepath
                try
                {
                        Uri path = Uri.parse("android.resource://org.covenantcollege.safetyandsecurityapp/raw/regulations");

                        Intent objIntent = new Intent(Intent.ACTION_VIEW);

                        objIntent.setData(path);

                        objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(objIntent); // start the pdf viewer

                } catch (ActivityNotFoundException e)
                {
                    Toast.makeText(getActivity(), "No Viewer Application Found", Toast.LENGTH_SHORT).show();

                } catch (Exception e)
                {
                    e.printStackTrace();
                }*/
            }
        });

        return rootview;
    }

    // TODO THIS NEEDS TO BE GENERICIZED SO THAT YOU CAN CALL AN HTTP POST FROM ANYWHERE AND NOT COPY AND PASTE CODE LIKE I DID
    // sorry it's one in the morning and I need to present something tomorrow.
    private class queryParkingNotifications extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... str) {
            try {
                String urlString = "http://kepler.covenant.edu/~safesec/queryParkingNotifications.php";

                // Setup and open HTTP connection
                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("charset", "utf-8");
                con.setUseCaches(false);

                // Read results
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String urlResponse = in.readLine();
                in.close();
                con.disconnect(); // We have all we need from this HTTP request, close it

                if (urlResponse != null) {
                    return urlResponse;
                } else {
                    return "Server Error";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Error";
            } catch (IOException e) {
                e.printStackTrace();
                return "Error. Are you connected to the Internet?";
            }
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject serverResponse = new JSONObject(result);
                JSONArray parkingNotificationsJSONArray = serverResponse.getJSONArray("notifications");

                String[] parkingNotifications = new String[parkingNotificationsJSONArray.length()];
                for(int i = 0; i < parkingNotificationsJSONArray.length(); i++) {
                    parkingNotifications[i] = parkingNotificationsJSONArray.getString(i);
                    System.out.println(parkingNotificationsJSONArray.getString(i));
                }

                ListView parkingNotificationsList = (ListView) rootview.findViewById(R.id.parking_notifications);
                parkingNotificationsList.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, parkingNotifications));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
