package com.technorio.inc.bloodbankplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class About extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        about = (TextView) findViewById(R.id.abt);
//        String htmlText = " %s ";
//        String myData = "Bloodbank+ " +
//                "" +
//                "Imagine a situation when someone close to you is in the ICU. After an hour, the doctor rushes outside and tells you that you need to get AB -ve blood as early as possible, or else anything may happen. You rush to the nearest blood bank, call your relatives, your friends, but no one has it or if they do, are unwilling to help because they are afraid. You are confused, stressed, and afraid of losing someone so close to your heart. You then post in Social Media, but all in vain. " +
//                "Blood Bank+ is a platform that connects eligible and willing blood donors to people who need blood. With just few steps, you are connected with the perfect person who has and wants to donate the blood." +
//                "Each and every one of you can be the part of the solution. All you have to do is register your account and be a Hero. Registering your account can be done via our website, Mobile App or SMS. We currently are in our first phase. In first phase, we\\'ll be collecting data of donors and validating it. Because we\\'re still working with our platform, we won\\'t however be able to fulfill the blood request via our website (but you can always let us know if you need blood and we\\'ll connect you to the probable donors). By the first or second week of January, 2016, we'll launch the fully functioning system. But for that, we need the records of Blood Donors.\\n\n" +
//                "We thus want to ask you all to join this revolution." +
//                "Your donation can make a difference of life or death to someone. Every time you donate blood, you are saving up to 3 lives. So stop making excuses and start donating blood." ;
//                about.loadData(String.format(htmlText, myData), "text/html", "utf-8");
//


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        startActivity(new Intent(About.this, com.technorio.inc.bloodbankplus.Menu.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.register_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.home:
                startActivity(new Intent(About.this, Menu.class));
                finish();
                break;
            default:
                break;
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.emergencyNumber) {
            startActivity(new Intent(About.this,Emergency.class));
            finish();

        }
//        else if (id == R.id.supportUs) {
//            startActivity(new Intent(About.this, SupportUs.class));
//            finish();
//        }
        else if (id == R.id.supporter) {
            startActivity(new Intent(About.this, Supporters.class));
            finish();
        }

        else if (id == R.id.howitworks) {
            startActivity(new Intent(About.this, Working.class));
            finish();
        }
        else if (id == R.id.disclaimer) {
            startActivity(new Intent(About.this, Disclaimer.class));
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
