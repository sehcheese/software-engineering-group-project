package org.covenantcollege.safetyandsecurityapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.telephony.SmsManager;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Keith on 23-Mar-15.
 */
public class shuttle_Fragment extends Fragment {
    View rootview;

    String shuttleNumber = "1234567890";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.shuttle_layout, container, false);

        //set up the buttons
        Button callButton = (Button) rootview.findViewById(R.id.callShuttleButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callShuttle(rootview);
            }
        });
        Button textButton = (Button) rootview.findViewById(R.id.textShuttleButton);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textShuttle(rootview);
            }
        });
        return rootview;
    }

    //calls the shuttle
    public void callShuttle(View view) {
        Toast.makeText(getActivity(), "Calling the Security Shuttle", Toast.LENGTH_SHORT).show();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + shuttleNumber));
        startActivity(callIntent);
    }

    public void textShuttle(View view) {
        EditText nameField = (EditText)view.findViewById(R.id.shuttleRiderName);
        Spinner fromLocation = (Spinner)view.findViewById(R.id.shuttlePickUpLocationSpinner);
        Spinner toLocation = (Spinner)view.findViewById(R.id.shuttleDestinationSpinner);
        String message = "Hi im " + nameField.getText() + " and I'm feeling lazy. Come pick me up at " + fromLocation.getSelectedItem().toString() +
                " and take me alllll the way to " + toLocation.getSelectedItem().toString() + ". Oh and make it snappy. Ain't nobody got time for waitin'! Hurry!";

        System.out.println(message);


        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts = sms.divideMessage(message); //we have to do this in case it is longer than a single message
        sms.sendMultipartTextMessage(shuttleNumber, null, parts, null, null);

        Toast.makeText(getActivity(), "Messaged Security Shuttle", Toast.LENGTH_LONG).show();
    }
}
