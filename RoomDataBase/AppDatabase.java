package com.moutamid.daiptv.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.moutamid.daiptv.models.ChannelsGroupModel;
import com.moutamid.daiptv.models.ChannelsModel;
import com.moutamid.daiptv.models.EPGModel;
import com.moutamid.daiptv.models.FavoriteChannelModel;
import com.moutamid.daiptv.models.MoviesGroupModel;
import com.moutamid.daiptv.models.RecentChannelsModel;
import com.moutamid.daiptv.models.SeriesGroupModel;


/**
 * This reference is used for single table in your database
 * if you have multiple tables then use the below line  for multiple tables in your database
 *
 *  @Database(entities = {  ChannelsModel.class, ChannelsGroupModel.class, SeriesGroupModel.class,
 *                         MoviesGroupModel.class, RecentChannelsModel.class, EPGModel.class
 *                     }, version = 1, exportSchema = false)
 *
 *     Just add the comma sperared Model Class name. as in the about code snippet we are creating 6 tables in DataBase
 * */
@Database(entities = {  ChannelsModel.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase database;
    private static String Channels_DATABASE = "Channels_DATABASE";

    public synchronized static AppDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Channels_DATABASE)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract ChannelsDAO channelsDAO();

}
