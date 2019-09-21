package com.example.the_youthempire;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class bank_transfer extends Fragment {

    Button bank;
    EditText acc_hol,acc_no,bank_name,branch,ifse;
    String bankurl="http://youthempire.tech/youthempire/bank_transfer.php";
    String login_phone="Null";
    String p,wp,wa;
    String points1="No";
    String amount1="Null";
    String q;
    ProgressDialog progressDialog;


    public bank_transfer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_bank_transfer, container, false);

        SharedPreferences b=getContext().getSharedPreferences(login_phone, Context.MODE_PRIVATE);
        p=b.getString("loginPhone","No");

        SharedPreferences c=getContext().getSharedPreferences(points1, Context.MODE_PRIVATE);
        wp=c.getString("points1","No");

        SharedPreferences d=getContext().getSharedPreferences(amount1, Context.MODE_PRIVATE);
        wa=d.getString("ammount1","No");

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(true);

        bank=(Button)view.findViewById(R.id.bank);
        acc_hol=(EditText)view.findViewById(R.id.acc_hol);
        acc_no=(EditText)view.findViewById(R.id.acc_no);
        bank_name=(EditText)view.findViewById(R.id.bank_name);
        branch=(EditText)view.findViewById(R.id.branch);
        ifse=(EditText)view.findViewById(R.id.ifse);


        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (acc_hol.getText().toString().trim().equals("")){
                    acc_hol.setError("Please Enter Bank Holder Name");
                    acc_hol.requestFocus();
                }else if (acc_no.getText().toString().trim().equals("")){
                    acc_no.setError("Please Enter Your Account Number");
                    acc_no.requestFocus();
                }else if (bank_name.getText().toString().trim().equals("")){
                    bank_name.setError("Please Enter Your Bank Name");
                    bank_name.requestFocus();
                }else if (branch.getText().toString().trim().equals("")){
                    branch.setError("Please Enter Your Branch Name");
                    branch.requestFocus();
                }else if (ifse.getText().toString().trim().equals("")){
                    ifse.setError("Please Enter Your IFSC Code");
                    ifse.requestFocus();
                }else {

                    q = "A/C holder name: " + acc_hol.getText().toString() +
                            "\n" + "A/C number: " + acc_no.getText().toString() + "\n"
                            + "Bank name: " + bank_name.getText().toString() + "\n" + "Branch: " + branch.getText().toString() +
                            "\n" + "IFSC Code: " + ifse.getText().toString();
                    progressDialog.show();
                    uploadBank ubn = new uploadBank();
                    new Thread(ubn).start();
                }
            }
        });

        return view;
    }
    class uploadBank implements Runnable{
        @Override
        public void run() {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, bankurl, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    Toast.makeText(getContext(),"Your Bank Details Update",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(),"Some error occurred!",Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("ph_no",p);
                    params.put("upbank",q);//name should be same as database
                    params.put("point_type",wp);
                    params.put("amount",wa);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }
    }
}
