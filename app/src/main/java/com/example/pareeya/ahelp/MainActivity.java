package com.example.pareeya.ahelp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Explicit การประกาศตัวแปร
    private MyManage myManage;
    private EditText nameEditText, MyPhoneEditText,
            PasswordEditText, rePasswordEdiText;
    private String nameString, MyPhoneString,
    passwordString, rePasswordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText7);
        MyPhoneEditText = (EditText) findViewById(R.id.editText8);
        PasswordEditText = (EditText) findViewById(R.id.editText9);
        rePasswordEdiText = (EditText) findViewById(R.id.editText10);



        myManage = new MyManage(this);

        //Check SQLite ทำการเข้าหน้าล็อคอินแค่ครั้งเดียว

        Log.d("4SepV1", "Man==>");

        if (checkSQLite()) {
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }

    }//Main Method

    private boolean checkSQLite() {

        boolean result = false;

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);
        cursor.moveToFirst();

        if (cursor.getCount() !=0){
            result = true;
        }
        Log.d("4SepV1", "Result==>" + result);
        return result;


    }

    public void clickSaveData(View view) {

        nameString = nameEditText.getText().toString().trim();
        MyPhoneString = MyPhoneEditText.getText().toString().trim();
        passwordString = PasswordEditText.getText().toString().trim();
        rePasswordString = rePasswordEdiText.getText().toString().trim();

        Log.d("loctV1", "Pass ==>" + passwordString);
        Log.d("loctV1", "RePass ==>" + rePasswordString);


        //Check Space เช็คข้อมูล

        if (checkSpace()) {
            //Have Space ถ้ามีความว่างเปล่า
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "กรอกข้อมูลไม่ครบ", "กรุณากรอกข้อมูลใหม่ คะ");
        } else if (MyPhoneString.length() != 10) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "Phone False","กรุณากรอก หมายเลขโทรศัพท์ เพียงแค่ 10 หลัก");
        } else if (passwordString.length() !=4) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"Password False","กรุณากรอก Password ให้มีแค่ 4 หลัก");
        } else if (!passwordString.equals(rePasswordString)) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "Password False", "กรุณากรอก Password ให้เหมือนกัน");
        } else {
            confirmData();
        }


    }//clickSaveData
    //การตรวจสอบข้อมูลชื่อ-เบอร์โทร
    private void confirmData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.alert);
        builder.setTitle("โปรดตรวจสอบข้อมูล");
        builder.setMessage("ชื่อ-นามสกุล = " + nameString + "\n" +
                "เบอร์โทรศัพท์ = " + MyPhoneString + "\n" +
                "Password = " +passwordString);
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SaveSQLite();
                dialog.dismiss();

            }
        });
        builder.show();
    }

    private void SaveSQLite() {

        myManage.addValueToSQLite(nameString, MyPhoneString,passwordString);
        startActivity(new Intent(MainActivity.this, ContentActivity.class));
        finish();

    }

    private boolean checkSpace() {
        return nameString.equals("") ||
                MyPhoneString.equals("")||
                passwordString.equals("")||
                rePasswordString.equals("");


    }

}//Main Class