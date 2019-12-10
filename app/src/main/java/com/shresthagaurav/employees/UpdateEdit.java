package com.shresthagaurav.employees;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateEdit extends AppCompatActivity {
    Button btn_search, btn_edit,btn_dele;
    EditText et_name, et_salary, et_age, et_search;


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
    }

}
