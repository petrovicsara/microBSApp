package com.example.microbsentryapp.view;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.microbsentryapp.R;

public class StartServiceActivity extends AppCompatActivity {

    EditText etCity;
    TextView tvTemp;
    Button btnWeather;

    String url = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    String apikey = "828dcf18354478d261299e36c52f3f1f";
    LocationManager manager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);

        etCity = findViewById(R.id.etCityName);
        tvTemp = findViewById(R.id.tvTemp);
        btnWeather = findViewById(R.id.btnWeather);

        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                weatherapi myapi = retrofit.create(weatherapi.class);
                Call<Example> examplecall = myapi.getweather(etCity.getText().toString().trim(),apikey);
                examplecall.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        if(response.code() == 404){
                            Toast.makeText(StartServiceActivity.this, "", Toast.LENGTH_SHORT).show();
                        }
                        else if(!(response.isSuccessful())){
                            Toast.makeText(StartServiceActivity.this,response.code() + " ",Toast.LENGTH_LONG).show();
                            return;
                        }
                        Example mydata = response.body();
                        Main main = mydata.getMain();
                        Double temp = main.getTemp();
                        Integer temperature = (int)(temp - 273.15);
                        tvTemp.setText(String.valueOf(temperature) + "C");
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast.makeText(StartServiceActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}