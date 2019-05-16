package com.jct.bd.patientapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jct.bd.patientapp.R;
import com.jct.bd.patientapp.controller.fragments.AboutUsFragment;
import com.jct.bd.patientapp.controller.fragments.Module1;
import com.jct.bd.patientapp.controller.fragments.Module2;
import com.jct.bd.patientapp.controller.fragments.Module3;
import com.jct.bd.patientapp.controller.fragments.PrivacyPolicyFragment;
import com.jct.bd.patientapp.model.entities.Patient;
import com.jct.bd.patientapp.model.entities.Type;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Type module;
    private Fragment myFragment;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get the patient from the extra information
        Intent intent = getIntent();
        patient = (Patient)intent.getSerializableExtra("patient");// need to add this when the activity is being called
        module = patient.getModule();

        //Load the compatible fragment
        if(module == Type.BEGINNER)
            myFragment = new Module1();
        else if(module == Type.ADVANCED)
            myFragment = new Module2();
        else if(module == Type.FINISHED)
            myFragment = new Module3();
        loadFragment(myFragment);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_aboutUs) {
            AboutUsFragment abousUsFragment = new AboutUsFragment();
            loadFragment(abousUsFragment);
        } else if (id == R.id.nav_logout) {
            logout();
        } else if (id == R.id.nav_privacyPolicy) {
            PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void logout(){

    }
}
