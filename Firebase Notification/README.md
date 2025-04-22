Step 1: Create *FirebaseUtils*
A class that save the device's FCM token in Firebase Realtime Database under the user's UID.

Step 2:  Create *MyFirebaseMessagingService*
This class is responsible for handling push notifications and FCM token updates. 
It save device token in Firebase in *onNewToken* method 
onMessageReceived()	It handles incoming messages
showNotification()	It shows the actual notification

Step 3: Create *FCMNotificationHelper*
It is used to send notfication

🔐 Required Setup
Must use Firebase Admin SDK credentials (via service_account.json) for server authentication.
	1. Go to Firebase Console > Project Settings > Service Accounts
	2. Click “Generate new private key”
	3. A service_account.json will be downloaded
	4. Place this file inside: 
		/app/src/main/assets/service_account.json
This file is read by TokenManager that also we created to generate a valid OAuth token.
We also need Project ID 
	1. Enable Firebase Cloud Messaging
	2. Inside your project settings > "Cloud Messaging" tab
	3. Note down your Project ID
	4. Then put here
	   "https://fcm.googleapis.com/v1/projects/*Project_ID*/messages:send";

Step 4: 
Find client_email in your /assets/service_account.json. Copy This
Go to the Firebase Console (IAM Settings)
Visit https://console.firebase.google.com/

Select your project
Click on the gear icon next to Project Overview → Select Project settings
Now switch to Google Cloud Console:
Click on the link under "Project ID" (e.g., your-project-id) — this opens the Google Cloud Console.
	Open IAM Permissions Page
	Go to: https://console.cloud.google.com/iam-admin/iam
In New principals, paste the client_email.
In the Role dropdown:  Select
Firebase Admin 
 Save it
Step 5: Permissions
 we need these Permissionsin Menifest file
 
 <uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.VIBRATE"/>
<uses-permission android:name="android.permission.WAKE_LOCK"/>
<uses-permission android:name="com.google.android.c2dm.permission.RECEIVE"/>
 
 Also, we need to call our register here as below , it is used to handle incoming FCM messages
 
 <service android:name=".MyFirebaseMessagingService" android:exported="false">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT"/>
    </intent-filter>
</service>
