package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edPassword,edUserName;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUserName=findViewById(R.id.userName);
        edPassword=findViewById(R.id.password);
        btn=findViewById(R.id.loginbutton);
        tv=findViewById(R.id.userRegister);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String Username=edUserName.getText().toString();
                String password=edPassword.getText().toString();
                Database db=new Database(getApplicationContext(),"haelthcare",null,1);
                if (Username.length()==0 || password.length()==0){

                    Toast.makeText(getApplicationContext(),"Please Fill All Details",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (db.login(Username,password)==1){
                        Toast.makeText(getApplicationContext(),"loginSucces",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences =getSharedPreferences("share prefrence", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("Username",Username);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                    }else {
                        Toast.makeText(getApplicationContext(),"invalid UserName and password ",Toast.LENGTH_SHORT).show();

                    }


                }


            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}