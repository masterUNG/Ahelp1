package com.example.pareeya.ahelp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;
    private EditText nameEditText, MyPhoneEditText;
    private String nameSting,MyPhoneString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        nameEditText  = (EditText) findViewById(R.id.editText7);
        MyPhoneEditText = (EditText) findViewById(R.id.editText8);


        myManage = new MyManage(this);


    }//Main Method

    public void ClickSaveData (View view) {
        nameSting = nameEditText.getText().toString().trim();
        MyPhoneString = MyPhoneEditText.getText().toString().trim();

        //check space

        if (CheckSpace()) {
            //เมื่อไหร่ที่มีความว่างเปล่า
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"มีช่องว่าง","กรุณากรอกทุกช่อง คะ");
        }

    }//clicksave

    private boolean CheckSpace() {
        return nameSting.equals("") ||
                MyPhoneString.equals("");
    }

} //Main Class
