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
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("accept", "*/*");
                headers.put("X-Authorization", "YOUR TOKEN"); // TODO
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }
