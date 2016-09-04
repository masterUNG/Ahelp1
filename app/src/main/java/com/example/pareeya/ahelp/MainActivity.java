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

    //Explicit
    private MyManage myManage;
    private EditText nameEditText, MyPhoneEditText;
    private String nameString, MyPhoneString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText7);
        MyPhoneEditText = (EditText) findViewById(R.id.editText8);



        myManage = new MyManage(this);

        Log.d("4SepV1", "Man==>");

        if (checkSQLite()) {
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
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

        //Check Space เช็คข้อมูล

        if (checkSpace()) {
            //Have Space ถ้ามีความว่างเปล่า
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "กรอกข้อมูลไม่ครบ", "กรุณากรอกข้อมูลใหม่ คะ");
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
                "เบอร์โทรศัพท์ = " + MyPhoneString + "\n");
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

        myManage.addValueToSQLite(nameString, MyPhoneString);
        startActivity(new Intent(MainActivity.this, ContentActivity.class));

    }

    private boolean checkSpace() {
        return nameString.equals("") ||
                MyPhoneString.equals("");


    }

}//Main Class