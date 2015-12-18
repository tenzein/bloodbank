package com.technorio.inc.bloodbankplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class userProfile extends AppCompatActivity {

    private TextView txtMsg,txtThank;
    String message ="";
String receives;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
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

    txtMsg = (TextView )findViewById(R.id.txtWelcome);
txtThank = (TextView) findViewById(R.id.txtThank);
        Bundle got = getIntent().getExtras();
        receives = got.getString("username");
        txtThank.setText(receives + " for registering.");

        message = txtThank.getText().toString();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(userProfile.this,Menu.class));
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_howit_works, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.home:

               Intent prof = new Intent(userProfile.this,Menu.class);
                startActivity(prof);
                finish();
                break;
            default:
                break;
        }

        return true;
    }

}
