1. Set up OneSignal
a. Create a OneSignal Account (https://onesignal.com/)
Go to OneSignal and create an account.

b. Create an App in OneSignal Dashboard
Once logged in, you'll need to create an app in the OneSignal Dashboard.
Navigate to Apps.
Click Add App and follow the instructions to create a new app.
Select Android as the platform for your app.
Here you need to upload service_Account.josn that we get from firebase 
Must use Firebase Admin SDK credentials (via service_account.json) for server authentication.
	1. Go to Firebase Console > Project Settings > Service Accounts
	2. Click “Generate new private key”
	3. A service_account.json will be downloaded

c. Get OneSignal App ID and REST API Key
	App ID:
	After creating your app in the OneSignal Dashboard, you'll be able to find the App ID in the Settings > Keys & IDs section.
	Copy this App ID; it will be used in your Android project to send notifications.
	REST API Key:
	The REST API Key can also be found in Settings > Keys & IDs in the OneSignal Dashboard.
	Copy this API Key for use in your app when making API requests.
	
2.  Add OneSignal Dependency to Your Project
	
	dependencies {
    implementation 'com.onesignal:OneSignal:4.0.0'
    implementation 'com.android.volley:volley:1.2.0'
}

3. Retrieving Player ID

	The Player ID is a unique identifier for each device that registers with OneSignal for push notifications.
	 OSDeviceState deviceState = OneSignal.getDeviceState();
        if (deviceState != null) {
            String playerId = deviceState.getUserId();
            Log.d("OneSignal", "Player ID: " + playerId);
            // You can now store this Player ID in Firebase or any other database
        }
