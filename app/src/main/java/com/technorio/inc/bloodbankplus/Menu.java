package com.technorio.inc.bloodbankplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Menu extends AppCompatActivity  implements View.OnClickListener{

    int size = 5;
    ViewPager myPager = null;
    int count = 0;
    Timer timer;
    private Button btnRegister, btnEmergency, btnAbout, btnMessage;
    static TextView mDotsText[];
    private int mDotsCount;
    private LinearLayout mDotsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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

        initializing();
//        btnSupport.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnEmergency.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnMessage.setOnClickListener(this);

//        image slide with timer
        ViewPagerAdapter adapter = new ViewPagerAdapter(Menu.this, size);
        myPager = (ViewPager) findViewById(R.id.reviewpager);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);

        // Timer for auto sliding
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= 5) {
                            myPager.setCurrentItem(count);
                            count++;
                        } else {
                            count = 0;
                            myPager.setCurrentItem(count);
                        }
                    }
                });
            }
        }, 500, 7000);
    }

    //    initialzing the button of the class
    public void initializing()
    {
        btnRegister =(Button ) findViewById(R.id.btnRegister);
        btnEmergency =(Button) findViewById(R.id.btnEmergency);
        btnAbout =(Button) findViewById(R.id.btnAbout);
        btnMessage =(Button) findViewById(R.id.btnMessage);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnRegister:
                startActivity(new Intent(Menu.this, Register.class));
                finish();
                break;
            case R.id.btnEmergency:
                startActivity(new Intent(Menu.this,Emergency.class));
                finish();
                break;
            case R.id.btnAbout:
                startActivity(new Intent(Menu.this,About.class));
                finish();
                break;
            case R.id.btnMessage:
                startActivity(new Intent(Menu.this,Message.class));
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.info:

                startActivity(new Intent(Menu.this,HowitWorks.class));
                finish();

                  break;
            // action with ID action_settings was selected
            case R.id.dis:
               startActivity(new Intent(Menu.this, Disclaimer.class));
                finish();
                break;
            default:
                break;
        }

        return true;
    }
}
