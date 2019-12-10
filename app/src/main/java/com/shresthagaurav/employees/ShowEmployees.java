package com.shresthagaurav.employees;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shresthagaurav.employees.adapter.AdapterRecycleView;
import com.shresthagaurav.employees.api.EmployeeAPI;
import com.shresthagaurav.employees.model.Employee;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ShowEmployees extends AppCompatActivity {
    RecyclerView recyclerView;
    private static String base_url = "http://dummy.restapiexample.com/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employees);
        recyclerView = findViewById(R.id.ReV);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        final Call<List<Employee>> listCall = employeeAPI.getallEmployee();
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ShowEmployees.this, "error"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("error","error" +response.code() );
                return;
                }
                List<Employee>listemp=response.body();
                AdapterRecycleView adapterRecycleView = new AdapterRecycleView(ShowEmployees.this,listemp);
                recyclerView.setAdapter(adapterRecycleView);
                recyclerView.setLayoutManager(new LinearLayoutManager(ShowEmployees.this));
                
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(ShowEmployees.this, "error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error","error   "+t.getLocalizedMessage() );

            }
        });
    }


}
