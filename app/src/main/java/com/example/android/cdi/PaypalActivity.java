package com.example.android.cdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PaypalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        volver();
    }

    public void volver (){
        Intent intent = new Intent();
        setResult(2, intent);
        finish();
    }

}
