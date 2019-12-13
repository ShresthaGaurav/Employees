package com.shresthagaurav.employees.api;

import com.shresthagaurav.employees.model.Employee;
import com.shresthagaurav.employees.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeAPI {
    @GET("employees")
    Call<List<Employee>> getallEmployee();

    @GET("employee/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID") int empid);
    @POST("create")
    Call<Void> register(@Body EmployeeCUD cud);
}
