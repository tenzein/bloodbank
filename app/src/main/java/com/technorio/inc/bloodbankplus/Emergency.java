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
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Emergency extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();

                switch (childPosition)
                {
                    case 0:

                        break;
                }

                return false;
            }
        });



    }



    /*
                * Preparing the list data
                */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Emergency");
        listDataHeader.add("Police");
        listDataHeader.add("Ambulance");
        listDataHeader.add("Bloodbank");
        listDataHeader.add("Hospital");

        // Adding child data
        List<String> emergency = new ArrayList<String>();
        emergency.add("Police Control: 100");
        emergency.add("Fire Brigade: 101");
        emergency.add("Traffic Control Room: 103");

        List<String> police = new ArrayList<String>();
        police.add("Police Headquarter Exchange, Naxal: +97714411210 / +97714410088");
        police.add("Metropolitan Police Range (Bhaktapur): +97716614821");
        police.add("Metropolitan Police Range (Lalitpur): +97715521207");
        police.add("Metropolitan Police Range (Kathmandu): +97714261945 / +97714261790");
        police.add("Police Emergency Number: +97714228435 / +97714226853");

        List<String> ambulance = new ArrayList<String>();
        ambulance.add("Paropakar Ambulance Service: +97714260859");
        ambulance.add("Lalitpur Redcross Ambulance Service: +977115545666");
        ambulance.add("Bishal Bazar Ambulance Service: +977114244121");
        ambulance.add("Redcross Ambulance Service: +977114228094");
        ambulance.add("Medicare National Hospital - Ambulance: +977114467067");
        ambulance.add("Nepal Red Cross Society: +977114228094");
        ambulance.add("Ambulance Paropakar: +977114251614");
        ambulance.add("Nepal Ambulance Service: 102");

        List<String> hostpital = new ArrayList<String>();
        hostpital.add("Vayodha Hospital: +97714281666");
        hostpital.add("Nepal Eye Bank: +97714493684");
        hostpital.add("Nepal Eye Hospital: +97714250691");
        hostpital.add("Tilganga Eye Hospital: +97714423684");
        hostpital.add("Bir Hospital: +97714223807");
        hostpital.add("Nepal Police Hospital: +97714412430");
        hostpital.add("TU Teaching Hospital: +97714412404");
        hostpital.add("Maternity Hospital: +97714253276");
        hostpital.add("Teku Hospital: +97714253396");
        hostpital.add("Patan Hospital: +97715522278");
        hostpital.add("Bhaktapur Hospital: +97716610676");
        hostpital.add("Mental Hospital: +97715521333");
        hostpital.add("Kanti Children Hospital: +97714414798 / +97714427452");
        hostpital.add("Kathmandu Model Hospital: +97714240805");
        hostpital.add("B&B Hospital: +97715533206");
        hostpital.add("Medicare National Hospital: +97714467067");
        hostpital.add("Nepal Orthopaedic Hospital: +97714493725");
        hostpital.add("Kathmandu Medical College (Sinamangal): +97714476152");
        hostpital.add("Nepal Medical College (Jorpati): +97714486008");
        hostpital.add("Kantipur Dental Hospital, Maharajgunj :+97714371603");
        hostpital.add("Kantipur Hospital, New Baneshwor: +97714498757");
        hostpital.add("Hospital and Research Centre : +97714476225");
        hostpital.add("Norvic Hospital: +97714258554");
        hostpital.add("Martyr Gangalal National Heart Centre: +97714371322");
        hostpital.add("Life Care Hospital: +97714227735");
        hostpital.add("Miteri Hospital: +97714280555");
        hostpital.add("Capital Hospital: +97714244022");
        hostpital.add("Shree Satya Sai Centre: +97714498035 ");
        hostpital.add("National Kidney Centre: +97716612266");
        hostpital.add("");
        hostpital.add("");
        hostpital.add("");
        hostpital.add("");
        hostpital.add("");

        List<String> bloodbank = new ArrayList<String>();
        bloodbank.add("Blood Bank: +977114225344");


        listDataChild.put(listDataHeader.get(0), emergency); // Header, Child data
        listDataChild.put(listDataHeader.get(1), police);
        listDataChild.put(listDataHeader.get(2), ambulance);
        listDataChild.put(listDataHeader.get(3), bloodbank);
        listDataChild.put(listDataHeader.get(4), hostpital);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        startActivity(new Intent(Emergency.this, com.technorio.inc.bloodbankplus.Menu.class));
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
                startActivity(new Intent(Emergency.this, Menu.class));
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

        }
       else if (id == R.id.AboutUs) {
        startActivity(new Intent(Emergency.this, About.class));
        finish();
    }
//        else if (id == R.id.supportUs) {
//            startActivity(new Intent(Emergency.this, SupportUs.class));
//            finish();
//        }

        else if (id == R.id.supporter) {
            startActivity(new Intent(Emergency.this, Supporters.class));
            finish();
        }

        else if (id == R.id.howitworks) {
            startActivity(new Intent(Emergency.this, Working.class));
            finish();
        }
        else if (id == R.id.disclaimer) {
            startActivity(new Intent(Emergency.this, Disclaimer.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
