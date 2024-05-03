private void callApi(Context context, String notifyMessage, String sender) {
        RequestQueue requestQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JSONObject json = new JSONObject();
        try {
            String url = "http://3.29.136.159:8000/create"; // Replace with your API url
            json.put("key1", "value 1");
            json.put("key2", "value 2");
            
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json,
                    response -> {  
                        Log.d("TOKEN_CHECK", "Response : " + response.toString());
                    },
                    error -> {
                        Log.e("TOKEN_CHECK", "Error  : " + error.getLocalizedMessage());
                    }
            );
            requestQueue.add(request);
        } catch (JSONException|SecurityException e) {
            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
