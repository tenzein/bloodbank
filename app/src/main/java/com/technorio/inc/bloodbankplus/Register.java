package com.technorio.inc.bloodbankplus;

import android.app.ProgressDialog;
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
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.technorio.inc.bloodbankplus.app.AppConfig;
import com.technorio.inc.bloodbankplus.app.AppController;
import com.technorio.inc.bloodbankplus.helper.GeneralUtil;
import com.technorio.inc.bloodbankplus.helper.SQLiteHandler;
import com.technorio.inc.bloodbankplus.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bijznas.notify.crouton.Crouton;
import bijznas.notify.crouton.Style;

public class Register extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText edtfirstName, edtlastName, edtUsername, edtPassword, edtVpassword, edtNumber;
    private static final String TAG = Register.class.getSimpleName();
    private Button btnRegister;
    public DatePicker datePicker;
    private Spinner sexSpinner;
    private Spinner bloodSpinner;
    private EditText edtStreet, edtDis,edtEmail;

    private Spinner yearSpinner, monthSpinner, daySpinner;

    public String userName;
    //    progress dialog
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private String dob,s,blood;
    private String y,m,d;

    AQuery aq;
    String firstName;
    String numbers;
    String code;
    String msdcal;
    String imei;
 String fName,lName,username,password,vPassword,street, district,phone,email;
    public static String BASE_URL = "https://www.cognalys.com/api/v2/request_missed_call/";
    public static String BASE_URL2 ="https://www.cognalys.com/api/v2/confirm_verification/";
    public static String ONE = "MISSING CREDENTIALS";
    public static String TWO = "MISSING REQUIRED VALUES";
    public static String THREE = "MISSING PROPER NUMBER";
    public static String FOUR = "VERIFICATION SUCCESS";
    public static String FIVE="NUMBER IS NOT CORRECT";
    public static String SIX="MOBLIE NUMBER VERIFICATION CANCELED";
    public static String SEVEN="NETWORK ERROR CANNOT BE VERIFIED";
    public static String EIGHT="MOBLIE NUMBER VERIFICATION FAILED, NO INTERNET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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

        edtfirstName = (EditText) findViewById(R.id.fName);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("A+");
        categories.add("A-");
        categories.add("B+");
        categories.add("B-");
        categories.add("AB+");
        categories.add("AB-");
        categories.add("O+");
        categories.add("O-");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        bloodSpinner.setAdapter(dataAdapter);

        //        spinner gender
        // Spinner Drop down elements
        List<String> gender = new ArrayList<String>();
        gender.add("Select");
        gender.add("Male");
        gender.add("Female");
        gender.add("Other");

        ArrayAdapter<String> sexAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, gender);
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexSpinner.setAdapter(sexAdapter);


        // attaching data adapter to spinner
        sexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        sexSpinner.setSelection(position);

                String sex = sexSpinner.getSelectedItem().toString();
                s = sex;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Crouton.makeText(Register.this, "Please Select Gender", Style.ALERT).show();
            }
        });

        bloodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 1) {
                    String bloodGroup = (String) bloodSpinner.getSelectedItem().toString();
                    blood = bloodGroup;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Crouton.makeText(Register.this,"Please Select Blood Group",Style.ALERT).show();
            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String year = (String)yearSpinner.getSelectedItem().toString();
                y=year;
                Log.d(TAG, "spinner value onItemSelected--->"+y);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Crouton.makeText(Register.this,"Please Select Year",Style.ALERT).show();
            }
        });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String month =(String) monthSpinner.getSelectedItem().toString();
                m=month;
                Log.d(TAG, "spinner value onItemSelected--->"+m);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Crouton.makeText(Register.this,"Please Select Month",Style.ALERT).show();
            }
        });

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String day = (String)daySpinner.getSelectedItem().toString();
                d=day;
                Log.d(TAG, "spinner value onItemSelected--->"+d);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Crouton.makeText(Register.this,"Please Select Day",Style.ALERT).show();
            }
        });


        Log.d(TAG, "spinner value onItemSelected--->"+dob);
