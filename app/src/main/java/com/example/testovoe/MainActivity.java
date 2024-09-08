package com.example.testovoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AppCompatButton LoginBut;
    EditText Login_Edit;
    EditText Password_Edit;
    public static final String Logins_FileName= "com.example.testovoe.Logins";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginBut = findViewById(R.id.Button_log);
        Login_Edit = findViewById(R.id.Login);
        Password_Edit = findViewById(R.id.Password);



        LoginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Login = Login_Edit.getText().toString();
                String Password= Password_Edit.getText().toString();

                SharedPreferences perfs = getSharedPreferences(Logins_FileName, MODE_PRIVATE);
                String User_Password = perfs.getString(Login, null);


                if (User_Password!=null &&! TextUtils.isEmpty(Password_Edit.getText().toString())){

                    if(User_Password.equals(Password)){
                        Intent intent = new Intent(MainActivity.this, Films.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Авторизация прошла успешно",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Неверный пароль",Toast.LENGTH_LONG).show();
                    }

                }
                else{

                    SharedPreferences.Editor editor = getSharedPreferences(Logins_FileName, MODE_PRIVATE).edit();
                    editor.putString(Login,Password);
                    editor.commit();
                    Toast.makeText(MainActivity.this, "Зарегестрировано! Нажмите еще раз для входа",Toast.LENGTH_LONG).show();
                }

            }
        });





    }
}