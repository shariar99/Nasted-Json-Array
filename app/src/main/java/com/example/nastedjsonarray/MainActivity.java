package com.example.nastedjsonarray;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ApiService apiService;
    private RecyclerView recyclerView;
    private List<Topping> toppings;
    private YourAdapter toppingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create the API service
        apiService = retrofit.create(ApiService.class);

        // Make the API call
        Call<YourResponseModel> call = apiService.getResponse();
        call.enqueue(new Callback<YourResponseModel>() {
            @Override
            public void onResponse(Call<YourResponseModel> call, Response<YourResponseModel> response) {
                if (response.isSuccessful()) {
                    // Retrieve the topping list from the response
                    YourResponseModel yourResponse = response.body();
                    toppings = yourResponse.getTopping();

                    // Set up the RecyclerView
                    toppingAdapter = new YourAdapter(toppings);
                    recyclerView.setAdapter(toppingAdapter);
                    Toast.makeText(MainActivity.this, "List Fetch Success", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "list Cannot Fetch", Toast.LENGTH_SHORT).show();
                    // Handle API call error
                }
            }

            @Override
            public void onFailure(Call<YourResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                // Handle API call failure
            }
        });
    }
}
