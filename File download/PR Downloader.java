public class PRDOWNLOAD {

	int downloadID;

public void startPRDownloader() {
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setReadTimeout(30000)
                .setConnectTimeout(30000)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);
        String url = userModel.url;
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/DA IPTV/";
        Log.d(TAG, "startDownloading: " + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();  // Create the file if it doesn't exist
        }
        Log.d(TAG, "url: " + url);
	// here i am downloading a mp4 file but if your file is pdf then you need to replace ".mp4" with ".pdf" and same for any other files extension
	String filename = "FILE_NAME" + ".mp4"
        downloadID = PRDownloader.download(url, filePath, filename)
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {
                        Log.d(TAG, "onStartOrResume: Started");
                     	// this function run when the file is started to download
                    }
                })
                .setOnPauseListener(() -> {

                })
                .setOnCancelListener(() -> {

                })
                .setOnProgressListener(progress -> {
                    Log.d(TAG, "onProgress: current " + progress.totalBytes);
                    Log.d(TAG, "onProgress: total " + progress.totalBytes);
                    int pro = (int) ((progress.currentBytes / progress.totalBytes) * 100);
                    // binding.progress.setText(pro + "%");  Set the progress to textview of progressbar
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // this function run when the download is completed successfully
                    }

                    @Override
                    public void onError(Error error) {
                        Log.d(TAG, "onError: serverError " + error.isServerError());
                        Log.d(TAG, "onError: connectionError " + error.isConnectionError());
                        if (error.isConnectionError()) {
                            Log.d(TAG, "Connection Error : " + error.getConnectionException().getMessage());
                        } else if (error.isServerError()) {
                            Log.d(TAG, "Server Error : " + error.getServerErrorMessage());
                        } else {
                            Log.d(TAG, "Error : " + error.getResponseCode());
                        }
                    }
                });

	// if you need to pause the downloading use this line 
	//	PRDownloader.pause(downloadId);
	// if you need to resume the downloading use this line 
	//	PRDownloader.resume(downloadId);
	// Cancel with the download id
	//	PRDownloader.cancel(downloadId);
	// Cancel all the requests
	//	PRDownloader.cancelAll();
	// Status of a download request
	//	Status status = PRDownloader.getStatus(downloadId);
    }

}