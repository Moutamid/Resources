public class FirebaseStorageDownloader {

	

public void startDownload() {
	String link = "YOUR_FILE_URL_FROM_FIREBASE_STORAGE";
        StorageReference sr = FirebaseStorage.getInstance().getReferenceFromUrl(link);
        String path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + getString(R.string.app_name) + File.separator;
        File f = new File(path);
        f.mkdirs();
        File local = new File(path, sr.getName());
        Log.d(TAG, "local: " + local.getAbsolutePath());
        sr.getFile(local)
                .addOnProgressListener(snapshot -> {
                    double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                    // update the view like textView or progressBar with this progress value
                })
                .addOnSuccessListener(taskSnapshot -> {
                    Toast.makeText(this, "File downloaded successfully", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

}