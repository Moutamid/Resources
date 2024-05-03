 public void uploadFile() {
        OkHttpClient client = new OkHttpClient();

        String fileName = file.getName();
        String mimeType = URLConnection.guessContentTypeFromName(fileName);

        Log.d(TAG, "fileName: " + fileName);
        Log.d(TAG, "mimeType: " + mimeType);
        RequestBody fileBody = RequestBody.create(MediaType.parse(mimeType), file);

        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        multipartBuilder.addFormDataPart("files[]", fileName, fileBody);

        MultipartBody requestBody = multipartBuilder.build();
        Request request = new Request.Builder()
                .url("https://checkpost.vworks.in/api/upload") // TODO Replace with your api url
                .post(requestBody)
                .addHeader("accept", "*/*")
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("X-Authorization", "Bearer " + token) // TODO Replace with your api token
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(OcrActivity.this, "Image submit failed", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseString = response.body().string();
                    Log.d(TAG, "responseString: " + responseString);
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(OcrActivity.this, "Image submit failed : " + response.message(), Toast.LENGTH_SHORT).show();
                    });
                    // Handle unsuccessful response
                    Log.d(TAG, "onResponse: " + response.message());
                }
            }
        });
    }