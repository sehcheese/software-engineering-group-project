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
import android.widget.Toast;

/**
 * Created by Keith on 23-Mar-15.
 */
public class shuttle_Fragment extends Fragment {
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.shuttle_layout, container, false);

        //set up the buttons
        Button callButton = (Button) rootview.findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callShuttle(v);
            }
        });
        Button textButton = (Button) rootview.findViewById(R.id.textButton);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textShuttle(v);
            }
        });
        return rootview;
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu., menu);
        return super.onCreateOptionsMenu(menu);
    }*/


    /*//This allows us to do stuff when the buttons are clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.textButton:
                System.out.println("texting shuttle");
                return true;
            case R.id.callButton:
                System.out.println("calling shuttle");
                callShuttle();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }*/

    //calls the shuttle
    public void callShuttle(View view) {
        //Log.v("Test", "[*] testPhoneCall()");
        Toast.makeText(getActivity(), "Calling?", Toast.LENGTH_SHORT).show();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:1234567890"));
        //startActivity(callIntent);
    }

    public void textShuttle(View view) {
        String number = "1234567890";//edittText1.getText().toString();
        String message = "test";//edittText2.getText().toString();
/*        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pIntent=PendingIntent.getActivity(getApplicationContext(), 0, i,0);
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(number, null, message, pIntent, null);*/
        Toast.makeText(getActivity(), "Message Sent !", Toast.LENGTH_LONG).show();
    }
}
