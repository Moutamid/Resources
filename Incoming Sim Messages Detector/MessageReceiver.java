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
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        this.context = context;
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED") || intent.getAction().equals("com.google.android.gms.rcs.RECEIVE_RCS_MESSAGE")) {
            Log.d(TAG, "onReceive SMS");
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        String sender = smsMessage.getDisplayOriginatingAddress();
                        String notifMessage = smsMessage.getMessageBody();
                        
			

                    }
                }
            }
        }
    }
}