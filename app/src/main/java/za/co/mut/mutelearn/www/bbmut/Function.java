package za.co.mut.mutelearn.www.bbmut;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Function extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            Courses coursesFrag = new Courses();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            coursesFrag.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, coursesFrag).commit();
        }

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.function, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_courses) {

            changeFragment("courses");

        } else if (id == R.id.nav_announcements) {
            changeFragment("announcement");

        } else if (id == R.id.nav_assignments) {
            changeFragment("assignment");

        } else if (id == R.id.nav_help) {
            changeFragment("help");

        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBodyText = "Check it out Mangosuthu University of Technology Blackboard";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));

        } else if (id == R.id.nav_logout) {

        }else if(id==R.id.nav_groups){
            changeFragment("groups");
        }
        else if(id==R.id.nav_material){
            changeFragment("study");
        }
        else if(id==R.id.nav_settings){
            Intent i=new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //changing fragments
    public void changeFragment(String fragname){
        // Create fragment and give it an argument specifying the article it should show
        if (fragname.equals("groups")){
            Groups groupsFrag = new Groups();
            Bundle args = new Bundle();
            groupsFrag.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, groupsFrag);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();

        }else if (fragname.equals("announcement")){

            Announcement announcementFrag = new Announcement();
            Bundle args = new Bundle();
            announcementFrag.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, announcementFrag);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();

        }else if (fragname.equals("help")){

            Help helpFrag = new Help();
            Bundle args = new Bundle();
            helpFrag.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, helpFrag);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();

        }else if (fragname.equals("assignment")){

            Assignment assignmentFrag = new Assignment();
            Bundle args = new Bundle();
            assignmentFrag.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, assignmentFrag);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();

        }else if (fragname.equals("study")){

            StudyMaterial studyFrag = new StudyMaterial();
            Bundle args = new Bundle();
            studyFrag.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, studyFrag);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();

        }else if (fragname.equals("courses")){
            Courses coursesFrag = new Courses();
            Bundle args = new Bundle();
            coursesFrag.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, coursesFrag);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();
        }else{

        }


    }
}
