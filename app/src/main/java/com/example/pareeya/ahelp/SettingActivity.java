package com.example.pareeya.ahelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }//Main Method
    public void clickSetting (View view){
        startActivity(new Intent(SettingActivity.this,HomeActivity.class));

    }
}//MAIN Class
