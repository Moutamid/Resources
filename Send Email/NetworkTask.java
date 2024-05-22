package com.moutamid.sampleproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class NetworkTask extends AsyncTask<List<String>, Void, Boolean> {
    private Activity mContext;

    public NetworkTask(Activity context) {
        mContext = context;
    }

    @Override
    protected Boolean doInBackground(List<String>... imageUriLists) {
        try {
            final String username = "SENDER_EMAIL";
            final String password = "SENDER_EMAIL_PASSWORD";
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            return new javax.mail.PasswordAuthentication(username, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("SENDER_EMAIL"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("RECEIVER_EMAIL"));
            message.setSubject("SUBJECT_NAME");

            String messageBody = "<p>Hello!</p>";
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(messageBody, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            Log.e("SendEmailTask", "Error sending email", e);
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            // Email sent successfully, show success toast
            Toast.makeText(mContext, "Email sent successfully!", Toast.LENGTH_SHORT).show();
            mContext.finish();
        } else {
            // Error sending email, show error toast
            Toast.makeText(mContext, "Something went wrong, Please try again later", Toast.LENGTH_SHORT).show();
        }
        // You can also finish the activity here if needed
        // SendActivity.activity.finish();
    }
}
