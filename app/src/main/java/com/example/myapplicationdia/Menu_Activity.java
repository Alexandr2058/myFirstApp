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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationdia.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu_Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private ListView listViewMenu;
    private Singlton singlton;
    TextView tvName;
    String txtName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.buttonNV);
        tvName = findViewById(R.id.textView6);
        bottomNavigationView.setSelectedItemId(R.id.menu);

        singlton = Singlton.getInstance();

//      Навигационная панель
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.docoments:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.service:
                        startActivity(new Intent(getApplicationContext(),Services_Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.notif:
                        startActivity(new Intent(getApplicationContext(),Notification_Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu:
                        return true;
                }
                return false;
            }
        });

//      Передача данных из файла string, в массив и в LstView
        listViewMenu = findViewById(R.id.menuCategory);
        Resources resources = getResources();
        String[] numbers = resources.getStringArray(R.array.name_of_menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<> (this, R.layout.name_item_menu, R.id.userNameMenu, numbers);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.name_of_menu, android.R.layout.simple_list_item_1);
        listViewMenu.setAdapter(adapter);

//      Листнер для итемов
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Menu_Activity.this, (String)listViewMenu.getItemAtPosition(i), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Menu_Activity.this, Emergency.class);
                Intent intentD = new Intent(Menu_Activity.this, EnterData.class);
                Intent intentL = new Intent(Menu_Activity.this, LoginPage.class);
                if (((String) listViewMenu.getItemAtPosition(i)).equals("Ввести данні")){
                    startActivity(intentD);
                } else if (((String) listViewMenu.getItemAtPosition(i)).equals("Вийти")) {
                    startActivity(intentL);
                }
                else {
                    startActivity(intent);
                }
//                startActivity(intent);
            }
        });

//        Resources resources1 = getResources();
//        String [] images = resources1.getStringArray(R.array.image_array);
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<> (this, R.layout.name_item_menu, R.id.imageViewMenu, images);
//        listViewMenu.setAdapter(adapter1);
        //this, R.layout.name_item_menu, R.id.imageViewMenu, images


        txtName = singlton.getName();
        tvName.setText(txtName);
    }
}