package com.kundan.transportdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kundan.transportdemo.interfaces.CustomApiInterface;
import com.kundan.transportdemo.model.TransportModel;
import com.kundan.transportdemo.restClient.RestClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button navigateBtn;
    private TextView textViewCar, textViewTrain;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        textViewCar = (TextView) findViewById(R.id.textViewCar);
        textViewTrain = (TextView) findViewById(R.id.textViewTrain);
        navigateBtn = (Button) findViewById(R.id.navigateBtn);
        navigateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Latitude:" + latitude + " Longitude:" + longitude, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                intent.putExtra("KEY_LAT",latitude);
                intent.putExtra("KEY_LONG",longitude);
                startActivity(intent);


            }
        });
        CustomApiInterface service = RestClient.getClient();
        Call<ArrayList<TransportModel>> call = service.getTransportList();
        call.enqueue(new Callback<ArrayList<TransportModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TransportModel>> call, Response<ArrayList<TransportModel>> response) {

                Log.d("MainActivity", "Status Codeeee = " + response.code());
                final ArrayList<TransportModel> result = response.body();
                Log.d("MainActivity", "response = " + new Gson().toJson(result));
                CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(MainActivity.this, result);
                spinner.setAdapter(customSpinnerAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        textViewCar.setVisibility(View.VISIBLE);
                        textViewTrain.setVisibility(View.VISIBLE);

                        if (result.get(position).getFromcentral().getCar() != null) {
                            textViewCar.setText("Car-" + result.get(position).getFromcentral().getCar());
                        } else {
                            textViewCar.setVisibility(View.GONE);
                        }
                        if (result.get(position).getFromcentral().getTrain() != null) {
                            textViewTrain.setText("Train-" + result.get(position).getFromcentral().getTrain());
                        } else {
                            textViewTrain.setVisibility(View.GONE);
                        }
                        if (result.get(position).getLocation() != null) {
                            latitude = result.get(position).getLocation().getLatitude();
                            longitude = result.get(position).getLocation().getLongitude();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<TransportModel>> call, Throwable t) {

            }
        });
    }
}
