package in.example.serivces;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class MyReceiver extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, MyService.class);
        context.startService(i);

    }

    public void enter() {
}

    public void noenter() {
        String tag_json_obj = "json_obj_req";

        String url = "http://localhost/notification/index.php";


        HashMap<String, String> params = new HashMap<String, String>();


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        try {
                            if((response.get("status").toString()).equals("0")) {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Response", "Error: " + error.getMessage());
                // hide the progress dialog
            }

        });

    }


}
