package com.example.the_youthempire;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class signup extends AppCompatActivity {

    RadioButton terms_conditions;
    TextView dialog_txt;
    Button back_to_login,register;
    RadioGroup rg;
    RadioButton m,fe;
    EditText fn,ph,addr,pin,email,landmark,pass,repass,refer_id;
    String gender="NA";
    String url1="http://youthempire.tech/youthempire/check_ph.php";
    String url2="http://youthempire.tech/youthempire/register.php";
    List<List_Data_cp> list_data_cp;
    String login_status="No";
    int f;
    String tac="NA";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        list_data_cp=new ArrayList<>();
        //initialize progress dialog
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(true);

        fn=(EditText)findViewById(R.id.fn);
        ph=(EditText)findViewById(R.id.ph);
        addr=(EditText)findViewById(R.id.addr);
       refer_id=(EditText)findViewById(R.id.refer_id);
        register=(Button)findViewById(R.id.register);
        pin=(EditText) findViewById(R.id.pin);
        email=(EditText) findViewById(R.id.email);
        m=(RadioButton) findViewById(R.id.m);
        fe=(RadioButton) findViewById(R.id.f);
        landmark=(EditText) findViewById(R.id.land);
        pass=(EditText) findViewById(R.id.pass);
        repass=(EditText) findViewById(R.id.repass);

        back_to_login=(Button)findViewById(R.id.back_to_login);
        terms_conditions=(RadioButton) findViewById(R.id.terms_conditions);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m.isChecked()){
                    gender="Male";
                }else if (fe.isChecked()){
                    gender="female";
                }
                if (terms_conditions.isChecked()){
                    tac="Yes";
                }
                if (fn.getText().toString().trim().equals("")){
                    fn.setError("Please Enter Your Name");
                    fn.requestFocus();
                }else if (ph.getText().toString().trim().equals("")){
                    ph.setError("Please Enter Your Phone Number");
                    ph.requestFocus();
                }else if (addr.getText().toString().trim().equals("")){
                    addr.setError("Please Enter Your Address");
                    addr.requestFocus();
                }else if (pin.getText().toString().trim().equals("")){
                    pin.setError("Please Enter Your Pin Code");
                    pin.requestFocus();
                }else if (email.getText().toString().trim().equals("")){
                    email.setError("Please Enter Your Email Address");
                    email.requestFocus();
                }else if(gender.equals("NA")){
                    Toast.makeText(signup.this, "Please Select Your Gender", Toast.LENGTH_SHORT).show();
                }else if (landmark.getText().toString().trim().equals("")){
                    landmark.setError("Please Enter a Landmark");
                    landmark.requestFocus();
                }else if (pass.getText().toString().trim().equals("")){
                    pass.setError("Please Enter a Password");
                    pass.requestFocus();
                }else if (!repass.getText().toString().equals(pass.getText().toString())){
                    repass.setError("Password not Matched!");
                    repass.requestFocus();
                }else if (refer_id.getText().toString().trim().equals("")){
                    refer_id.setError("Please Enter Reffer ID");
                    refer_id.requestFocus();
                }else if(tac.equals("NA")){
                    Toast.makeText(signup.this, "Please Accept Our terms and Conditions", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.show();
                    check_ph cp=new check_ph();
                    new Thread(cp).start();
                }
            }
        });




        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });

        /*terms_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(signup.this);

                builder.setTitle("Terms & Conditions");
                builder.setMessage("You accept all terms and conditions");

                builder.setNeutralButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog mdialog=builder.create();
                mdialog.show();
            }
        });*/
    }
    class check_ph implements Runnable{
        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject ob = array.getJSONObject(i);
                            List_Data_cp listDatacp = new List_Data_cp(
                                    ob.getString("ph_no"));
                            list_data_cp.add(listDatacp);
                            String[] PhNo=new String[array.length()];
                            PhNo[i]=listDatacp.getPhone();

                            if (PhNo[i].equals(ph.getText().toString().trim())){
                                f=1;
                            }else {
                                f=0;
                            }

                        }
                        if (f==1){
                            shoeDialog();
                            progressDialog.dismiss();
                        }else {
                           reg r=new reg();
                           new Thread(r).start();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "error occurred!", Toast.LENGTH_SHORT).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Some error occurred!",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            });
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);


        }
    }
    public void shoeDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Message");

        dialog.setMessage("This number is already registered!");

        dialog.setPositiveButton("Login", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {


                finish();

            }

        });

        dialog.setCancelable(true);

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }

        }).show();
    }
    class reg implements Runnable{
        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url2,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(getApplicationContext(),response+"Registered Successful!",Toast.LENGTH_LONG).show();

                            SharedPreferences sp=getSharedPreferences(login_status, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("loginStatus","Yes");
                            editor.apply();


                           progressDialog.dismiss();

                           Intent i=new Intent(getApplicationContext(),YouthEmpire.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(i);


                        }
                    }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Some error occurred!",Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                    progressDialog.dismiss();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("name",fn.getText().toString().trim());
                    params.put("ph_no",ph.getText().toString().trim());
                    params.put("landmark",landmark.getText().toString().trim());
                    params.put("address",addr.getText().toString().trim());
                    params.put("pin_no",pin.getText().toString().trim());
                    params.put("password",convertPassMD5(pass.getText().toString()));
                    params.put("email",email.getText().toString());
                    params.put("gen",gender);
                    params.put("ref_id",refer_id.getText().toString().trim());

                    return params;
                }
            };

            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }
    }

    public static String convertPassMD5(String pass){
        String password=null;
        MessageDigest mdEnc;
        try{
            mdEnc=MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(),0,pass.length());
            pass=new BigInteger(1,mdEnc.digest()).toString(16);
            while (pass.length()<32){
                pass="0" + pass;
            }
            password=pass;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

}