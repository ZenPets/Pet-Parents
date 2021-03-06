package biz.zenpets.users.utils.helpers.classes.location;

//@SuppressWarnings("ConstantConditions")
//public class StateSelectorActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
//
//    /** THE INCOMING COUNTRY ID **/
//    private String COUNTRY_ID = null;
//
//    /** THE COUNTRIES ADAPTER AND ARRAY LIST INSTANCE **/
//    private StatesListAdapter adapter;
//    private ArrayList<State> arrStates = new ArrayList<>();
//    private final ArrayList<State> arrFilteredResults = new ArrayList<>();
//
//    /** THE SEARCH VIEW INSTANCE **/
//    private SearchView searchView;
//
//    /** CAST THE LAYOUT ELEMENTS **/
//    @BindView(R.id.linlaProgress) LinearLayout linlaProgress;
//    @BindView(R.id.listStates) RecyclerView listStates;
//    @BindView(R.id.linlaEmpty) LinearLayout linlaEmpty;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.state_selector_list);
//        ButterKnife.bind(this);
//
//        /* INSTANTIATE THE ADAPTER */
//        adapter = new StatesListAdapter(arrStates);
//
//        /* CONFIGURE THE RECYCLER VIEW */
//        configRecycler();
//
//        /* CONFIGURE THE ACTIONBAR */
//        configAB();
//
//        /* FETCH THE LIST OF STATES */
//        fetchStates();
//
////        /* GET THE INCOMING COUNTRY ID */
////        Bundle bundle = getIntent().getExtras();
////        if (bundle != null && bundle.containsKey("COUNTRY_ID")) {
////            COUNTRY_ID = bundle.getString("COUNTRY_ID");
////            if (COUNTRY_ID != null) {
////                /* FETCH THE LIST OF STATES */
////                fetchStates();
////            } else {
////                Toast.makeText(getApplicationContext(), "Failed to get required info...", Toast.LENGTH_SHORT).show();
////                finish();
////            }
////        } else {
////            Toast.makeText(getApplicationContext(), "Failed to get required info...", Toast.LENGTH_SHORT).show();
////            finish();
////        }
//    }
//
//    /***** FETCH THE LIST OF STATES *****/
//    private void fetchStates() {
//        LocationsAPI api = ZenApiClient.getClient().create(LocationsAPI.class);
//        Call<States> call = api.allStates("51");
//        call.enqueue(new Callback<States>() {
//            @Override
//            public void onResponse(Call<States> call, Response<States> response) {
//                if (response.body() != null && response.body().getStates() != null)  {
//                    arrStates = response.body().getStates();
//                    if (arrStates.size() > 0)    {
//
//                        /* CAST THE PRIMARY ARRAY DATA TO THE FILTERED ARRAY */
//                        arrFilteredResults.addAll(arrStates);
//
//                        /* INSTANTIATE THE STATES SEARCH ADAPTER */
//                        adapter = new StatesListAdapter(arrFilteredResults);
//
//                        /* SET THE ADAPTER TO THE STATES RECYCLER VIEW */
//                        listStates.setAdapter(adapter);
//
//                        /* HIDE THE PROGRESS AFTER FETCHING THE DATA */
//                        linlaProgress.setVisibility(View.GONE);
//                    } else {
//                        /* SHOW THE EMPTY LAYOUT AND HIDE THE RECYCLER VIEW */
//                        linlaEmpty.setVisibility(View.VISIBLE);
//                        listStates.setVisibility(View.GONE);
//                    }
//                } else {
//                    /* SHOW THE EMPTY LAYOUT AND HIDE THE RECYCLER VIEW */
//                    linlaEmpty.setVisibility(View.VISIBLE);
//                    listStates.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<States> call, Throwable t) {
////                Log.e("STATES FAILURE", t.getMessage());
//                Crashlytics.logException(t);
//            }
//        });
//    }
//
//    /***** CONFIGURE THE ACTIONBAR *****/
//    private void configAB() {
//        Toolbar myToolbar = findViewById(R.id.myToolbar);
//        setSupportActionBar(myToolbar);
//        String strTitle = "Select Your State";
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setTitle(strTitle);
//        getSupportActionBar().setSubtitle(null);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_country_search, menu);
//        MenuItem search = menu.findItem(R.id.menuSearchCountries);
//        searchView = (SearchView) search.getActionView();
//        searchView.setIconified(true);
//        searchView.setOnQueryTextListener(this);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                break;
//            default:
//                break;
//        }
//        return false;
//    }
//
//    /** CONFIGURE THE RECYCLER VIEW **/
//    private void configRecycler() {
//        /* SET THE CONFIGURATION */
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        listStates.setLayoutManager(manager);
//        listStates.setHasFixedSize(true);
//
//        /* SET THE ADAPTER */
//        listStates.setAdapter(adapter);
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String search) {
//        adapter.getFilter().filter(search);
//        return true;
//    }
//
//    /***** THE STATES ADAPTER *****/
//    private class StatesListAdapter
//            extends RecyclerView.Adapter<StatesListAdapter.StatesVH>
//            implements Filterable {
//
//        /** ARRAY LIST TO GET DATA FROM THE ACTIVITY **/
//        private final ArrayList<State> mArrayList;
//        private ArrayList<State> mFilteredList;
//
//        private StatesListAdapter(ArrayList<State> arrStates) {
//
//            /* CAST THE CONTENTS OF THE ARRAY LIST IN THE METHOD TO THE LOCAL INSTANCE */
//            this.mArrayList = arrStates;
//            this.mFilteredList = arrStates;
//        }
//
//        @Override
//        public int getItemCount() {
//            return mFilteredList.size();
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull final StatesVH holder, final int position) {
//            final State data = mFilteredList.get(position);
//
//            /* SET THE STATE NAME */
//            if (data.getStateName() != null)
//                holder.txtCountryName.setText(data.getStateName());
//
//            /* SEND THE SELECTED STATE BACK TO THE CALLING ACTIVITY */
//            holder.txtCountryName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    searchView.clearFocus();
//                    Intent intent = new Intent();
//                    intent.putExtra("STATE_ID", data.getStateID());
//                    intent.putExtra("STATE_NAME", data.getStateName());
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//            });
//        }
//
//        @NonNull
//        @Override
//        public StatesVH onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//
//            View itemView = LayoutInflater.
//                    from(parent.getContext()).
//                    inflate(R.layout.country_row, parent, false);
//
//            return new StatesVH(itemView);
//        }
//
//        class StatesVH extends RecyclerView.ViewHolder	{
//            final AppCompatTextView txtCountryName;
////            final AppCompatTextView txtCurrencySymbol;
//            StatesVH (View v) {
//                super(v);
//                txtCountryName = v.findViewById(R.id.txtCountryName);
////                txtCurrencySymbol = v.findViewById(R.id.txtCurrencySymbol);
//            }
//        }
//
//        @Override
//        public Filter getFilter() {
//            return new Filter() {
//
//                @Override
//                protected FilterResults performFiltering(CharSequence constraint) {
//                    String charString = constraint.toString();
//
//                    if (charString.isEmpty()) {
//                        mFilteredList = mArrayList;
//                    } else {
//                        ArrayList<State> filteredList = new ArrayList<>();
//                        for (State data : mArrayList) {
//                            if (data.getStateName().toLowerCase().contains(charString)) {
//                                filteredList.add(data);
//                            }
//                        }
//                        mFilteredList = filteredList;
//                    }
//
//                    FilterResults filterResults = new FilterResults();
//                    filterResults.values = mFilteredList;
//                    return filterResults;
//                }
//
//                @Override
//                protected void publishResults(CharSequence constraint, FilterResults results) {
//                    mFilteredList = (ArrayList<State>) results.values;
//                    notifyDataSetChanged();
//                }
//            };
//        }
//    }
//}