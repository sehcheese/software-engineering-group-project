package org.covenantcollege.safetyandsecurityapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


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
        tip = mEdit.getText().toString();

        //this click listener reacts when the submit button is clicked on tip_submission_layout
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v("title", mEdit.getText().toString());

            }
        });

        return rootview;

    }
}
