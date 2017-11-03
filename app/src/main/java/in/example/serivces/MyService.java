package in.example.serivces;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MyService extends Service {
    final String TAG = "Response";

    public MyService() {
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        notification();
        Toast.makeText(getApplicationContext(),"serivce starts" ,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void nofity(String message){
        NotificationCompat.Builder b = new NotificationCompat.Builder(getApplicationContext());
        b.setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setTicker("{your tiny message}")
                .setContentTitle("notification")
                .setContentText(message)
                .setContentInfo("INFO");

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, b.build());
    }

    public void notification(){


        String tag_json_obj = "json_obj_req";

        String url = "http://192.168.1.105/notification/index.json";
/*
        final ProgressDialog pDialog = new ProgressDialog(getA);
        pDialog.setMessage("Loading...");
        pDialog.show();*/

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());


                        try {
                            response.get("status");
                            if ((response.get("status").toString()).equals("1")){
                                nofity(response.get("message").toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                //pDialog.hide();
            }
        });

// Adding request to request queue
        Appcontroller.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