//        DOB spinner
        List<String> year = new ArrayList<String>();
        year.add("Year");
        year.add("2000");
        year.add("1999");
        year.add("1998");
        year.add("1997");
        year.add("1996");
        year.add("1995");
        year.add("1994");
        year.add("1993");
        year.add("1992");
        year.add("1991");
        year.add("1990");
        year.add("1989");
        year.add("1987");
        year.add("1986");
        year.add("1985");
        year.add("1984");
        year.add("1983");
        year.add("1982");
        year.add("1981");
        year.add("1980");
        year.add("1979");
        year.add("1978");
        year.add("1977");
        year.add("1976");
        year.add("1975");
        year.add("1974");
        year.add("1973");
        year.add("1972");
        year.add("1971");
        year.add("1970");


        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, year);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        List<String> month = new ArrayList<String>();
        month.add("Month");
        month.add("1");
        month.add("2");
        month.add("3");
        month.add("4");
        month.add("5");
        month.add("6");
        month.add("7");
        month.add("8");
        month.add("9");
        month.add("10");
        month.add("11");
        month.add("12");

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, month);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        List<String> day = new ArrayList<String>();
        day.add("Day");
        day.add("31");
        day.add("30");
        day.add("29");
        day.add("28");
        day.add("27");
        day.add("26");
        day.add("25");
        day.add("24");
        day.add("23");
        day.add("22");
        day.add("21");
        day.add("20");
        day.add("19");
        day.add("18");
        day.add("17");
        day.add("16");
        day.add("15");
        day.add("14");
        day.add("13");
        day.add("12");
        day.add("11");
        day.add("10");
        day.add("9");
        day.add("8");
        day.add("7");
        day.add("6");
        day.add("5");
        day.add("4");
        day.add("3");
        day.add("2");
        day.add("1");

        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, day);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

//        List<String> code = new ArrayList<String>();
//        code.add("Code");
//        code.add("+977");
//        code.add("+971");
//
//        ArrayAdapter<String> codeAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item, code);
//        codeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        countryCode.setAdapter(codeAdapter);
//

//        Progress Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

//        Session Manager
        session = new SessionManager(getApplicationContext());

//        SQlite database handler
        db = new SQLiteHandler(getApplicationContext());

//   Register Button click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fName = edtfirstName.getText().toString().trim();
                 lName = edtlastName.getText().toString().trim();
                 username = edtUsername.getText().toString().trim();
                 password = edtPassword.getText().toString().trim();
                vPassword = edtVpassword.getText().toString().trim();
                street = edtStreet.getText().toString().trim();
                district = edtDis.getText().toString().trim();
                phone = edtNumber.getText().toString().trim();
                email = edtEmail.getText().toString().trim();

               dob = y+"/"+ m+"/" + d;

                if (!fName.isEmpty() && !lName.isEmpty() && !username.isEmpty()
                        && !password.isEmpty() && !vPassword.isEmpty() && !street.isEmpty()
                        && !district.isEmpty() && !dob.isEmpty() && !blood.isEmpty() &&!s.isEmpty()
                        && !phone.isEmpty() && !email.isEmpty()){
                    if(!password.equals(vPassword)) {
                        Crouton.makeText(Register.this, "Password did not match", Style.ALERT).show();
                    }else if(!isValidEmail(email))
                    {
                        Crouton.makeText(Register.this, "Invalid Email", Style.ALERT).show();

                    }else if(!ValidPhone(phone)) {
                        Crouton.makeText(Register.this, "Invalid Phone Number", Style.ALERT).show();

                    }else
                    {
//                        mobile verification

                        if(isOnline(Register.this))
                        {
                            registerUser(fName, lName, username, password, vPassword, dob, blood, s, phone, email, street, district);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"No Connection",Toast.LENGTH_LONG).show();
                        }
                      }
                } else {
                    Crouton.makeText(Register.this,"Please enter the details", Style.ALERT).show();
                }

            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

//    calling the cognalys api
    private void verify() {
        imei= GeneralUtil.getImei(Register.this);

//        number=  "+977"+ edtNumber.getText().toString().trim();

        String e = BASE_URL + "?app_id=" + "ab4b70dbc02849dd999e70b"
                + "&access_token=" +"39bd8ce75157fc63ad744b8ee85e4f95d0d0c364"+ "&mobile="
                + "+977" + phone + "&imei=" +GeneralUtil.getImei(Register.this)+ "&mcc=" + GeneralUtil.getMCC(Register.this) + "&mnc="
                + "null" + "&lat=" + GeneralUtil.getLat(Register.this) + "&brand_name=" + GeneralUtil.getDeviceName()
                + "&os_version=" +GeneralUtil.getOSVersion() + "&model_number=" + GeneralUtil.getDeviceModelNumber()
                + "&lon=" + GeneralUtil.getLon(Register.this) + "&gmail_id=" + GeneralUtil.getEmail(Register.this);
        System.out.println(e);


        aq = new AQuery(this);
        aq.ajax(e, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(object);
                parseInfo(object);
            }
        });

