package com.hui.tally.clockpackage;



import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AlarmForegroundService extends Service {
    public static final String ACTION_START_FOREGROUND = "com.example.alarm_clock.START_FOREGROUND";
    public static final String EXTRA_NOTIFICATION = "com.example.alarm_clock.NOTIFICATION";
    private static final int NOTIFICATION_ID = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            if (ACTION_START_FOREGROUND.equals(action)) {
                Notification notification = intent.getParcelableExtra(EXTRA_NOTIFICATION);
                startForeground(NOTIFICATION_ID, notification);
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
