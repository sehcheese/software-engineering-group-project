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
import android.widget.NumberPicker;
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

    String shuttleNumber = "4234002654";

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

        //set our min/max values for the number picker
        NumberPicker riders = (NumberPicker)rootview.findViewById(R.id.shuttleNumberOfRiders);
        riders.setMaxValue(8);
        riders.setMinValue(1);
        riders.setValue(1);

        Spinner pickUp = (Spinner)rootview.findViewById(R.id.shuttlePickUpLocationSpinner);
        pickUp.setSelection(5);
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
        NumberPicker riders = (NumberPicker)view.findViewById(R.id.shuttleNumberOfRiders);
        String message = "Hi I'm " + nameField.getText();
        if(riders.getValue() > 1)
        {
            message += ". " + (riders.getValue() - 1) + " people and I";
        }
        else
        {
            message += " and I";
        }
        message += " would like a ride from " + fromLocation.getSelectedItem().toString() +
                " to " + toLocation.getSelectedItem().toString() + ". Thanks!";

        System.out.println(message);


        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts = sms.divideMessage(message); //we have to do this in case it is longer than a single message
        sms.sendMultipartTextMessage(shuttleNumber, null, parts, null, null);

        Toast.makeText(getActivity(), "Messaged Security Shuttle", Toast.LENGTH_LONG).show();
    }
}
