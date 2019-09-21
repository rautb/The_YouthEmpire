package com.example.the_youthempire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Product extends AppCompatActivity {

    Button pro_btn1,pro_btn2,pro_btn3,pro_btn4,pro_btn5,pro_btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        pro_btn1=(Button)findViewById(R.id.pro_btn1);
        pro_btn2=(Button)findViewById(R.id.pro_btn2);
        pro_btn3=(Button)findViewById(R.id.pro_btn3);
        pro_btn4=(Button)findViewById(R.id.pro_btn4);
        pro_btn5=(Button)findViewById(R.id.pro_btn5);
        pro_btn6=(Button)findViewById(R.id.pro_btn6);

        pro_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Torch.class);
                startActivity(i);
            }
        });

        pro_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is=new Intent(getApplicationContext(),Headphone.class);
                startActivity(is);
            }
        });

        pro_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iq=new Intent(getApplicationContext(),Zebronics.class);
                startActivity(iq);
            }
        });

        pro_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Dublin_phone.class);
                startActivity(i);
            }
        });

        pro_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iw=new Intent(getApplicationContext(),Powerbank.class);
                startActivity(iw);
            }
        });

        pro_btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ie=new Intent(getApplicationContext(),BuyProduct.class);
                startActivity(ie);
            }
        });
    }
}
