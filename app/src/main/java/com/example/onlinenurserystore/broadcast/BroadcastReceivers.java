package com.example.onlinenurserystore.broadcast;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.onlinenurserystore.R;

public class BroadcastReceivers extends android.content.BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;
    boolean noConnectivity;


    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManagerCompat= NotificationManagerCompat.from(context);
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY,false
            );
            if (noConnectivity){
                Notification notification = new NotificationCompat.Builder(context,CreateChannel.CHANNEL_1)
                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                        .setContentTitle("Disconnect")
                        .setContentText("No Connectivity found")
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                notificationManagerCompat.notify(11,notification);
            }else {

                Notification notification = new NotificationCompat.Builder(context,CreateChannel.CHANNEL_1)
                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                        .setContentTitle("Connected")
                        .setContentText("Connection Successfully Established")
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                notificationManagerCompat.notify(22,notification);
            }
        }
    }
}


