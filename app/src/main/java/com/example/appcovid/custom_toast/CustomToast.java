package com.example.appcovid.custom_toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.appcovid.R;

public  class CustomToast extends Toast {
    public static int ERROR = 3;

    public static long SHORT = 4000;
    public static long LENGTH_LONG = 7000;

    public CustomToast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, String message, int duration, int type, boolean androidicon) {
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        View layout = LayoutInflater.from(context).inflate(R.layout.activity_patient, null, false);
        TextView l1 = (TextView) layout.findViewById(R.id.toast_text);

//        ConstraintLayout linearLayout = (ConstraintLayout) layout.findViewById(R.id.toast_type);

        if (type == 3) {
            l1.setBackgroundResource(R.drawable.error_shape);
        }
        toast.setView(layout);
        return toast;
    }
}
