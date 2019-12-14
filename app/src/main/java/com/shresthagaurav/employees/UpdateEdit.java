package com.shresthagaurav.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shresthagaurav.employees.api.EmployeeAPI;
import com.shresthagaurav.employees.model.Employee;
import com.shresthagaurav.employees.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateEdit extends AppCompatActivity {
    Button btn_search, btn_edit,btn_dele;
    EditText et_name, et_salary, et_age, et_search;
    private static String base_url = "http://dummy.restapiexample.com/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_edit);
        btn_search = findViewById(R.id.btn_se_update);
        btn_edit = findViewById(R.id.btn_edit);
        btn_dele = findViewById(R.id.btn_delete);
        et_search = findViewById(R.id.txtsearch);
        et_name = findViewById(R.id.up_name);
        et_age = findViewById(R.id.up_age);
        et_salary = findViewById(R.id.up_salary);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
                final Call<Employee> employeeCall = employeeAPI.getEmployeeByID(Integer.parseInt(et_search.getText().toString()));
                employeeCall.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(UpdateEdit.this, "error"+response.code(), Toast.LENGTH_SHORT).show();
                            Log.d("error","error" +response.code() );
                            return;
                        }
                        et_name.setText(response.body().getEmployee_name());
                        et_age.setText(String.valueOf(response.body().getEmployee_age()));
                        et_salary.setText(String.valueOf(response.body().getEmployee_salary()));

                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText( UpdateEdit.this, "error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error","error   "+t.getLocalizedMessage() );

                    }
                });
            }
        });
      btn_edit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String name = et_name.getText().toString();
              Float salary =Float.parseFloat(et_salary.getText().toString());
              int age = Integer.parseInt(et_age.getText().toString());
              EmployeeCUD CUD = new EmployeeCUD(name,salary,age,"");
              Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                      .addConverterFactory(GsonConverterFactory.create())
                      .build();
              EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
              Call<Void> voidCall= employeeAPI.updateEmployee(Integer.parseInt(et_search.getText().toString()),CUD);
              voidCall.enqueue(new Callback<Void>() {
                  @Override
                  public void onResponse(Call<Void> call, Response<Void> response) {
                      if(!response.isSuccessful()){
                          Toast.makeText(UpdateEdit.this, "error"+response.code(), Toast.LENGTH_SHORT).show();
                          Log.d("error","error" +response.code() );
                          return;
                      }
                      Toast.makeText(UpdateEdit.this, "Update", Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void onFailure(Call<Void> call, Throwable t) {
                      Toast.makeText( UpdateEdit.this, "error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                      Log.d("error","error   "+t.getLocalizedMessage() );

                  }
              });
          }
      });
      btn_dele.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                      .addConverterFactory(GsonConverterFactory.create())
                      .build();
              EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
              Call<Void> voidCall = employeeAPI.deleteEmployee(Integer.parseInt(et_search.getText().toString()));
              voidCall.enqueue(new Callback<Void>() {
                  @Override
                  public void onResponse(Call<Void> call, Response<Void> response) {
                      if(!response.isSuccessful()){
                          Toast.makeText(UpdateEdit.this, "error"+response.code(), Toast.LENGTH_SHORT).show();
                          Log.d("error","error" +response.code() );
                          return;
                      }
                      Toast.makeText(UpdateEdit.this, "deleted", Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void onFailure(Call<Void> call, Throwable t) {
                      Toast.makeText( UpdateEdit.this, "error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                      Log.d("error","error   "+t.getLocalizedMessage() );
                  }
              });
          }

      });
    }

}
