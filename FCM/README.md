##This repository is used for sending the notifications to other users using FCM

you need to add these dependencies for Firebase cloud messaging (FCM)

implementation("com.android.volley:volley:1.2.1")
implementation("com.google.firebase:firebase-messaging:23.2.1")

How to use.

<User ID> = auth().getCurrentUser().getUid();


 new FcmNotificationsSender("/topics/" + <User ID>, "Title Text From App",
                "Description Text From App", getApplicationContext(), this).SendNotifications();


// Before calling the above code you also need to add this code in you activity but this code needs to run only one time

FirebaseMessaging.getInstance().subscribeToTopic(<User ID>);

You also need the server key you can get the server key from firebase 

Your Project > Project Settings > Cloud Messaging > Cloud Messaging API (Legacy)