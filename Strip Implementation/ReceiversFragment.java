package com.moutamid.tiptop.tiper_side.fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fxn.stash.Stash;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.moutamid.tiptop.R;
import com.moutamid.tiptop.databinding.FragmentReceiversBinding;
import com.moutamid.tiptop.models.TransactionModel;
import com.moutamid.tiptop.models.UserModel;
import com.moutamid.tiptop.tiper_side.TipInterface;
import com.moutamid.tiptop.tiper_side.adapter.UsersAdapter;
import com.moutamid.tiptop.utilis.Constants;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Transfer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReceiversFragment extends Fragment {
    FragmentReceiversBinding binding;
    RequestQueue requestQueue;
    private static final String TAG = "ReceiversFragment";

    public ReceiversFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReceiversBinding.inflate(getLayoutInflater(), container, false);

        Stripe.apiKey = Constants.API_KEY;

        return binding.getRoot();
    }

    private void sendTransfer(long money) {
        Stripe.apiKey = Stash.getString(Constants.ACCESS_TOKEN);
        Map<String, Object> params = new HashMap<>();
        params.put("amount", money);
        params.put("currency", "usd");
        params.put(
                "destination",
                "RECEIVER_BANK_ACCOUNT_NUMBER" // TODO Replace with receiver account number
        );
        params.put("transfer_group", "ORDER_95");

        try {
            Transfer transfer = Transfer.create(params);
            Log.d(TAG, "sendTransfer: " + transfer.getRawJsonObject().toString());
        } catch (StripeException e) {
            e.printStackTrace();
            Constants.dismissDialog();
        }
    }

}