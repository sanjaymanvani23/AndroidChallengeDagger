package com.mobile.androidchallengedagger.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class NetworkChangeReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        try
        {
            if (Util.isOnline(context)) {
              //  Toast.makeText(context, "Internet connected", Toast.LENGTH_SHORT).show();
                Intent mintent = new Intent("network");
                // You can also include some extra data.

                LocalBroadcastManager.getInstance(context).sendBroadcast(mintent);
            } else {
                Toast.makeText(context, "Internet lost", Toast.LENGTH_SHORT).show();

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


}