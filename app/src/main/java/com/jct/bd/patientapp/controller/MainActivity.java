package com.jct.bd.patientapp.controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jct.bd.patientapp.R;
import com.jct.bd.patientapp.controller.fragments.AboutUsFragment;
import com.jct.bd.patientapp.controller.fragments.PrivacyPolicyFragment;
import com.jct.bd.patientapp.model.backend.FactoryBackend;
import com.jct.bd.patientapp.model.backend.IBackend;
import com.jct.bd.patientapp.model.entities.Patient;
import com.jct.bd.patientapp.model.entities.Type;


public class MainActivity extends AppCompatActivity {
    private Type module;
    private Patient patient;
    DrawerLayout dl;
    ActionBarDrawerToggle t;
    NavigationView nv;
    private IBackend backend = FactoryBackend.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        //get the patient from the extra information
        Intent intent = getIntent();
        String emailOfThePatient = intent.getStringExtra("patient");// need to add this when the activity is being called
        dl = (DrawerLayout) findViewById(R.id.activityMain);
        t = new ActionBarDrawerToggle(this,dl,R.string.open,R.string.close){
            public void onDrawerClosed(View view){
                getSupportActionBar().setTitle(R.string.open);
                supportInvalidateOptionsMenu();
            }
            public void onDrawerOpened(View view){
                getSupportActionBar().setTitle(R.string.close);
                supportInvalidateOptionsMenu();
            }
        };
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.AboutAs:
                        loadFragment(new AboutUsFragment());
                        break;
                    case R.id.privacyPolicy:
                        loadFragment(new PrivacyPolicyFragment());
                        break;
                    case R.id.signOut:
                        Toast.makeText(getApplicationContext(), R.string.goodbye,Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        finish();
                        break;
                }
                dl.closeDrawer(GravityCompat.START);
                getSupportActionBar().setTitle(R.string.close);
                supportInvalidateOptionsMenu();
                dl.addDrawerListener(t);
                return true;
            }
        });
        patient = backend.getPatient(emailOfThePatient);
        module = patient.getModule();
            Intent m1 = new Intent(this, Module1.class);
            m1.putExtra("module",module.toString());
            m1.putExtra("id",patient.getId());
            m1.putExtra("email",patient.getEmail());
            startActivity(m1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
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
}
