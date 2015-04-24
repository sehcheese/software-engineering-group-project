package org.covenantcollege.safetyandsecurityapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.net.URL;

/**
 *
 */
public class parking_Fragment extends Fragment {
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.parking_regulations_layout, container, false);

        Button button = (Button) rootview.findViewById(R.id.butt1);
        button.setOnClickListener(new View.OnClickListener() {

            /**
             * Opens the a PDF document on locally install viewer
             * @param v
             */
            public void onClick(View v)
            {
                /**
                 *
                 */
                //File pdf_file = new File("regulations.pdf"); // filepath
                try
                {
                        Uri path = Uri.parse("android.resource://org.covenantcollege.safetyandsecurityapp/raw/regulations");

                        Intent objIntent = new Intent(Intent.ACTION_VIEW);

                        objIntent.setDataAndType(path, "application/pdf");

                        objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(objIntent); // start the pdf viewer

                } catch (ActivityNotFoundException e)
                {
                    Toast.makeText(getActivity(), "No Viewer Application Found", Toast.LENGTH_SHORT).show();

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        return rootview;
    }
}
