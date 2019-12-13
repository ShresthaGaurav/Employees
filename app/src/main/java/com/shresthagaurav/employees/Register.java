package com.shresthagaurav.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shresthagaurav.employees.api.EmployeeAPI;
import com.shresthagaurav.employees.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Register extends AppCompatActivity {
    EditText etName, etSalary, etage;
    Button btnRegister;
    private static String base_url = "http://dummy.restapiexample.com/api/v1/";


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = findViewById(R.id.et_name);
        etage = findViewById(R.id.et_age);
        etSalary = findViewById(R.id.et_salary);
        btnRegister = findViewById(R.id.btn_register);

btnRegister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        EmployeeCUD employeeCUD = new EmployeeCUD(etName.getText().toString(),Float.parseFloat(etSalary.getText().toString()),Integer.parseInt(etage.getText().toString()),"");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        final Call<Void> listCall = employeeAPI.register(employeeCUD);
        listCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(Register.this, "user created", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Register.this, "user not created", Toast.LENGTH_SHORT).show();

            }
        });

    }
});
    }
}
