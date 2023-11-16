package com.hui.tally.clockpackage;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.hui.tally.R;

import java.util.Calendar;

public class clockmain extends AppCompatActivity {


    private ImageView clockBton;
    private TimePicker timePicker;
    private Button setAlarmButton;
    private Button stopAlarmButton;
    private PendingIntent pendingIntent;
    private AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockmain);

        //找到鬧鐘圖片控建
        //clockBton = findViewById(R.id.clock_icon);

        alarmReceiver = new AlarmReceiver();
        timePicker = findViewById(R.id.timePicker);
        setAlarmButton = findViewById(R.id.setAlarmButton);
        stopAlarmButton = findViewById(R.id.stopAlarmButton);

        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });

        stopAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAlarm();
            }
        });
        //命令跳轉鬧鐘頁面
        /*clockBton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clockIntent = new Intent(clockmain.this, MainActivityM1.class);
                startActivity(clockIntent);
            }
        });*/
    }

    private void setAlarm() {
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(clockmain.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(clockmain.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        Toast.makeText(clockmain.this, "鬧鈴設置成功", Toast.LENGTH_SHORT).show();
    }

    private void stopAlarm() {
        // 取消闹钟
        Intent intent = new Intent(clockmain.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(clockmain.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        // 停止铃声
        AlarmReceiver.stopAlarmSound();

        Toast.makeText(clockmain.this, "鬧鐘已停止", Toast.LENGTH_SHORT).show();
    }



}

