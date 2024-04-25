package com.moutamid.secretservice.receivers;

import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.Feature;
import com.google.android.gms.tasks.Tasks;

import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MissedCallReceiver extends BroadcastReceiver {
    String TAG = "MyPhoneStateListener";
    static MyPhoneStateListener listener;

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        this.context = context;
// for incoming calls

	 String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
	 if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Log.d(TAG, "onReceive: numberrr  " + number);
                if (number != null) {
                   // Do what ever you need to do with this number
                }
            }

// For missed call or end call
        if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            Log.d(TAG, "onReceive CALL");
            if(listener == null){
                listener = new MyPhoneStateListener(context);
            }
            TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            telephony.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }
}