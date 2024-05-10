package com.moutamid.moneytransfer.fragments;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    UserModel userModel;
    ArrayList<TransactionModel> list;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);

 layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.currencyRC.setLayoutManager(layoutManager);
        binding.currencyRC.setHasFixedSize(false);
startAutoScroll();

        return binding.getRoot();
    }

    LinearLayoutManager layoutManager;

    private boolean isScrolling = false;
    private final int scrollSpeed = 100; // Adjust the scroll speed as needed
    private final long scrollDelayMillis = 100; // Adjust the delay between scrolls as needed
    private Handler handler = new Handler();

    private void startAutoScroll() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLastItemVisible()) {
                    // Scroll to the first item if the last item is visible
                    layoutManager.scrollToPosition(0);
                } else {
                    // Scroll by a certain amount (e.g., 100 in my case)
                    // scrollSpeed positive value for scrolling left to right & negative value for right to left
                    binding.currencyRC.smoothScrollBy(scrollSpeed, 0);
                }

                // Repeat the process after the delay
                handler.postDelayed(this, scrollDelayMillis);
            }
        }, scrollDelayMillis);
    }

    // Stop auto-scrolling when needed, for example, in onDestroyView
    private void stopAutoScroll() {
        handler.removeCallbacksAndMessages(null);
    }

    private boolean isLastItemVisible() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) binding.currencyRC.getLayoutManager();
        int pos = layoutManager.findLastCompletelyVisibleItemPosition();
        int numItems = binding.currencyRC.getAdapter() != null ? binding.currencyRC.getAdapter().getItemCount() : 0;
        return pos >= numItems - 1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopAutoScroll();
    }

    private void updateUI() {
        binding.wish.setText(getWish());
        binding.tvNavName.setText(userModel.getName());
        binding.currencyRate.setText(getString(R.string.currency_rates_for) + " " + userModel.getCountry());
        Glide.with(HomeFragment.this).load(userModel.getImage()).placeholder(R.drawable.profile_icon).into(binding.imgNavLogo);
    }
}