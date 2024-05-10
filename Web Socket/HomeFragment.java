package com.moutamid.annoe.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpResponse;


import tech.gusavila92.websocketclient.WebSocketClient;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    private WebSocketClient webSocketClient;
    private WebSocketClient webTrainClient;
    private boolean isRunning = false;
    Thread thread;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);

       
            createWebSocketClient();

        return binding.getRoot();
    }


    private void createWebSocketClient() {
        URI uri;
        try {
            // Connect to local host
            uri = new URI("YOUR_URL");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen() {
                Log.i("WebSocket", "Session is starting");
                webSocketClient.send("Hello World!");
            }

            @Override
            public void onTextReceived(String s) {
                Log.i("WebSocket", "Message received");
                final String message = s;
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Log.i("WebSocket", message);
                            
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onBinaryReceived(byte[] data) {
            }

            @Override
            public void onPingReceived(byte[] data) {
            }

            @Override
            public void onPongReceived(byte[] data) {
            }

            @Override
            public void onException(Exception e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onCloseReceived() {
                Log.i("WebSocket", "Closed ");
                System.out.println("onCloseReceived");
            }
        };

        webSocketClient.setConnectTimeout(10000);
        webSocketClient.setReadTimeout(60000);
        webSocketClient.enableAutomaticReconnection(5000);
        webSocketClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        webSocketClient.close();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webSocketClient.close();
    }
}