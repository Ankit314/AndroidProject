package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_detail1={
            {"Doctor Name:Dr.B.S. Gupta","Hospital Address: Jaipur, Rajasthan","Experince: 50+year","Mobile Number:+91-1412547000","  600"},
            {"Doctor Name:Dr.Anantha Subramaniam","Hospital Address: Chennai","Experince: 48+year","Mobile Number:+91-4428293333"," 700"},
            {"Doctor Name:Dr.Aditya Ravi Chinta","Hospital Address:Hyderabad","Experince: 44+year","Mobile Number: +91-4023606868"," 500"},
            {"Doctor Name:Dr.Aman Kumar","Hospital Address: Tamil Nadu ","Experince: 29+year","Mobile Number:+91-4428290200","  400"},
            {"Doctor Name:Dr.Amitav Mohanty","Hospital Address: Bhubaneswar","Experince: 20+year","Mobile Number:+91-6747150382","  600"}

    };
    private String[][] doctor_detail2={
            {"Doctor Name:Dr.Geeta Buryok","Hospital Address:Shalimar Bagh","Experince: 25+ Years","Mobile Number:9852263803","  600"},
            {"Doctor Name:Pooja Makhija","Hospital Address:Maharashtra ","Experince: 11+Years","Mobile Number:+91-9930992298"," 700"},
            {"Doctor Name:Lavleen Kaur ","Hospital Address:Chandigarh","Experince: 11+Years","Mobile Number:91-9870481482"," 500"},
            {"Doctor Name:Ryan Fernando","Hospital Address:Tamil Nadu ","Experince: 24+Years","Mobile Number:+91-9743430000","  400"},
            {"Doctor Name:Rujuta Diwaker","Hospital Address:Maharashtra ","Experince: 20+Years","Mobile Number:+91-8080824276","  600"}

    };
    private String[][] doctor_detail3={
            {"Doctor Name:Dr.Kartik Mandal","Hospital Address:  West Bengal","Experince: 44+years","Mobile Number:9852263803"," 600"},
            {"Doctor Name:Dr.Shashi Bhushan Gupta","Hospital Address: Madhya Pradesh","Experince: 12+years","Mobile Number:+919266665081","700"},
            {"Doctor Name:Dr.Anil Kohli","Hospital Address: Delhi","Experince: 46+years","Mobile Number:9852263803","500"},
            {"Doctor Name:Dr.Vani Hegde","Hospital Address: Bihar ","Experince: 32+years","Mobile Number:9852263803"," 400"},
            {"Doctor Name:Dr.Amar Ravjiani","Hospital Address:  Maharashtra","Experince: 31+years","Mobile Number:9852263803","600"}

    };
    private String[][] doctor_detail4={
            {"Doctor Name:Dr.Albinus Lakra","Hospital Address:Jamshedpur","Experince: 10+years ","Mobile Number:080-67506847"," 600"},
            {"Doctor Name:Dr.Amitabha Bandyopadhyay","Hospital Address: Jamshedpur","Experince: 40+years ","Mobile Number:080-67506847"," 700"},
            {"Doctor Name:Dr.Ashish Goyal","Hospital Address:Jaipur","Experince: 8+years","Mobile Number:080-67506845"," 500"},
            {"Doctor Name:Dr.Balasubramaniam Ramana","Hospital Address: Kolkata","Experince: 5+year","Mobile Number:080-67506852","  400"},
            {"Doctor Name:Dr.Debdoot Soren","Hospital Address: Jamshedpur","Experince: 10+years ","Mobile Number:080-67506847","  600"}

    };
    private String[][] doctor_detail5={
            {"Doctor Name:Dr. Anil Bhan","Hospital Address:Gurugram","Experince: 40+years","Mobile Number:9852263803"," 600"},
            {"Doctor Name:Dr. Suresh Joshi","Hospital Address: Mumbai","Experince:  39+years ","Mobile Number:9852263803"," 700"},
            {"Doctor Name:Dr. Prafulla Kerkar","Hospital Address: Mumbai","Experince: 31+years","Mobile Number:9852263803"," 500"},
            {"Doctor Name:Dr. Bharat Shivdasani","Hospital Address:  Mumbai","Experince: 23+year","Mobile Number:9852263803"," 400"},
            {"Doctor Name:Dr. Atul Limaye","Hospital Address:  US","Experince: 21+years","Mobile Number:9852263803"," 600"}

    };

    TextView tv;
    Button btn;
    String [][] doctor_detail={};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.TextViewCartLabTestTtitle);
        btn=findViewById(R.id.btnLabTestBack);

        Intent it=getIntent();
        String Title=it.getStringExtra("Title");
        tv.setText(Title);
        if (Title.compareTo("Family Physician")==0)
            doctor_detail=doctor_detail1;
        else
        if (Title.compareTo("Dietician")==0)
            doctor_detail=doctor_detail2;
        else
        if (Title.compareTo("Dentist")==0)
            doctor_detail=doctor_detail3;
        else
        if (Title.compareTo("Surgeon")==0)
            doctor_detail=doctor_detail4;
        else
            doctor_detail=doctor_detail5;



            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));

            }
        });
        list=new ArrayList();
        for (int i=0;i<doctor_detail.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",doctor_detail[i][0]);
            item.put("line2",doctor_detail[i][1]);
            item.put("line3",doctor_detail[i][2]);
            item.put("line4",doctor_detail[i][3]);
            item.put("line5","Cons Fees"+doctor_detail[i][4]+"/-");
            list.add(item);



        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst=findViewById(R.id.ListViewLabTestCart);
        lst.setAdapter(sa);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",Title);
                it.putExtra("text2",doctor_detail[i][0]);
                it.putExtra("text3",doctor_detail1[i][1]);
                it.putExtra("text4",doctor_detail2[i][3]);
                it.putExtra("text5",doctor_detail3[i][4]);

                startActivity(it);
            }
        });

    }
}