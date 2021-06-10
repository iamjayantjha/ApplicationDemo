package com.manipal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ContactInformationActivity extends AppCompatActivity {
    ImageView logo;
    private WebView mywebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information);
        logo = findViewById(R.id.logo);
        mywebView=(WebView) findViewById(R.id.contact);
        mywebView.setWebViewClient(new WebViewClient());
        mywebView.loadUrl("https://jaipur.manipal.edu/muj/contact-us.html");
        WebSettings webSettings=mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        logo.setOnClickListener(v->{
            Intent main = new Intent(ContactInformationActivity.this,MainActivity.class);
            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        ContactInformationActivity.this.finish();
        overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_top);
        if(mywebView.canGoBack()) {
            mywebView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }

    public class mywebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view,url,favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);
            return true;
        }
    }
}