package com.example.the_youthempire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class wallet extends AppCompatActivity {


    Spinner points;
    EditText with_bal;
    TextView tp1, ep1, rp1;
    String url = "http://youthempire.tech/youthempire/profile.php";
    // String img_uo_url="http://youthempire.tech/youthempire/img_up.php";
    List<List_Data_s> list_data_s;
    String login_phone = "Null";
    String p,r="select";
    ImageView bhim_upi,bank_up,paytm_up;
    int tp,rp,ep;
    String points1="No";
    String amount1="Null";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        bhim_upi=(ImageView) findViewById(R.id.bhim_upi);
        bank_up=(ImageView) findViewById(R.id.bank_up);
        paytm_up=(ImageView) findViewById(R.id.paytm_up);
        points = (Spinner) findViewById(R.id.points);
        tp1 = (TextView) findViewById(R.id.tp1);
        ep1 = (TextView) findViewById(R.id.ep1);
        rp1 = (TextView) findViewById(R.id.rp1);
        with_bal=(EditText)findViewById(R.id.with_bal);
        String[] dept = {"Select", "Task Points", "Reffer Points", "Extra Points"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, dept);
        points.setAdapter(adapter);

        points.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1: {
                        r="Task Points";
                       tp = Integer.parseInt(tp1.getText().toString().trim());
                        if (tp < 500) {
                            Toast.makeText(wallet.this, "Minimum withdrawal balance 500", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case 2: {
                        r="Reffer Points";
                        rp = Integer.parseInt(rp1.getText().toString().trim());
                        if (tp < 500) {
                            Toast.makeText(wallet.this, "Minimum withdrawal balance 500", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case 3: {
                        r="Extra Points";
                        ep = Integer.parseInt(ep1.getText().toString().trim());
                        if (tp < 500) {
                            Toast.makeText(wallet.this, "Minimum withdrawal balance 500", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SharedPreferences b = getApplicationContext().getSharedPreferences(login_phone, Context.MODE_PRIVATE);
        p = b.getString("loginPhone", "No");
        list_data_s = new ArrayList<>();

        wallett wl = new wallett();
        new Thread(wl).start();

        bhim_upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (with_bal.getText().toString().equals(""))
                {
                    Toast.makeText(wallet.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }else {
                    int w=Integer.parseInt(with_bal.getText().toString().trim());
                    switch (r) {
                        case "Task Points":
                            if (w > tp) {
                                Toast.makeText(wallet.this, "Withdrawal amount should be equal or less then your Task point", Toast.LENGTH_LONG).show();
                            } else {
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bhim()).commit();

                                SharedPreferences sp = getSharedPreferences(points1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("points1", "Task Points");
                                editor.apply();

                                SharedPreferences sp2 = getSharedPreferences(amount1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = sp2.edit();
                                editor2.putString("ammount1", with_bal.getText().toString().trim());
                                editor2.apply();

                            }
                            break;
                        case "Reffer Points":
                            if (w > rp) {
                                Toast.makeText(wallet.this, "Withdrawal amount should be equal or less then your Task point", Toast.LENGTH_LONG).show();
                            } else {
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bhim()).commit();

                                SharedPreferences sp = getSharedPreferences(points1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("points1", "Reffer Points");
                                editor.apply();

                                SharedPreferences sp2 = getSharedPreferences(amount1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = sp2.edit();
                                editor2.putString("ammount1", with_bal.getText().toString().trim());
                                editor2.apply();

                            }
                            break;
                        case "Extra Points":
                            if (w > ep) {
                                Toast.makeText(wallet.this, "Withdrawal amount should be equal or less then your Task point", Toast.LENGTH_LONG).show();
                            } else {
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bhim()).commit();

                                SharedPreferences sp = getSharedPreferences(points1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("points1", "Extra Points");
                                editor.apply();

                                SharedPreferences sp2 = getSharedPreferences(amount1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = sp2.edit();
                                editor2.putString("ammount1", with_bal.getText().toString().trim());
                                editor2.apply();
                            }
                            break;

                    }
                }
            }
        });
        bank_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (with_bal.getText().toString().equals(""))
                {
                    Toast.makeText(wallet.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }else {
                    int w=Integer.parseInt(with_bal.getText().toString().trim());
                switch (r) {
                    case "Task Points":
                        if (w > tp) {
                            Toast.makeText(wallet.this, "Withdrawal amount should be equal or less then your Task point", Toast.LENGTH_LONG).show();
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bank_transfer()).commit();

                            SharedPreferences sp = getSharedPreferences(points1, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("points1", "Task Points");
                            editor.apply();

                            SharedPreferences sp2 = getSharedPreferences(amount1, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor2 = sp2.edit();
                            editor2.putString("ammount1", with_bal.getText().toString().trim());
                            editor2.apply();
                        }
                        break;
                    case "Reffer Points":
                        if (w > rp) {
                            Toast.makeText(wallet.this, "Withdrawal amount should be equal or less then your Task point", Toast.LENGTH_LONG).show();
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bank_transfer()).commit();
                            SharedPreferences sp = getSharedPreferences(points1, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("points1", "Reffer Points");
                            editor.apply();

                            SharedPreferences sp2 = getSharedPreferences(amount1, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor2 = sp2.edit();
                            editor2.putString("ammount1", with_bal.getText().toString().trim());
                            editor2.apply();
                        }
                        break;
                    case "Extra Points":
                        if (w > ep) {
                            Toast.makeText(wallet.this, "Withdrawal amount should be equal or less then your Task point", Toast.LENGTH_LONG).show();
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new bank_transfer()).commit();
                            SharedPreferences sp = getSharedPreferences(points1, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("points1", "Extra Points");
                            editor.apply();

                            SharedPreferences sp2 = getSharedPreferences(amount1, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor2 = sp2.edit();
                            editor2.putString("ammount1", with_bal.getText().toString().trim());
                            editor2.apply();
                        }
                        break;
                }
                }
            }
        });
        paytm_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (with_bal.getText().toString().equals(""))
                {
                    Toast.makeText(wallet.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }else {
                    int w = Integer.parseInt(with_bal.getText().toString().trim());

                    switch (r) {
                        case "Task Points":
                            if (w > tp) {
                                Toast.makeText(wallet.this, "Withdrawal amount should be equal or less then your Task point", Toast.LENGTH_LONG).show();
                            } else {
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new paytm()).commit();

                                SharedPreferences sp = getSharedPreferences(points1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("points1", "Task Points");
                                editor.apply();

                                SharedPreferences sp2 = getSharedPreferences(amount1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = sp2.edit();
                                editor2.putString("ammount1", with_bal.getText().toString().trim());
                                editor2.apply();
                            }
                            break;
                        case "Reffer Points":
                            if (w > rp) {
                                Toast.makeText(wallet.this, "Withdrawal amount should be equal or less then your Task point", Toast.LENGTH_LONG).show();
                            } else {
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new paytm()).commit();
                                SharedPreferences sp = getSharedPreferences(points1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("points1", "Reffer Points");
                                editor.apply();

                                SharedPreferences sp2 = getSharedPreferences(amount1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = sp2.edit();
                                editor2.putString("ammount1", with_bal.getText().toString().trim());
                                editor2.apply();
                            }
                            break;
                        case "Extra Points":
                            if (w > ep) {
                                Toast.makeText(wallet.this, "Withdrawal amount should be equal or less then your Task point", Toast.LENGTH_LONG).show();
                            } else {
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new paytm()).commit();
                                SharedPreferences sp = getSharedPreferences(points1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("points1", "Extra Points");
                                editor.apply();

                                SharedPreferences sp2 = getSharedPreferences(amount1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = sp2.edit();
                                editor2.putString("ammount1", with_bal.getText().toString().trim());
                                editor2.apply();
                            }
                            break;

                    }
                }
            }
        });
    }

    class wallett implements Runnable{

        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        JSONArray array=jsonObject.getJSONArray("data");
                        for (int i=0; i<array.length(); i++ ){
                            JSONObject ob=array.getJSONObject(i);
                            final List_Data_s listDataa=new List_Data_s(ob.getString("task_point"),
                                    ob.getString("extra_point"),ob.getString("reffer_point"));
                            list_data_s.add(listDataa);
                            tp1.setText(listDataa.getTp11());
                            ep1.setText(listDataa.getEp11());
                            rp1.setText(listDataa.getRp11());

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(),"Some error occurred!",Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("php",p);  //name should be same as database
                    return params;

                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }


}