package com.example.myapplicationdia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EnterData extends AppCompatActivity {

    Button buttonOk, buttonNo;
    EditText nameField, secondNameField, lastNameField, innField, passField;
    TextView bornDateField;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_data);

        buttonOk = findViewById(R.id.buttonOk);
        buttonNo = findViewById(R.id.buttonNo);
        nameField = findViewById(R.id.name);
        secondNameField = findViewById(R.id.secondName);
        lastNameField = findViewById(R.id.lastName);
        bornDateField = findViewById(R.id.bornDate);
        innField = findViewById(R.id.inn);
        passField = findViewById(R.id.pass);


        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerData customerData;
                try {
                    customerData = new CustomerData
                            (
                            -1,
                            lastNameField.getText().toString(),
                            nameField.getText().toString(),
                            secondNameField.getText().toString(),
                            bornDateField.getText().toString(),
                            Integer.parseInt(innField.getText().toString()),
                            Integer.parseInt(passField.getText().toString())
                            );
//                    Toast.makeText(EnterData.this, customerData.toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    Toast.makeText(EnterData.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                    customerData = new CustomerData(-1, null, null, null, null, 0, 0 );
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(EnterData.this);
                String pass = passField.getText().toString();
                Boolean checkPass = dataBaseHelper.checkPass(pass);
                if (checkPass == false) {
                    boolean success = dataBaseHelper.addOne(customerData);
                    Toast.makeText(EnterData.this, "Успешно = " + success, Toast.LENGTH_SHORT).show();
                    Log.d("MyLog", "yes");
                    Intent intent = new Intent(EnterData.this, LoginPage.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EnterData.this, "Пароль існує, введіть іншій ", Toast.LENGTH_SHORT).show();
                    Log.d("MyLog", "No");
                }
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EnterData.this, "Отмена", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EnterData.this, Menu_Activity.class);
                startActivity(intent);
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        bornDateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EnterData.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth + "/" + month + "/" + year;
                bornDateField.setText(date);
            }
        };

    }
}