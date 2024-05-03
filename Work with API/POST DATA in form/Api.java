   private void uploadData() {
        String url = ""; // TODO Replace with your API url
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Log.d("TOKEN_CHECK", "Response : " + response.toString());
                },
                error -> {
                    Log.e("TOKEN_CHECK", "Error  : " + error.getLocalizedMessage() + "");
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Key1", "value 1");
                params.put("Key2", "value 2");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }