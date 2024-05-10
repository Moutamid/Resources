package com.moutamid.routineapp.fragments;

public class InsightsFragment extends Fragment implements OnChartValueSelectedListener {
    FragmentInsightsBinding binding;
    int active = 0;
    private BarChart barChart;
    ArrayList<BarEntry> barArraylist;

    public InsightsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInsightsBinding.inflate(getLayoutInflater(), container, false);

        barChart = binding.chart;
        barChart.setOnChartValueSelectedListener(this);

        barArraylist = new ArrayList<>();
        historyList = new ArrayList<>()

	addData();

        return binding.getRoot();
    }

    private void addData() {
        barArraylist.clear();
        for (int i = 1; i<=35; i++){
                barArraylist.add(new BarEntry(i, (new Random().nextInt(30) + 1)));
           }
        barChart.clear();
        initChart();
    }

    private void initChart() {
        BarDataSet barDataSet = new BarDataSet(barArraylist,"NAME");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        //color bar data set
        barDataSet.setColor(Stash.getInt(Constants.COLOR_TEXT, R.color.text));
        barDataSet.setFormLineWidth(5f);
        //text color
        barDataSet.setValueTextColor(Color.WHITE);
       // barData.setValueTypeface(Typeface.createFromAsset(requireContext().getApplicationContext().getAssets(), "poppins.ttf"));
        //settting text size
        barDataSet.setValueTextSize(13f);
        barChart.getDescription().setEnabled(false);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}