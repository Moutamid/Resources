Add these below classes in the Android studio code:

1. DummyNotificationActivity.java
2. activity_dummy_notification.xml
3. MyFirebaseMessagingService.java
4. AndroidManifest.xml

Now add the below dependencies in app-level gradle:

1. implementation("com.squareup.okhttp3:okhttp:5.1.0")
2. implementation("com.google.firebase:firebase-messaging")


Create a new project in vercel. 
Add the "sendNotification.js" file in path '/root/pages/api/sendNotification.js'

Make sure it's on Github.

In Firebase Console → Project Settings → Service accounts, generate a new private key. You'll get JSON file. 

Add these values as Environment Variables in your Vercel project (Project Settings → Environment Variables):

1. FIREBASE_PROJECT_ID
2. FIREBASE_CLIENT_EMAIL
3. FIREBASE_PRIVATE_KEY

Make sure there are no quotation marks in the values while adding.

Open the vercel root folder in the File Explorer and in the file path on top, type cmd and run the following command:
npm install firebase-admin

Push the changes to github and it will be synced in vercel.

In the app, make sure the Notification Permission is allowed.