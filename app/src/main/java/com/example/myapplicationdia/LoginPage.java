package com.example.myapplicationdia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText pass;
    TextView signIn, signUp, tvMenu;
    DataBaseHelper DB;
    String password = "";

    private Singlton singlton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        pass = findViewById(R.id.enterPassArea);
        signIn = findViewById(R.id.tvSignIn);
        signUp = findViewById(R.id.tvSignUp);
        DB = new DataBaseHelper(this);
        tvMenu = findViewById(R.id.textView6);

        singlton = Singlton.getInstance();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = pass.getText().toString();
                String name = "";
                String sName = "";
                String lName = "";
                String bDate = "";
                int innCust;

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginPage.this, "Заполніть поле пароль", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkPass = DB.checkPass(password);
                    if (checkPass == true){
                    Intent intent = new Intent(LoginPage.this, MainActivity.class);

                    name = DB.retCustName(password);
                    sName = DB.retCustSN(password);
                    lName = DB.retCustLN(password);
                    bDate = DB.retCustBD(password);
                    innCust = DB.retCustInn(password);

                    singlton.setName(name);
                    singlton.setSecondName(sName);
                    singlton.setLastName(lName);
                    singlton.setBornData(bDate);
                    singlton.setInn(innCust);

                    Intent intentMenu = new Intent(LoginPage.this, CustomerData.class);
                    intentMenu.putExtra("nameCust", name);
                    startActivity(intent);
                    }else {
                        Toast.makeText(LoginPage.this, "Зареєструйтесь", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, EnterData.class);
                startActivity(intent);
            }
        });
    }
}