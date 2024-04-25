package com.moutamid.daiptv.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.google.android.material.card.MaterialCardView;
import com.moutamid.daiptv.R;
import com.moutamid.daiptv.activities.VideoPlayerActivity;
import com.moutamid.daiptv.database.AppDatabase;
import com.moutamid.daiptv.database.ChannelsDAO;
import com.moutamid.daiptv.models.ChannelsModel;
import com.moutamid.daiptv.models.EPGModel;
import com.moutamid.daiptv.models.UserModel;
import com.moutamid.daiptv.utilis.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ChanelsAdapter extends PagedListAdapter<ChannelsModel, ChanelsAdapter.ParentVH> {

    Context context;
    private static final String TAG = "ChannelsFragment";

    public ChanelsAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<ChannelsModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ChannelsModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull ChannelsModel oldItem, @NonNull ChannelsModel newItem) {
                    return oldItem.getID() == newItem.getID();
                }

                @Override
                public boolean areContentsTheSame(@NonNull ChannelsModel oldItem, @NonNull ChannelsModel newItem) {
                    return Objects.equals(oldItem, newItem);
                }
            };


    @NonNull
    @Override
    public ParentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParentVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.channels_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParentVH holder, int position) {
        ChannelsModel model = getItem(position);
        if (model != null) {
            // Load the data
        }
    }

    public class ParentVH extends RecyclerView.ViewHolder {

        // TODO add your own views

//        TextView title, epg;
//        ImageView image;
//        MaterialCardView add;
        public ParentVH(@NonNull View itemView) {
            super(itemView);
//            add = itemView.findViewById(R.id.add);
//            title = itemView.findViewById(R.id.title);
//            epg = itemView.findViewById(R.id.epg);
//            image = itemView.findViewById(R.id.image);
        }
    }

}
