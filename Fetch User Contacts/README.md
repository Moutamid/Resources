These classes is used for fetching the user contacts as single selection or by fetching the whole list at once

for that you need to add this permission in your manifest and ask this permission from user to allow this permission;

<uses-permission android:name="android.permission.READ_CONTACTS" />


for fetching the whole list just add this line 

ArrayList<ContactModel> list = ContactManager.getAllContacts(this);

// the ContactModel class is also available in this repository you can vist this model class as well.


for fetching the single selection start the intent on a button or your prefrence and copy the code of onActivityResult and you will get the contact name and contact number
and do with it what you need to do 
