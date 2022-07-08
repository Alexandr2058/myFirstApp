package com.example.myapplicationdia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplicationdia.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Services_Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    SearchView searchView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        bottomNavigationView = findViewById(R.id.buttonNV);
        bottomNavigationView.setSelectedItemId(R.id.service);
        listView = findViewById(R.id.listViewServ);
        searchView = (SearchView) findViewById(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.docoments:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.service:
                        return true;
                    case R.id.notif:
                        startActivity(new Intent(getApplicationContext(),Notification_Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(),Menu_Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        Resources resources = getResources();
        String[] numbers = resources.getStringArray(R.array.name_of_services);
        ArrayAdapter<String> adapter = new ArrayAdapter<> (this, R.layout.name_item_source, R.id.userName, numbers);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.name_of_services, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);



//      Листнер для итемов
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Services_Activity.this, (String)listView.getItemAtPosition(i), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Services_Activity.this, Emergency.class);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });


    }
}
