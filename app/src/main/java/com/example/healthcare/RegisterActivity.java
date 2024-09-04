package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edPassword,edUserName,edEmail,edConform;
    Button btn;
    TextView tv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edConform=findViewById(R.id.BookAppointmentFees);
        edEmail=findViewById(R.id.BookAppointment_Address);
        edPassword=findViewById(R.id.BookAppointmentContactNumber);
        edUserName=findViewById(R.id.BookAppointment_fullName);

        tv=findViewById(R.id.RegisteruserRegisteration);
        btn=findViewById(R.id.BookAppointmentBtn);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username=edUserName.getText().toString();
                String Email=edEmail.getText().toString();
                String password=edPassword.getText().toString();
                String ConformPassword=edConform.getText().toString();
                Database db=new Database(getApplicationContext(),"haelthcare",null,1);
                if (Username.length()==0 || Email.length()==0 || password.length()==0 || ConformPassword.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please Fill the All Details",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.compareTo(ConformPassword)==0)
                    {
                        if (isValid(password))

                        {
                            db.register(Username,Email,password);

                            Toast.makeText(getApplicationContext(),"Record Submited",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Password Must be contain at leat  8 charecter  having letter (A to Z) digit (1 to 9) and special symbol",Toast.LENGTH_LONG).show();
                        }



                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Password did not match",Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });



    }
    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if (passwordhere.length()<8){
            return false;

        }
        else {
            for (int p=0;p<passwordhere.length();p++){
                if (Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for (int r=0;r<passwordhere.length();r++){
                if (Character.isLetter(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for (int s=0;s<passwordhere.length();s++){
                if (Character.isLetter(passwordhere.charAt(s))){
                    f3=1;
                }
            }
            if (f1==1 && f2==1 && f3==1)
                return true;
            return false;

        }

    }
}