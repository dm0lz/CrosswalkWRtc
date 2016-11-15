package com.example.olivier.crosswalkwrtc;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.widget.Toast;

public class TurnOnWifiTask extends AsyncTask<Void,Void,Void> {
    private Context context;
    private boolean was_turned_on;

    public TurnOnWifiTask(Context context){
        this.context=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        was_turned_on = enableWifi(context);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(was_turned_on){
            Toast.makeText(context, "Wifi has been activated.", Toast.LENGTH_LONG).show();
        } else{
            //Toast.makeText(context, "Wifi is activated.", Toast.LENGTH_LONG).show();
        }
    }

    public static boolean enableWifi(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        boolean action_required = false;
        if(!wifi.isWifiEnabled()) {
            wifi.setWifiEnabled(true);
            action_required = true;
        }
        return action_required;
    }
}