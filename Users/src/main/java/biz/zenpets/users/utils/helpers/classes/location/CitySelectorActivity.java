package biz.zenpets.users.utils.helpers.classes.location;

//@SuppressWarnings("ConstantConditions")
//public class CitySelectorActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
//
//    /** THE INCOMING STATE ID **/
//    private String STATE_ID = null;
//
//    /** THE COUNTRIES ADAPTER AND ARRAY LIST INSTANCE **/
//    private CitiesListAdapter adapter;
//    private ArrayList<City> arrCities = new ArrayList<>();
//    private final ArrayList<City> arrFilteredResults = new ArrayList<>();
//
//    /** THE SEARCH VIEW INSTANCE **/
//    private SearchView searchView;
//
//    /** CAST THE LAYOUT ELEMENTS **/
//    @BindView(R.id.linlaProgress) LinearLayout linlaProgress;
//    @BindView(R.id.listCities) RecyclerView listCities;
//    @BindView(R.id.linlaEmpty) LinearLayout linlaEmpty;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.city_selector_list);
//        ButterKnife.bind(this);
//
//        /* INSTANTIATE THE ADAPTER */
//        adapter = new CitiesListAdapter(arrCities);
//
//        /* CONFIGURE THE RECYCLER VIEW */
//        configRecycler();
//
//        /* CONFIGURE THE ACTIONBAR */
//        configAB();
//
//        /* GET THE INCOMING STATE ID */
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null && bundle.containsKey("STATE_ID")) {
//            STATE_ID = bundle.getString("STATE_ID");
//            if (STATE_ID != null) {
//                /* FETCH THE LIST OF CITIES */
//                fetchCities();
//            } else {
//                Toast.makeText(getApplicationContext(), "Failed to get required info...", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        } else {
//            Toast.makeText(getApplicationContext(), "Failed to get required info...", Toast.LENGTH_SHORT).show();
//            finish();
//        }
//    }
//
//    /***** FETCH THE LIST OF CITIES *****/
//    private void fetchCities() {
//        LocationsAPI api = ZenApiClient.getClient().create(LocationsAPI.class);
//        Call<Cities> call = api.allCities(STATE_ID);
//        call.enqueue(new Callback<Cities>() {
//            @Override
//            public void onResponse(Call<Cities> call, Response<Cities> response) {
//                if (response.body() != null && response.body().getCities() != null)  {
//                    arrCities = response.body().getCities();
//                    if (arrCities.size() > 0)    {
//
//                        /* CAST THE PRIMARY ARRAY DATA TO THE FILTERED ARRAY */
//                        arrFilteredResults.addAll(arrCities);
//
//                        /* INSTANTIATE THE CITIES SEARCH ADAPTER */
//                        adapter = new CitiesListAdapter(arrFilteredResults);
//
//                        /* SET THE ADAPTER TO THE CITIES RECYCLER VIEW */
//                        listCities.setAdapter(adapter);
//
//                        /* HIDE THE PROGRESS AFTER FETCHING THE DATA */
//                        linlaProgress.setVisibility(View.GONE);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "An error occurred fetching the list of states", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "An error occurred fetching the list of states", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Cities> call, Throwable t) {
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
//        listCities.setLayoutManager(manager);
//        listCities.setHasFixedSize(true);
//
//        /* SET THE ADAPTER */
//        listCities.setAdapter(adapter);
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
//    /***** THE CITIES ADAPTER *****/
//    private class CitiesListAdapter
//            extends RecyclerView.Adapter<CitiesListAdapter.CitiesVH>
//            implements Filterable {
//
//        /** ARRAY LIST TO GET DATA FROM THE ACTIVITY **/
//        private final ArrayList<City> mArrayList;
//        private ArrayList<City> mFilteredList;
//
//        private CitiesListAdapter(ArrayList<City> arrStates) {
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
//        public void onBindViewHolder(@NonNull final CitiesVH holder, final int position) {
//            final City data = mFilteredList.get(position);
//
//            /* SET THE CITY NAME */
//            if (data.getCityName() != null)
//                holder.txtCountryName.setText(data.getCityName());
//
//            /* SEND THE SELECTED CITY BACK TO THE CALLING ACTIVITY */
//            holder.txtCountryName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    searchView.clearFocus();
//                    Intent intent = new Intent();
//                    intent.putExtra("CITY_ID", data.getCityID());
//                    intent.putExtra("CITY_NAME", data.getCityName());
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//            });
//        }
//
//        @NonNull
//        @Override
//        public CitiesVH onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//
//            View itemView = LayoutInflater.
//                    from(parent.getContext()).
//                    inflate(R.layout.country_row, parent, false);
//
//            return new CitiesVH(itemView);
//        }
//
//        class CitiesVH extends RecyclerView.ViewHolder	{
//            final AppCompatTextView txtCountryName;
////            final AppCompatTextView txtCurrencySymbol;
//            CitiesVH (View v) {
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
//                        ArrayList<City> filteredList = new ArrayList<>();
//                        for (City data : mArrayList) {
//                            if (data.getCityName().toLowerCase().contains(charString)) {
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
//                    mFilteredList = (ArrayList<City>) results.values;
//                    notifyDataSetChanged();
//                }
//            };
//        }
//    }
//}