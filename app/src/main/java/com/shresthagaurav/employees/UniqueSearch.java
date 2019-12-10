package com.shresthagaurav.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

public class UniqueSearch extends AppCompatActivity {
    SearchView searchView;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique_search);
        searchView = findViewById(R.id.txt_search);
        recyclerView = findViewById(R.id.RVunique);

    }


}
