package com.example.olivier.crosswalkwrtc;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

public class EnsureConnectedTask extends AsyncTask<Void,Void,Void> {
    private Context context;

    public EnsureConnectedTask(Context context){
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try{
            while (!isWifiConnected()) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            // e
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(isWifiConnected()){
            Toast.makeText(context, "Launching XWalk...", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this.context, MainActivity.class);
            context.startActivity(i);
            ((SplashActivity)context).finish();
        } else{
            Toast.makeText(context, "This should never happen.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isWifiConnected(){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) && activeNetwork.isConnected();
    }
}