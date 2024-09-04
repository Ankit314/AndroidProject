package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {


    private String [][] packages= {
            {"Packages1: Full Body Cheekup", "", "", "", "999"},
            {"Package2: Blood Glauses fasting ","","","","299"},
            {"Package3: COVID-19 Antibody-IgG","","","","899"},
            {"Package4:Thyroad Cheek","","","","499"},
            {"Package5:Immunity Cheek","","","","699"}


    };

    private String [] packagedetails={

            "Vitamin B12 Cyanocobalamin\n" +
                    "( Weakness & Brain Health )\n"+
                    "Vitamin D3 Total 25-Hydroxy\n" +
                    "( Bone Health, Immunity & Tiredness )\n"+
                    "Iron Profile - Anaemia\n" +
                    "( Hair, Skin & Anxiety )\n"+
                    "HbA1c (Glycated hemoglobin)\n" +
                    "( Higher HbA1c, Greater diabetes complications )\n"+
                    "Lipid/Cholesterol Profile\n" +
                    "( Heart health, Artries Clogging/Hardening )\n"+
                    "CBC - Complete Haemogram\n" +
                    "( Blood Cancer, Infection,Hb & Anaemia )\n"+
                    "lapid Profile\n"+

                    "Liver Function Test \n",
            "Blood Glauses fasting",


            "COVID-19 Antibody-IgG\n"+"Real-Time RT PCR for COVID-19\n"+"TrueNat Test for COVID-19\n"+"CBNAAT Test for COVID-19 \n",

            "Thyroid Profile - T3 T4 TSH\n" +
                    "( Weight Gain/Loss, Mood Swings )\n"+"Lipid/Cholesterol Profile\n" +
                    "( Heart health, Artries Clogging/Hardening )\n"+"LFT - Liver Function Tests with GGT\n" +
                    "( Jaundice, Weight Loss, Abdominal Pain, Nausea )\n"+"KFT - Kidney Function Tests - RFT\n" +
                    "( Kidney Diseases, Frequent Urination )\n"+"CBC - Complete Haemogram\n" +
                    "( Blood Cancer, Infection,Hb & Anaemia )",


            "Complete Hemogram \n"+

                    "Vitamin B12 Cyanocobalamin\n" +
                    "( Weakness & Brain Health )\n"+
                    "Iron Studies \n"+
                    "Kidny Function Test \n"+
                    "Calcium, Phosphorus & ALP\n" +
                    "( Healthy Bones & Teeth Profile )\n"+
                    "Liver Function test\n"+
                    "Lipid Profile"



    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoTocart,btnback;
    ListView listView;
//    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);



        listView=findViewById(R.id.ListViewLabTestCart);

        btnback=findViewById(R.id.btnLabTestBack);
        btnGoTocart=findViewById(R.id.btnLabTestGoToCart);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });

        list=new ArrayList();
        for (int i=0;i<packages.length;i++){

            item=new HashMap<String,String>();
            item.put("Line1",packages[i][0]);
            item.put("Line2",packages[i][1]);
            item.put("Line3",packages[i][2]);
            item.put("Line4",packages[i][3]);
            item.put("Line5","Total Cost"+packages[i][4]+"/-");
            list.add(item);


        }

        sa=new SimpleAdapter(this,list,R.layout.multi_lines,

                new String[] {"Line1","Line2","Line3","Line4","Line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent it= new Intent(LabTestActivity.this,LabTestDetailsActivity.class);

                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packagedetails[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);

            }
        });
        btnGoTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));

            }
        });
    }
}