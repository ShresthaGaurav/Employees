package com.shresthagaurav.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Register extends AppCompatActivity {
    EditText etName, etSalary, etage;
    Button btnRegister;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = findViewById(R.id.et_name);
        etage = findViewById(R.id.et_age);
        etSalary = findViewById(R.id.et_salary);
        btnRegister = findViewById(R.id.btn_register);


    }
}
