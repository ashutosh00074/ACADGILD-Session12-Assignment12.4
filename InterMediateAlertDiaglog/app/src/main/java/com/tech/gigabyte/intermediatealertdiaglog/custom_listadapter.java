package com.tech.gigabyte.intermediatealertdiaglog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by GIGABYTE on 5/30/2017.
 */

class custom_listadapter extends ArrayAdapter<Eachrow>
{
    private LayoutInflater mlayoutInflater;
    private ArrayList<Eachrow> eachrows;

    custom_listadapter(Context context, int TextViewResourceId, ArrayList<Eachrow> eachrows)
    {
        super(context,TextViewResourceId,eachrows);
        this.eachrows=eachrows;
        mlayoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    public View getView(int position, View convertview, @NonNull ViewGroup parents)
    {

        convertview=mlayoutInflater.inflate(R.layout.each_row,null);
        Eachrow eachrow=eachrows.get(position);
        if(eachrow != null)
        {
            TextView name=(TextView)convertview.findViewById(R.id.textView_name);
            TextView phone=(TextView)convertview.findViewById(R.id.textView_phone);
            TextView dob=(TextView)convertview.findViewById(R.id.textView_dob);

            if(name!=null)
            {
                name.setText(eachrow.getTxtname());
            }
            if(phone != null)
            {
                phone.setText(eachrow.getTxtphone());
            }
            if(dob != null)
            {
                dob.setText(eachrow.getTxtdob());
            }
        }
        return convertview;
    }
}