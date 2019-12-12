package com.shresthagaurav.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.shresthagaurav.employees.api.EmployeeAPI;
import com.shresthagaurav.employees.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UniqueSearch extends AppCompatActivity {
    SearchView searchView;
    TextView textView;
    private static String base_url = "http://dummy.restapiexample.com/api/v1/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique_search);
        searchView = findViewById(R.id.txt_search);
        textView = findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
                final Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(query));
                listCall.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(UniqueSearch.this, "error"+response.code(), Toast.LENGTH_SHORT).show();
                            Log.d("error","error" +response.code() );
                            return;
                        }
                       String contex="";
                        contex="id: "+response.body().getId()+
                                "\n"+" name: "+response.body().getEmployee_name()+
                                "\n"+" Salary: "+response.body().getEmployee_salary()
                                +"\n"+" Age: "+response.body().getEmployee_age();
                        textView.setText(contex);
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(UniqueSearch.this, "error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error","error   "+t.getLocalizedMessage() );

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


}
