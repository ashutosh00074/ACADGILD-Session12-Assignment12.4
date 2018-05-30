package com.tech.gigabyte.intermediatealertdiaglog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView ls;
    DBhelper help;
    private static final int ADD = 1;
    private static final int REFRESH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //List View to show saved data
        ls = (ListView) findViewById(R.id.list_view);
        help = new DBhelper(this);
        ArrayList<Eachrow> getval = help.getalldetails();
        custom_listadapter adapter = new custom_listadapter(MainActivity.this, android.R.layout.simple_list_item_1, getval);
        ls.setAdapter(adapter);
    }

    @Override

    // Options Menu 1-->Add , 2-->Refresh .
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ADD, 0, "ADD");
        menu.add(0, REFRESH, 1, "REFRESH");
        return true;
    }

    @Override

    //On Options Item (Add, Refresh) Selection
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == ADD && item.getGroupId() == 0) {
            MyDialog myDialog = new MyDialog();
            myDialog.show(getFragmentManager(), "Custom Dialog");
        }
        if (item.getItemId() == REFRESH && item.getGroupId() == 0) {
            ArrayList<Eachrow> getval = help.getalldetails();
            custom_listadapter adapter2 = new custom_listadapter(MainActivity.this, android.R.layout.simple_list_item_1, getval);
            ls.setAdapter(adapter2);
        }
        return super.onOptionsItemSelected(item);
    }
}