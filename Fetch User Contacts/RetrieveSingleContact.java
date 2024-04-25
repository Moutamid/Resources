package com.moutamid.callmanager;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.moutamid.callmanager.models.ContactModel;

import java.util.ArrayList;

public class ContactManager {

/** You need to add this code in your Activity Class */

// TODO you need to add this permission in manifest : <uses-permission android:name="android.permission.READ_CONTACTS" />
	
	public final int PICK_CONTACT_REQUEST = 1;

	button.setOnClickListener(v -> {
         	Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        	pickContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
       		startActivityForResult(pickContact, PICK_CONTACT_REQUEST);
        });

 
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            Uri contactUri = data.getData();
            if (contactUri != null) {
                Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                        int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        
			String contactName = cursor.getString(nameIndex);
                        String contactNumber = cursor.getString(phoneIndex);
                        
                    } else {
                        Log.d("CHECK123", "Empty");
                    }
                    cursor.close();
                }
            }
        }
    }
	

}
