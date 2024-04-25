package com.moutamid.secretservice.receivers;

import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MessageSender {

    // TODO you need to add this permission in manifest : <uses-permission android:name="android.permission.SEND_SMS" />
    public static void sendAutoMessage(String phoneNumber, String message) {
        Log.d(TAG, "inside sendAutoMessage");
        try {
            String SENT = "SMS_SENT";
            PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, new Intent(SENT), PendingIntent.FLAG_IMMUTABLE);

            SmsManager sms = SmsManager.getDefault();

            ArrayList<String> parts = sms.divideMessage(message);

            ArrayList<PendingIntent> sendList = new ArrayList<>();
            sendList.add(sentPI);

            ArrayList<PendingIntent> deliverList = new ArrayList<>();
            deliverList.add(sentPI);

            sms.sendMultipartTextMessage(phoneNumber, null, parts, sendList, deliverList);

            Log.d(TAG, "SMS sent successfully");
        } catch (ActivityNotFoundException ae) {
            ae.printStackTrace();
            Log.d(TAG, "ActivityNotFoundException \t " + ae.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Missed Calll E \t " + e.getMessage());
        }
    }
}