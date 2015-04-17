package org.covenantcollege.safetyandsecurityapp;

import java.io.File;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.Toast;

/**
 * Created by mbabu on 4/16/15.
 */
public class Parking extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_regulations_layout);

        Button button = (Button) findViewById(R.id.butt1);
        button.setOnClickListener(new OnClickListener() {

         /**
         * Opens the a PDF document on locally install viewer
         * @param v
         */
        public void onClick(View v)
        {
            /**
             *
             */
            File pdf_file = new File(Environment.getExternalStorageDirectory(), "Parking Regulations.pdf");
            try
            {
                 if(pdf_file.exists())
                 {
                    Uri path = Uri.fromFile(pdf_file);

                    Intent objIntent = new Intent(Intent.ACTION_VIEW);

                    objIntent.setDataAndType(path, "application/pdf");

                    objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(objIntent);

                    } else
                    {
                        Toast.makeText(Parking.this, "File not found",
                        Toast.LENGTH_SHORT).show();
                    }

                } catch (ActivityNotFoundException e)
                {
                    Toast.makeText(Parking.this, "No Viewer Application Found", Toast.LENGTH_SHORT).show();

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
