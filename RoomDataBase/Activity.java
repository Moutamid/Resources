import android.content.Context;

import androidx.annotation.NonNull;

import com.moutamid.daiptv.adapters.ChanelsAdapter;
import com.moutamid.daiptv.database.AppDatabase;
import com.moutamid.daiptv.models.ChannelsGroupModel;
import com.moutamid.daiptv.utilis.Constants;
import com.moutamid.daiptv.viewmodels.ChannelViewModel;

import java.util.List;

public class ChannelsActivity extends AppCompatActivity {

    ChanelsAdapter adapter;
    ChannelViewModel itemViewModel;
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        adapter = new ChanelsAdapter(mContext);
        binding.channelsRC.setAdapter(adapter);

        itemViewModel = new ViewModelProvider(this).get(ChannelViewModel.class);

        adapter = new ChanelsAdapter(this);
        binding.channelsRC.setAdapter(adapter);

        // for fetching all items that matching the type "TYPE_CHANNEL" value from our database
        itemViewModel.getAll("TYPE_CHANNEL").observe(getViewLifecycleOwner(), adapter::submitList);

        // for fetching the items by group and its type
         itemViewModel.getItemsByGroup("group", "TYPE_CHANNEL").observe(getViewLifecycleOwner(), adapter::submitList);

        // for fetching the whole list without any filter item like by type or by group use this code
        database = AppDatabase.getInstance(this);
        List<ChannelsGroupModel> list = database.channelsDAO().getAllChannels();

        // for updating any item in your database as in this example we are updating the image link for a item position at 0
        int ID = 0; // YOUR_ITEM_ID_FROM_DATABASE;
        String link = "https://google.com";
        database.channelsDAO().update(ID, link);

    }
}