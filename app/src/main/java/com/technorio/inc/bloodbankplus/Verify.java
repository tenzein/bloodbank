package com.technorio.inc.bloodbankplus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Verify extends AppCompatActivity {
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
    private static final String TAG = Register.class.getSimpleName();

    String status;
    String numbers;
    String code;
    String mCall;
    String imei;
    AQuery aq;
    String userid;
    Button btnVerf;
    String receivedNo,receives;
    EditText edtCod;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
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

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Bundle got = getIntent().getExtras();
        receives = got.getString("key");
        receivedNo = got.getString("code");
        mCall = got.getString("miscall");
        System.out.println(receives);
        System.out.println(mCall);
        edtCod= (EditText) findViewById(R.id.edtCode);


        System.out.println(" number received"+receivedNo);
        btnVerf = (Button) findViewById(R.id.btnSubmit);
        btnVerf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = edtCod.getText().toString().trim();
                if(!code.isEmpty() && code.equals(receivedNo) ) {
                    getNewNumber();

                }
                else if(!code.equals(receivedNo))
                {
                    Toast.makeText(Verify.this, "Verification Code didnot match. Please Try Again!", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(Verify.this, "Please Enter the correct 5 last digit of miscal number", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    private void getNewNumber()
    {


        JSONInfo info=new JSONInfo();


        System.out.println("HEllo there Are you there BRO" + receivedNo);




//        msdcal="+1"+ numbers.substring(12,23);

        //String verifynumber= msdcal.substring(18,23);
        //System.out.println("I am Fine");



        String a = BASE_URL2 + "?app_id=" + "ab4b70dbc02849dd999e70b"
                + "&access_token=" +"39bd8ce75157fc63ad744b8ee85e4f95d0d0c364"+ "&mobile="
                + "+"+mCall + "&imei=" + GeneralUtil.getImei(Verify.this)+ "&mcc=" + GeneralUtil.getMCC(Verify.this) + "&mnc="
                + "null" + "&lat=" + GeneralUtil.getLat(Verify.this) + "&brand_name=" + GeneralUtil.getDeviceName()
                + "&os_version=" +GeneralUtil.getOSVersion() + "&model_number=" + GeneralUtil.getDeviceModelNumber()
                + "&lon=" + GeneralUtil.getLon(Verify.this) + "&gmail_id=" + GeneralUtil.getEmail(Verify.this);
        System.out.println(a);

        aq = new AQuery(this);
        aq.ajax(a, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(object);
                afterParse(object);
            }
        });

    }



    private ArrayList<JSONInfo> afterParse(JSONObject object)
    {

        ArrayList<JSONInfo> list=new ArrayList<>();

        JSONInfo info= new JSONInfo();
        try {
            info.app_user_id=object.getString("app_user_id");
            info.verifiednumber=object.getString("mobile");
            info.verifiedstatus=object.getString("status");
//            Toast.makeText(Verify.this, "app_user_id="+info.app_user_id, Toast.LENGTH_SHORT).show();
            Toast.makeText(Verify.this, "Number Verified ", Toast.LENGTH_SHORT).show();
            Toast.makeText(Verify.this, "Verified Status: "+info.verifiedstatus, Toast.LENGTH_SHORT).show();
            list.add(info);
            userid=info.app_user_id;
        status = info.verifiedstatus;

            Bundle users = new Bundle();
            users.putString("username", receives);
            users.putString("status", status);
            Intent profile = new Intent(Verify.this,userProfile.class);
            profile.putExtras(users);
            startActivity(profile);
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;

    }

    private void storeVerification(final String status) {

//        Tag used to canccel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Verifying.....");
        showDialog();


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Verifying Response:" + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // Now store the user in sqlite
                        JSONObject user = jObj.getJSONObject("user");
//                        String uid = user.getString("uid");
                        String status = user.getString("status");

                        // Inserting row in users table

                        Bundle users = new Bundle();
                    users.putString("username", receives);
                    users.putString("status",status);
            Intent profile = new Intent(Verify.this,userProfile.class);
            profile.putExtras(users);
            startActivity(profile);
                    finish();

                        // Launch login activity

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
                params.put("status", status);

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

}
