package com.technorio.inc.bloodbankplus.helper;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;

import com.matesnetwork.callverification.CogPref;
import com.matesnetwork.callverification.GPSTracker;

import java.security.MessageDigest;

/**
 * Created by Tripathee on 12/6/2015.
 */
public class GeneralUtil {
    public static String getImei(Context context) {
        String identifier = null;
        TelephonyManager tm = (TelephonyManager)context.getSystemService("phone");
        if(tm != null) {
            identifier = tm.getDeviceId();
        }

        if(identifier == null || identifier.length() == 0) {
            identifier = Settings.Secure.getString(context.getContentResolver(), "android_id");
        }

        return identifier;
    }

    public static String getSHA(Context context) {
        String sign = null;

        try {
            PackageInfo e = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            Signature[] var6 = e.signatures;
            int var5 = e.signatures.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                Signature signature = var6[var4];
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                sign = Base64.encodeToString(md.digest(), 0);
                Log.e("key:", sign);
            }
        } catch (Exception var8) {
            Log.e("key:", var8.toString());
        }

        return sign;
    }

    public static String getMCC(Context context) {
        return null;
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getEmail(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account account = getAccount(accountManager);
        return account == null?null:account.name;
    }

    private static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if(accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }

        return account;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        return capitalize(manufacturer);
    }

    public static String getDeviceModelNumber() {
        String model = Build.MODEL;
        return model;
    }

    private static String capitalize(String s) {
        if(s != null && s.length() != 0) {
            char first = s.charAt(0);
            return Character.isUpperCase(first)?s:Character.toUpperCase(first) + s.substring(1);
        } else {
            return "";
        }
    }

    public static double getLat(Context context) {
        GPSTracker tracker = new GPSTracker(context);
        return tracker.getLatitude();
    }

    public static double getLon(Context context) {
        GPSTracker tracker = new GPSTracker(context);
        return tracker.getLongitude();
    }

    public static String getUserId(Context context) {
        CogPref cogPref = new CogPref(context);
        return cogPref.getString("userID");
    }

    public static void setUserId(Context context, String id) {
        CogPref cogPref = new CogPref(context);
        cogPref.putString("userID", id);
    }

    public static String getappId(Context context) {
        CogPref cogPref = new CogPref(context);
        return cogPref.getString("appID");
    }

    public static void setappId(Context context, String id) {
        CogPref cogPref = new CogPref(context);
        cogPref.putString("appID", id);
    }

    public static String getaccessTok(Context context) {
        CogPref cogPref = new CogPref(context);
        return cogPref.getString("accessTok");
    }

    public static void setaccessTok(Context context, String id) {
        CogPref cogPref = new CogPref(context);
        cogPref.putString("accessTok", id);
    }
}
