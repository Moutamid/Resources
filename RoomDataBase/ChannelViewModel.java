package com.moutamid.daiptv.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.fxn.stash.Stash;
import com.moutamid.daiptv.database.ChannelRepository;
import com.moutamid.daiptv.models.ChannelsModel;
import com.moutamid.daiptv.models.UserModel;
import com.moutamid.daiptv.utilis.Constants;
import com.moutamid.daiptv.utilis.CustomArrayListLiveData;
import com.moutamid.daiptv.utilis.CustomDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ChannelViewModel extends AndroidViewModel {
    public static final int PAGE_SIZE = 18;
    private final ChannelRepository repository;

    public ChannelViewModel(@NonNull Application application) {
        super(application);
        repository = new ChannelRepository(application);
    }

    public LiveData<PagedList<ChannelsModel>> getAll(String type){
        return new LivePagedListBuilder<>(repository.getAllItems(type),
                new PagedList.Config.Builder()
                        .setPageSize(PAGE_SIZE)
                        .setEnablePlaceholders(true)
                        .build())
                .build();
    }

    public LiveData<PagedList<ChannelsModel>> getItemsByGroup(String group, String type) {
        return new LivePagedListBuilder<>(repository.getItemsByGroup(group, type),
                new PagedList.Config.Builder()
                        .setPageSize(PAGE_SIZE)
                        .setEnablePlaceholders(true)
                        .build())
                .build();
    }
}
