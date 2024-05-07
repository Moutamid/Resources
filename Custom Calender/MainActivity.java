package com.example.sampleappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CalendarRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    public LocalDate startDate, endDate, todayDate;
    boolean changeDetected = false;
    boolean scrollPager = true;
    TextView monthYear;
    RecyclerView recyclerView;
    public static LocalDate selected;
    private ArrayList<DateModelClass> dateArrayList;
    private List<LocalDate> dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        monthYear=findViewById(R.id.monthYear);

        dateArrayList = new ArrayList<>();
        Calendar(this, this);
    }


    public static LocalDate getSelected() {
        return selected;
    }

    public void Calendar(Context context, Activity activity) {
        startDate = new LocalDate(2022, 1, 1);
        endDate = new LocalDate(2050, 12, 31);
        todayDate = new LocalDate();

        initControl(context, activity);
    }


    @SuppressLint("ResourceAsColor")
    private void initControl(Context context, Activity activity) {

        monthYear.setBackgroundColor(R.color.app_color);
        setUpRecyclerView(context);
        setUpListeners(context);
    }

    private void setUpListeners(final Context context) {
        Log.d("dateeee", todayDate.getMonthOfYear() + "  " + todayDate.getYear());
        setMonthYearText(getMonthName(todayDate.getMonthOfYear()), "" + todayDate.getYear());
        LocalDate t = new LocalDate();
        if (selected != null && !selected.isEqual(t)) {
            selected = t;
        } else if (selected == null) {
            selected = t;
        }
        mAdapter.notifyDataSetChanged();
        mLayoutManager.scrollToPositionWithOffset(Days.daysBetween(startDate, todayDate).getDays() - 1, 0);

    }

    private void setUpRecyclerView(final Context context) {
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        dates = new ArrayList<>();
        int days = Days.daysBetween(startDate, endDate).getDays();

        for (int i = 0; i < days; i++) {
            LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i);
            dates.add(d);
        }
        if (dateArrayList != null) {
            mAdapter = new CalendarRecyclerViewAdapter(dates, dateArrayList);
            recyclerView.setAdapter(mAdapter);
        }
        mLayoutManager.scrollToPositionWithOffset(Days.daysBetween(startDate, todayDate).getDays() - 1, 0);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (scrollPager) {
                    int position = mLayoutManager.findFirstVisibleItemPosition() + 1;
                    LocalDate d = dates.get(position);
                    int year = d.getYear();
                    int month = d.getMonthOfYear();
                    setMonthYearText(getMonthName(month), "" + year);
                    changeDetected = true;
                    changeDetected = false;
                } else {
                    scrollPager = true;
                }
            }
        });

    }

    private String getMonthName(int month) {

        switch (month) {

            case 1:
                return "Jan";

            case 2:
                return "Feb";

            case 3:
                return "Mar";

            case 4:
                return "Apr";

            case 5:
                return "May";

            case 6:
                return "Jun";

            case 7:
                return "Jul";

            case 8:
                return "Aug";

            case 9:
                return "Sep";

            case 10:
                return "Oct";

            case 11:
                return "Nov";

            case 12:
                return "Dec";
        }

        return null;
    }

    private void setMonthYearText(String month, String year) {
        monthYear.setText(month + ", " + year);
    }
}