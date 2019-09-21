package com.example.the_youthempire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Payment extends AppCompatActivity {
    WebView wv;
    SwipeRefreshLayout refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        wv=(WebView)findViewById(R.id.wv);
        refresh=(SwipeRefreshLayout)findViewById(R.id.refresh);


        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                wv.reload();
                refresh.setRefreshing(true);
            }
        });

        wv.setWebViewClient(new WebViewClient()  {
            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view, url);
                refresh.setRefreshing(false);
            }

            //make call from webpage ....................
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                if (url.startsWith("tel:")){
                    Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(i);
                    return true;
                }
                return false;
            }



        });
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);
       // wv.loadUrl("http://youthempire.tech/youthempire/paytm/pgRedirect.php/");
        wv.loadUrl("http://youthempire.tech/youthempire/buy.php/");



    }
}