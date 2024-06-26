package com.moutamid.sampleproject;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.moutamid.sampleproject.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    
    private MessageAdapter message_adapter;
    private ArrayList<GPTModel> messages;

    GPTViewModel viewModel;
    String keyValue="PLACE_YOUR_API_KEY_HERE";
    String url = "https://api.openai.com/v1/chat/completions";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //ViewModel Bind
        viewModel = ViewModelProviders.of(this).get(GPTViewModel.class);
      
        //init rv
        messages = new ArrayList<>();
        message_adapter = new MessageAdapter(messages);
        LinearLayoutManager llm = new LinearLayoutManager(this.getApplicationContext());
        llm.setStackFromEnd(true);
        binding.recyclerView.setLayoutManager(llm);
        binding.recyclerView.setAdapter(message_adapter);
        binding.recyclerView.smoothScrollToPosition(message_adapter.getItemCount());


        //onClickListener
        binding.send.setOnClickListener(it -> {
            if (binding.txtChat.getText().length() > 0) {
                hideKeyboardFrom(getApplicationContext(), it);
                messages.add(new GPTModel(binding.txtChat.getText().toString(), "user"));
                message_adapter.notifyDataSetChanged();
                messages.add(new GPTModel("Typing...", "gpt"));
                try {
                    getResponse(binding.txtChat.getText().toString().trim());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else {
                runOnUiThread((Runnable) (new Runnable() {
                    public void run() {
                        Toast toast = Toast.makeText(getApplicationContext(), (CharSequence) "Please write here", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void getResponse(String question) throws JSONException {
        binding.txtChat.setText("");
        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", "gpt-3.5-turbo");
        JSONArray messagesArray = new JSONArray();
        JSONObject messageObject = new JSONObject();
        messageObject.put("role", "user");
        messageObject.put("content", question);
        messagesArray.put(messageObject);
        jsonObject.put("messages", messagesArray);
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    messages.remove(messages.size() - 1);
                    String resMessage = response.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content").trim();
                    messages.add(new GPTModel(resMessage, "gpt"));
                    message_adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, error -> {
            Toast.makeText(getApplicationContext(), "Failed response", Toast.LENGTH_SHORT)
                    .show();
        }) {
            @NotNull
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer "+keyValue);
                return params;
            }
        };
        postRequest.setRetryPolicy((RetryPolicy) (new RetryPolicy() {
            public int getCurrentTimeout() {
                return 50000;
            }
            public int getCurrentRetryCount() {
                return 50000;
            }
            public void retry(@Nullable VolleyError error) {
                runOnUiThread((Runnable) (new Runnable() {
                    public final void run() {
                        Toast toast = Toast.makeText(getApplicationContext(), (CharSequence) "API expired", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }));
            }
        }));
        queue.add(postRequest);
    }

    //Hide Keyboard
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