//


    }
    private ArrayList<JSONInfo> parseInfo(JSONObject object)
    {
        ArrayList<JSONInfo> list=new ArrayList<>();
        try {

            JSONInfo info=new JSONInfo();


            info.mobile=object.getString("mobile");
            info.status=object.getString("status");
            info.codes =object.getString("codes");
            info.cipher=object.getString("cipher");


            list.add(info);
            System.out.println("HEllo there" + info.cipher);

            numbers=info.cipher;
            System.out.println(numbers);

            msdcal=numbers.substring(12, 23);
            System.out.println(msdcal);

            code = msdcal.substring(6);

            System.out.println("verification code:" +code);

//            storing the response from the cognalys
            storeVerification(numbers,code,blood);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void storeVerification(final String miscal, final String code,final String bld) {

//        Tag used to canccel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering.....");
        showDialog();


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_VERIFY, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Verification Response:" + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");
                        System.out.println(uid);
                        System.out.println(firstName);


                        System.out.println("blood group"+bld);

                        // Launch code verifying activity
                        Bundle bundle = new Bundle();
                        bundle.putString("key", firstName);
                        bundle.putString("code", code);
                        bundle.putString("miscall",msdcal);
                        Intent intent = new Intent(Register.this, Verify.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Verification Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("cipher", miscal);
                params.put("code",code);
                params.put("blood", bld);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }


    //    validating email id
private boolean isValidEmail(String email)
{
    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}

//    validating phone number
private boolean ValidPhone(String phone)
{

    Pattern patterns = Pattern.compile("^(98|97)[0-9]{8}$");
    Matcher matchers = patterns.matcher(phone);
    return matchers.matches();
}

// Function to store user in MySQL database will post parmas  to register url

    private void registerUser(final String fName, final String lName, final String username, final String
            password, final String vpassword, final String dob, final String blood,final String gen,final String phone,
            final String email, final String street, final String dis) {

//        Tag used to canccel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering.....");
        showDialog();


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response:" + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");
                        System.out.println("I am Fine");
                        JSONObject user = jObj.getJSONObject("user");
//                        String uid = user.getString("uid");
                        String fName = user.getString("name");
                        String username = user.getString("username");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                        db.addUser(fName, username, uid, created_at);

                       Toast.makeText(Register.this,"You have Successfully registered with the username :" + fName,Toast.LENGTH_LONG).show();

                        // Launch miscal activity
                       firstName = fName;
                        verify();
//
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", fName);
                params.put("lname",lName);
                params.put("username", username);
                params.put("password", password);
                params.put("vpassword", vpassword);
                params.put("dob",dob);
                params.put("phone",phone);
                params.put("gender",gen);
                params.put("email",email);
                params.put("blood",blood);
                params.put("street", street);
                params.put("district", dis);
//                params.put("cipher",cip);
//                params.put("code", cod);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    //initializing the object
    public void initializing()
    {
        edtfirstName = (EditText) findViewById(R.id.fName);
        edtlastName =(EditText) findViewById(R.id.lName);
         edtUsername =(EditText) findViewById(R.id.edtUsername);
        edtPassword =(EditText) findViewById(R.id.edtPassword);
        edtVpassword =(EditText) findViewById(R.id.edtVpassword);
        edtNumber = (EditText) findViewById(R.id.edtPhone);
        edtStreet = (EditText) findViewById(R.id.edtStreet);
        edtDis =(EditText) findViewById(R.id.edtDis);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        sexSpinner = (Spinner) findViewById(R.id.sexSpinner);
        bloodSpinner =(Spinner) findViewById(R.id.bloodSpinner);
        yearSpinner =(Spinner) findViewById(R.id.year);
        monthSpinner =(Spinner) findViewById(R.id.month);
        daySpinner =(Spinner) findViewById(R.id.day);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        startActivity(new Intent(Register.this, com.technorio.inc.bloodbankplus.Menu.class));
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
                startActivity(new Intent(Register.this, com.technorio.inc.bloodbankplus.Menu.class));
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

        if (id == R.id.register) {
            // Handle the register action
        } else if (id == R.id.emergencyNumber) {
                startActivity(new Intent(Register.this,Emergency.class));
            finish();

        }
        else if (id == R.id.AboutUs) {
            startActivity(new Intent(Register.this, About.class));
            finish();
        }
//        else if (id == R.id.supportUs) {
//            startActivity(new Intent(Register.this, SupportUs.class));
//            finish();
//        }
        else if (id == R.id.team) {
            startActivity(new Intent(Register.this, Team.class));
            finish();
        }
        else if (id == R.id.supporter) {
            startActivity(new Intent(Register.this, Supporters.class));
            finish();
        }
        else if (id == R.id.howitworks) {
            startActivity(new Intent(Register.this, Working.class));
            finish();
        }
        else if (id == R.id.disclaimer) {
            startActivity(new Intent(Register.this, Disclaimer.class));
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
