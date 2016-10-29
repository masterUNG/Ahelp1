package com.example.pareeya.ahelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import static com.example.pareeya.ahelp.R.id.textView13;

/**
 * Created by pareeya on 10/29/2016.
 */

public class PhoneAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] nameStrings, phoneString;

    public PhoneAdapter(Context context, String[] nameStrings, String[] phoneString) {
        this.context = context;
        this.nameStrings = nameStrings;
        this.phoneString = phoneString;
    }

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.phone_listview, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.textView12);
        TextView textView1 = (TextView) view.findViewById(R.id.textView13);

        textView.setText(nameStrings[position]);
        textView.setText(phoneString[position]);




        return view;
    }
}//main class

