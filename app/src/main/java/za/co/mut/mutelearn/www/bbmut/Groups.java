package za.co.mut.mutelearn.www.bbmut;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Groups extends Fragment {

    //declaration
    RelativeLayout bottom;
    ImageButton btnsent;
    EditText inputName;
    FloatingActionButton fab;
    private AVLoadingIndicatorView progressBar;

    //we will use these constants later to pass the artist name and id to another activity
    public static final String ARTIST_NAME = "net.simplifiedcoding.firebasedatabaseexample.groupname";
    public static final String POST_ID = "net.simplifiedcoding.firebasedatabaseexample.groupid";

    //a list to store all the artist from firebase database
    List<pojo_groups> groups;

    ListView listgroups;

    //our database reference object
    DatabaseReference databaseGroups;

    public Groups() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_groups, container, false);
        databaseGroups = FirebaseDatabase.getInstance().getReference("Groups");

        //links
        bottom=(RelativeLayout)rootView.findViewById(R.id.relativeLayoutGroupBottom);
        btnsent=(ImageButton) rootView.findViewById(R.id.btnAddGroup);
        inputName=(EditText) rootView.findViewById(R.id.edtInputGroupName);
        listgroups = (ListView)rootView.findViewById(R.id.list_groups);
        progressBar=(AVLoadingIndicatorView)rootView.findViewById(R.id.loading_bar_groups);

        groups = new ArrayList<>();


        //clicks
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab_add_group);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottom.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);
            }
        });

        btnsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPost();
                bottom.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
            }
        });


        return rootView;
    }

    //convert arraylist into two normal arrays
    public String[] arry(){

        String[] list=new String[20];
        for (int i=0;i<20;i++){
            list[i]="mini 1";

        }
        return list;
    }


    private void addPost() {
        //getting the values to save
        String Name = inputName.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(Name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseGroups.push().getKey();

            //creating an Artist Object
            pojo_groups artist = new pojo_groups(Name);

            //Saving the Artist
            databaseGroups.child(id).setValue(artist);

            //setting edittext to blank again
            inputName.setText("");

            //displaying a success toast
            Toast.makeText(getActivity().getApplicationContext(), "Artist added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(getActivity().getApplicationContext(), "Please provide the group name", Toast.LENGTH_LONG).show();
            bottom.setVisibility(View.VISIBLE);
            fab.setVisibility(View.GONE);
        }
    }




    @Override
    public void onStart() {
        super.onStart();
        progressBar.setVisibility(View.GONE);
        //attaching value event listener
        databaseGroups.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                groups.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    pojo_groups group  = postSnapshot.getValue(pojo_groups.class);
                    //adding artist to the list
                    groups.add(group);
                }

                //creating adapter
                groupList groupAdapter = new groupList(Groups.this.getActivity(), groups);
                //attaching adapter to the listview

                listgroups.setAdapter(groupAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
