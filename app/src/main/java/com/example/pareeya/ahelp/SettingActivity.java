package com.example.pareeya.ahelp;

import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class SettingActivity extends AppCompatActivity {

    //Explicit
    private TextView phone1TextView, phone2TextView, phone3TextView,
            phone4TextView, phone5TextView;
    private ImageView addPhone1ImageView, addPhone2ImageView,
            addPhone3ImageView, addPhone4ImageView, addPhone5ImageView;
    private RadioGroup radioGroup;
    private RadioButton phone1RadioButton, phone2RadioButton,
            phone3RadioButton, phone4RadioButton, phone5RadioButton;
    private ListView listView;
    private Button button;
    private String urlJSON = "http://swiftcodingthai.com/fai/get_User_kanyarat.php";
    private String[] nameStrings, phoneStrings;
    private String nameChooseString, phoneChooseString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Bind Widget
        bindWidget();

        //create ListView
        SynUser synUser = new SynUser(SettingActivity.this);
        synUser.execute(urlJSON);

    }//Main Method

    private class SynUser extends AsyncTask<String, Void, String> {



        private Context context;



        public SynUser(Context context) {
            this.context = context;
        }


        @Override
        protected String doInBackground(String... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(params[0]).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();




            } catch (Exception e) {
                Log.d("29octV1", "e doInBack ==>" + e.toString());
                return null;
            }


        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("29octV1", "JSON ==>" + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                nameStrings = new String[jsonArray.length()];
                phoneStrings = new String[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    nameStrings[i] = jsonObject.getString("Name");
                    phoneStrings[i] = jsonObject.getString("Phone");

                }//for

                PhoneAdapter phoneAdapter = new PhoneAdapter(context, nameStrings, phoneStrings);

                listView.setAdapter(phoneAdapter);

               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                       Toast.makeText(context, "คุณเลือก" + nameStrings[position],
                               Toast.LENGTH_SHORT).show();

                   }//onIntemClick
               });

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }//SynUser Class



    private void bindWidget() {
        phone1TextView = (TextView) findViewById(R.id.textView5);
        phone2TextView = (TextView) findViewById(R.id.textView6);
        phone3TextView = (TextView) findViewById(R.id.textView9);
        phone4TextView = (TextView) findViewById(R.id.textView10);
        phone5TextView = (TextView) findViewById(R.id.textView11);


        addPhone1ImageView = (ImageView) findViewById(R.id.imageView6);
        addPhone2ImageView = (ImageView) findViewById(R.id.imageView7);
        addPhone3ImageView = (ImageView) findViewById(R.id.imageView8);
        addPhone4ImageView = (ImageView) findViewById(R.id.imageView9);
        addPhone5ImageView = (ImageView) findViewById(R.id.imageView10);


        phone1RadioButton = (RadioButton) findViewById(R.id.radioButton6);
        phone2RadioButton = (RadioButton) findViewById(R.id.radioButton7);
        phone3RadioButton = (RadioButton) findViewById(R.id.radioButton8);
        phone4RadioButton = (RadioButton) findViewById(R.id.radioButton9);
        phone5RadioButton = (RadioButton) findViewById(R.id.radioButton10);

        listView = (ListView) findViewById(R.id.livfriend);
        radioGroup = (RadioGroup) findViewById(R.id.ragPhone);



    }//bindWidget

    public void clickSetting(View view) {
        //startActivity(new Intent(SettingActivity.this,HomeActivity.class));

    }//clickSetting
}//MAIN Class
