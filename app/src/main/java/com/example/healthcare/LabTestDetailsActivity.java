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

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvpackageName,tvtotalCost;
    EditText edDetails;
    Button btnAddtocart,btnback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvpackageName=findViewById(R.id.textViewCartLabTestDetailsPackageName);
        tvtotalCost=findViewById(R.id.textViewLabDetailsTotalCost);
        edDetails=findViewById(R.id.editTextLabTestDetails);
        btnAddtocart=findViewById(R.id.btnLabtestDetailsAddToCart);
        btnback=findViewById(R.id.btnLabTestDetailsAcBack);


        edDetails.setKeyListener(null);

        Intent intent=getIntent();
        tvpackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvtotalCost.setText("Total Cost : "+intent.getStringExtra("text3")+"/-");

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));

            }
        });

        btnAddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences =getSharedPreferences("share prefrence", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=tvpackageName.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(db.cheekCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Allready Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addToCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(), "Record Inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));


                }




            }
        });

    }
}