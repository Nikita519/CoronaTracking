package com.shabalin.coronatracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shabalin.coronatracking.data.CountriesList;
import com.shabalin.coronatracking.data.Global;
import com.tomer.fadingtextview.FadingTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textViewTotalConfirmed;
    TextView textViewNewConfirmed;
    TextView textViewTotalDeaths;
    TextView textViewNewDeaths;
    TextView textViewTotalRecovered;
    TextView textViewNewRecovered;
    Button buttonCountryStatistics;

    FadingTextView fadingTextViewCovid;

    SwipeRefreshLayout  swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTotalConfirmed = findViewById(R.id.textViewTotalConfirmed);
        textViewNewConfirmed = findViewById(R.id.textViewNewConfirmed);
        textViewTotalDeaths = findViewById(R.id.textViewTotalDeaths);
        textViewNewDeaths = findViewById(R.id.textViewNewDeaths);
        textViewTotalRecovered = findViewById(R.id.textViewTotalRecovered);
        textViewNewRecovered = findViewById(R.id.textViewNewRecovered);
        buttonCountryStatistics = findViewById(R.id.buttonCountryStatistics);

        fadingTextViewCovid = findViewById(R.id.fadingTextViewCovid);
        String[] covid = {"COVID-19"};
        fadingTextViewCovid.setTexts(covid);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshMain);

        apiCall();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiCall();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        buttonCountryStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                startActivity(intent);
            }
        });



    }

    private void apiCall() {
        ApiService service = RetroInstance.getRetrofitInstance().create(ApiService.class);
        Call<CountriesList> call = service.getAllCountries();
        call.enqueue(new Callback<CountriesList>() {
            @Override
            public void onResponse(Call<CountriesList> call, Response<CountriesList> response) {

                if (response.isSuccessful()) {
                    textViewTotalConfirmed.setText(String.valueOf(response.body().getGlobal().getTotalConfirmed()));
                    textViewNewConfirmed.setText(String.valueOf(response.body().getGlobal().getNewConfirmed()));
                    //String[] nc = {String.valueOf(response.body().getGlobal().getNewConfirmed())};
                    //textViewNewRecovered.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_anim));
                    textViewTotalDeaths.setText(String.valueOf(response.body().getGlobal().getTotalDeaths()));
                    textViewNewDeaths.setText(String.valueOf(response.body().getGlobal().getNewDeaths()));
                    textViewTotalRecovered.setText(String.valueOf(response.body().getGlobal().getTotalRecovered()));
                    textViewNewRecovered.setText(String.valueOf(response.body().getGlobal().getNewRecovered()));

                } else {
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CountriesList> call, Throwable t) {

                Log.i("MyMeta", "Fail");
            }
        });
    }
}
