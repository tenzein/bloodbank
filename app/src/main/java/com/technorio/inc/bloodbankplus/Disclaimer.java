package com.technorio.inc.bloodbankplus;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

public class Disclaimer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



            final TextView txtView = (TextView)findViewById(R.id.txtTerms);

            txtView.setText(Html.fromHtml("<h2>Privacy Policy</h2> <p>Blood Bank+ respects the privacy of its users and has developed this Privacy Policy to demonstrate its commitment to protecting your privacy. These privacy policies (the “Privacy Policy”) are intended to describe for you, as an individual who is a user of mobile app and services offered by Blood Bank+, the information we collect, how that information may be used, with whom it may be shared, and your choices about such uses and disclosures.\n" +

                    "We encourage you to read this Privacy Policy carefully when using this app. By using this app, you are accepting the practices described in this Privacy Policy.\n" +
                    "If you have any questions about our privacy practices, please refer to the end of this Privacy Policy for information on How to Contact Us.</p>  " +
                    "<h1>Information We Collet About You :</h1> <p>We may collect personal information that can identify you, such as your name, email address, contact details, and other information also that does identify you but required for the app like your blood group. When you provide personal information through our app, the information may be sent to our servers.</p> " +
                    "<h1>Information you provide :</h1> <p>We may collect and store any personal information you enter on our app or provide to us in some other manner like information through your Facebook account. This includes identifying information, such as your name, address, email address, mobile and telephone numbers, blood group; we also may request information about your gender and age, and other demographic information.</p> <br>" +
                    "<h1>Information collected automatically:</h1>" +
                    "We automatically collect your current location to show nearby blood donation campaigns on map and inform you about emergency need of blood in your current location.\n" +
                    "<h1>How We Use the Information We Collect:</h1>" +
                    "We may use information that we collect about you to:<br>" +
                    "deliver the information to show in the app;<br>" +
                    "perform research and analysis about blood donors, requesters, blood donations etc;<br>\n" +
                    "communicate with you by e-mail, mobile devices;<br>\n" +
                    "to facilitate your contact details to blood requester for sending message or calling you directly;<br>\n" +
                    "enforce our terms and conditions;\n" +

                    "<h1>With Whom We Share Your Information:</h1>\n" +
                    "We want you to understand when and with whom we may share personal or other information we have collected about you or your activities on our app or while using our services. We do not share your personal information with others except as indicated below or when we inform you. We may share personal information with:\n" +
                    "<h1>Authorized service providers:</h1>We may share your personal information with other people who needs your help. However, no one are permitted to share or use such information for any other purposes.\n" +
                    "<h1>Legitimate Bodies:</h1> We also may disclose your information in response to a subpoena or similar investigative demand, a court order, or a request for cooperation from a law enforcement or other government agency; to establish or exercise our legal rights; to defend against legal claims; or as otherwise required by law. In such cases, we may raise or waive any legal objection or right available to us, in our sole discretion.\n" +
                    "When we believe disclosure is appropriate in connection with efforts to investigate, prevent, report or take other action regarding illegal activity, suspected fraud or other wrongdoing; to protect and defend the rights, property or safety of our company, our users, our employees, or others; to comply with applicable law or cooperate with law enforcement; or to enforce our app terms and conditions or other agreements or policies.\n" +
                    "Aggregated and non-personal information: We may share aggregated and non-personal information we collect under any of the above circumstances.\n" +
                    "<h1>How We Protect Your Personal Information</h1>\n" +
                    "<p>We take appropriate security measures (including physical, electronic and procedural measures) to help safeguard your personal information from unauthorized access and disclosure. For example, only authorized employees are permitted to access personal information, and they may do so only for permitted functions only. In addition, we use encryption in the transmission of your sensitive personal information between your system and ours, and we use firewalls to help prevent unauthorized persons from gaining access to your personal information.<br>\n" +
                    "We want you to feel confident using our app. However, no system can be completely secure. Therefore, although we take steps to secure your information, we do not promise, and you should not expect, that your personal information, searches, or other communications will always remain secure. Users should also take care with how they handle and disclose their personal information and should avoid sending personal information through insecure email.</p>\n" +
                    "Children’s Privacy<br>\n" +
                    "This app is for those person only who is eligible for donating blood i.e. anyone with age of 18 or above it can donate the app and use this app for tracking his/her donation records. While we do not knowingly collect personal information from children under the age of 18.\n" +
                    "<h1>Changes to This Privacy Policy</h1>\n" +
                    "We will occasionally update this Privacy Policy to reflect changes in our practices and services. When we post changes to this Privacy Policy, we will revise the \"Last Updated\" date at the top of this Privacy Policy. We recommend that you check our app from time to time to inform yourself of any changes in this Privacy Policy or any of our other policies. We might inform our app users by sending an information message in the app itself also.\n" +
                    "<h1>How to Contact Us</h1>\n" +
                    "If you have any questions about this Privacy Policy or our information-handling practices, or if you would like to request information about our disclosure of personal information to third parties, please contact us by sending an email.<br>\n" +
                    "\n" +
                    "Blood Bank+\n<br>" +
                    "support@bloodbankplus.org<br>\n" +
                    "© 2015 Blood Bank Plus,<br> \n" +
                    "All rights reserved.<br>\n" +
                    "\n" +
                    "This privacy Policy was last updated on 1st December, 2015 <br><br><br><br><br><br>"));



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
        startActivity(new Intent(Disclaimer.this,Menu.class));
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
                startActivity(new Intent(Disclaimer.this, Menu.class));
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
            startActivity(new Intent(Disclaimer.this,Emergency.class));
            finish();

        }
//        else if (id == R.id.supportUs) {
//            startActivity(new Intent(Disclaimer.this, SupportUs.class));
//            finish();
//        }
        else if (id == R.id.supporter) {
            startActivity(new Intent(Disclaimer.this, Supporters.class));
            finish();
        }
        else if (id == R.id.team) {
            startActivity(new Intent(Disclaimer.this, Team.class));
            finish();
        }
        else if (id == R.id.howitworks) {
            startActivity(new Intent(Disclaimer.this, Working.class));
            finish();
        }
        else if (id == R.id.AboutUs) {
            startActivity(new Intent(Disclaimer.this, About.class));
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }

}
