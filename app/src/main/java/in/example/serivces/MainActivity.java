package in.example.serivces;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private  PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());


        Intent intent = new Intent(MainActivity.this, MyReceiver.class);
        pendingIntent  = PendingIntent.getBroadcast(getApplicationContext(),0,intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(MainActivity.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 1000 * 120, pendingIntent);

    }
}
