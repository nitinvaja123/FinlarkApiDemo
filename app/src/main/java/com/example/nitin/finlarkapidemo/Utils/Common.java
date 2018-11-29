package com.example.nitin.finlarkapidemo.Utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Common {

    public static void InternetError(Context context) {
        Toast.makeText(context, "Please Check Your Internet Connections...!", Toast.LENGTH_SHORT).show();
    }

    public static boolean isInternetAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected() && netInfo.isAvailable())
            return true;

        return false;
    }
}
