package com.mobile.androidchallengedagger.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class Util {

    public static String getDateFormat(String date){
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date newDate = null;
        String newTime = "";
        try {
            newDate = format2.parse(date);
           newTime= format.format(newDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return newTime.toString();
    }

    public static boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

}