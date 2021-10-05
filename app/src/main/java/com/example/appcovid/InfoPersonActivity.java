package com.example.appcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appcovid.Notification.Utils;
import com.google.android.material.snackbar.Snackbar;

public class InfoPersonActivity extends AppCompatActivity {

    Button abc ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_emp);

//        Button btnSubmit = findViewById(R.id.submitInfoPerson);
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(InfoPersonActivity.this, HomeActivity.class));
//                finish();
//            }
//        });
//        abc = findViewById(R.id.noti);
//        abc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                headsUpNotification();
//            }
//        });
    }

    public void headsUpNotification(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Utils.CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(Utils.NOTI_TITLE)
                .setContentText(Utils.NOTI_DESC)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(Utils.NOTI_ID,builder.build());

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Utils.CHANNEL_ID)
//                .setSmallIcon(R.drawable.google)
//                .setContentTitle("My notification")
//                .setContentText("Much longer text that cannot fit one line...")
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText("Much longer text that cannot fit one line..."))
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
  }

}