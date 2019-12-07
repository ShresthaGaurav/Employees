package com.shresthagaurav.employees;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.shresthagaurav.employees.adapter.AdapterRecycleView;
import com.shresthagaurav.employees.database.DBHelper;
import com.shresthagaurav.employees.model.EmployeeS;

import java.util.ArrayList;
import java.util.List;


public class ShowEmployees extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHelper dbh = new DBHelper(this);
    List<EmployeeS> employeeSList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employees);
        recyclerView = findViewById(R.id.ReV);

        Cursor cursor = dbh.GetEmployess();
        if (cursor.getCount() == 0) {
            // show message
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            return;
        }


        while (cursor.moveToNext()) {
            employeeSList.add(new EmployeeS(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getInt(3)));
         
        }

        AdapterRecycleView adapterRecycleView = new AdapterRecycleView(this, employeeSList);
        recyclerView.setAdapter(adapterRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }




}
