package org.covenantcollege.safetyandsecurityapp;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by obedtandadjaja on 4/23/15.
 */
public class EmergencyInfo extends Fragment {

    Button select_button;
    ImageView image;
    TextView error;
    Switch s;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.emergency_info, container, false);

        image = (ImageView) rootView.findViewById(R.id.imageView);
        error = (TextView) rootView.findViewById(R.id.textView1);

        s = (Switch) rootView.findViewById(R.id.switch2);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    image.setVisibility(View.INVISIBLE);
                    select_button.setVisibility(View.INVISIBLE);
                    error.setVisibility(View.VISIBLE);
                }
                else
                {
                    image.setVisibility(View.VISIBLE);
                    select_button.setVisibility(View.VISIBLE);
                    error.setVisibility(View.INVISIBLE);
                }
            }
        });

        select_button = (Button)rootView.findViewById(R.id.button);
        select_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(v);
                getActivity().openContextMenu(v);
                unregisterForContextMenu(v);
            }
        });

        return rootView;
    }

    /**
     * Creates the context menu bar
     * @param menu menu to be created
     * @param v View
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Andreas Basement");
        menu.add(0, v.getId(), 0, "Ashe Barnes First Floor");
        menu.add(0, v.getId(), 0, "Brock Hall Basement");
        menu.add(0, v.getId(), 0, "Carter Basement");
        menu.add(0, v.getId(), 0, "Chapel Subground Level");
        menu.add(0, v.getId(), 0, "Founders Belz Ground Floor");
        menu.add(0, v.getId(), 0, "Guest Cottages");
        menu.add(0, v.getId(), 0, "Jackson Hall");
        menu.add(0, v.getId(), 0, "Library First Floor");
        menu.add(0, v.getId(), 0, "Mac Basement");
        menu.add(0, v.getId(), 0, "Mills First Floor");
        menu.add(0, v.getId(), 0, "Probasco First Floor");
        menu.add(0, v.getId(), 0, "Sanderson First Floor");
        menu.add(0, v.getId(), 0, "Student Apartments");

    }

    /**
     * @param item picture item under dialect
     * @return true if an item is selected
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        select_button.setText(item.getTitle());
        if(item.getTitle().equals("Andreas Basement"))
            image.setImageResource(R.drawable.andreas_basement);
        else if(item.getTitle().equals("Ashe Barnes First Floor"))
            image.setImageResource(R.drawable.ashe_barnes_first_floor);
        else if(item.getTitle().equals("Brock Hall Basement"))
            image.setImageResource(R.drawable.brock_hall_basement);
        else if(item.getTitle().equals("Carter Basement"))
            image.setImageResource(R.drawable.carter_basement);
        else if(item.getTitle().equals("Chapel Subground Level"))
            image.setImageResource(R.drawable.chapel_subground_level);
        else if(item.getTitle().equals("Founders Belz Ground Floor"))
            image.setImageResource(R.drawable.founders_belz_ground_floor);
        else if(item.getTitle().equals("Guest Cottages"))
            image.setImageResource(R.drawable.guest_cottages);
        else if(item.getTitle().equals("Jackson Hall"))
            image.setImageResource(R.drawable.jackson_hall);
        else if(item.getTitle().equals("Library First Floor"))
            image.setImageResource(R.drawable.library_first_floor);
        else if(item.getTitle().equals("Mac Basement"))
            image.setImageResource(R.drawable.mac_basement);
        else if(item.getTitle().equals("Mills First Floor"))
            image.setImageResource(R.drawable.mills_first_floor);
        else if(item.getTitle().equals("Probasco First Floor"))
            image.setImageResource(R.drawable.probasco_first_floor);
        else if(item.getTitle().equals("Sanderson First Floor"))
            image.setImageResource(R.drawable.sanderson_first_floor);
        else if(item.getTitle().equals("Student Apartments"))
            image.setImageResource(R.drawable.student_apartments);
        return true;
    }

=======
import android.app.Fragment;

/**
 * Created by mbabu on 4/23/15.
 */
public class EmergencyInfo extends Fragment {
>>>>>>> origin/master
}
