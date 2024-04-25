package com.moutamid.signalcopy.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.moutamid.signalcopy.Constants;
import com.moutamid.signalcopy.listeners.DeleteListener;
import com.moutamid.signalcopy.R;
import com.moutamid.signalcopy.model.MessageModel;
import com.moutamid.signalcopy.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageVH> {

    Context context;
    ArrayList<MessageModel> list;
    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;

    public MessageAdapter(Context context, ArrayList<MessageModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MessageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MSG_TYPE_LEFT) {
            view = LayoutInflater.from(context).inflate(R.layout.chat_left_side, parent, false);
        } else if (viewType == MSG_TYPE_RIGHT) {
            view = LayoutInflater.from(context).inflate(R.layout.chat_right, parent, false);
        }
        return new MessageVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageVH holder, int position) {
        MessageModel model = list.get(holder.getAbsoluteAdapterPosition());



    }

    @Override
    public int getItemViewType(int position) {
        // get currently signed in user
        UserModel userModel = (UserModel) Stash.getObject(Constants.STASH_USER, UserModel.class);
        return userModel.number.equals(list.get(position).getSenderID()) ? MSG_TYPE_RIGHT : MSG_TYPE_LEFT;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MessageVH extends RecyclerView.ViewHolder {
//        TextView message, time;
//        ImageView imageView, check;

        public MessageVH(@NonNull View itemView) {
            super(itemView);
//            message = itemView.findViewById(R.id.message);
//            time = itemView.findViewById(R.id.time);
//            imageView = itemView.findViewById(R.id.photo);
//            check = itemView.findViewById(R.id.check);
        }
    }

}
