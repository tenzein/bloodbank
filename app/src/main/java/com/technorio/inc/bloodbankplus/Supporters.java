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
import android.widget.ImageView;
import android.widget.TextView;

public class Supporters extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtPart;
    private ImageView image1,image2,image3,image4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txtPart =(TextView) findViewById(R.id.txtPartners);
        image1 =(ImageView) findViewById(R.id.technorio);
        image2 =(ImageView) findViewById(R.id.mega);
        image3 =(ImageView) findViewById(R.id.sparrow);
        image4 =(ImageView) findViewById(R.id.leadership);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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
        startActivity(new Intent(Supporters.this,Menu.class));
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
                startActivity(new Intent(Supporters.this, Menu.class));
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
            startActivity(new Intent(Supporters.this, Emergency.class));
            finish();
        }
        else if (id == R.id.AboutUs) {
            startActivity(new Intent(Supporters.this, About.class));
            finish();
        }
//        else if (id == R.id.supportUs) {
//            startActivity(new Intent(Supporters.this, SupportUs.class));
//            finish();
//        }

        else if (id == R.id.howitworks) {
            startActivity(new Intent(Supporters.this, Working.class));
            finish();
        }
        else if (id == R.id.disclaimer) {
            startActivity(new Intent(Supporters.this, Disclaimer.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
