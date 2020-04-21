package com.shabalin.coronatracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.shabalin.coronatracking.data.CountriesList;
import com.shabalin.coronatracking.data.Country;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdView mAdView;

    private CountryAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Country statistics");

        apiCall();

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("6A62EB6D124666267E1A8BCE83E5ADD9").build();
        mAdView.loadAd(adRequest);

    }

    private void apiCall() {
        ApiService service = RetroInstance.getRetrofitInstance().create(ApiService.class);
        Call<CountriesList> call = service.getAllCountries();
        call.enqueue(new Callback<CountriesList>() {
            @Override
            public void onResponse(Call<CountriesList> call, Response<CountriesList> response) {

                if (response.isSuccessful()) {

                    List<Country> list =  response.body().getCountries();
                    adapter = new CountryAdapter(list, getApplicationContext());
                    recyclerView.setAdapter(adapter);


                } else {
                    Toast.makeText(CountryActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CountriesList> call, Throwable t) {

                Log.i("MyMeta", "Fail");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
