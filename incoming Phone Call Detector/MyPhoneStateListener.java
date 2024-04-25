package com.moutamid.secretservice.services;

import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.fxn.stash.Stash;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyPhoneStateListener extends PhoneStateListener {
    private Context context;
    String TAG = "MyPhoneStateListener";
    int i = 0;
    int lastState = TelephonyManager.CALL_STATE_IDLE;
    boolean isIncoming;

    public MyPhoneStateListener(Context context) {
        this.context = context;
    }


    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        if (lastState == state) {
            //No change
            return;
        }
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                isIncoming = true;
                // incoming call started
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                //Transition of ringing->offhook are pickups of incoming calls.  Nothing down on them
                if (lastState != TelephonyManager.CALL_STATE_RINGING) {
                    isIncoming = false;
                    //outgoing call started
                }
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                //End of call(Idle).  The type depends on the previous state(s)
                if (lastState == TelephonyManager.CALL_STATE_RINGING) {
                    //toast here for missed call

                } else if (isIncoming) {
          
                    //incoming call ended
                    
                } else {
                    //outgoing call ended
                }
                break;
        }
        lastState = state;
    }
}
