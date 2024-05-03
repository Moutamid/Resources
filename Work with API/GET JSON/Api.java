   private void fetchData(Context context) {
        String url = "www.google.com"; // TODO Replace with your API url
         RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    // Response
                },
                error -> {
                  // Handle Error
                }
        );

        requestQueue.add(jsonObjectRequest);
    }