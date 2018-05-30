package com.tech.gigabyte.intermediatealertdiaglog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by GIGABYTE on 5/30/2017.
 *
 * My DATABASE FOR SAVING DATA
 */

class DBhelper
{
    private helper myhelper;
    DBhelper(Context context)
    {
        myhelper=new helper(context);
    }

    long insertintodb(String name, String phone, String dob)throws SQLException
    {
        SQLiteDatabase database=myhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(helper.COL_NAME,name);
        contentValues.put(helper.COL_PHONE,phone);
        contentValues.put(helper.COL_DOB,dob);
        return database.insert(helper.TABE_NAME,null,contentValues);
    }
    ArrayList<Eachrow> getalldetails() throws SQLException
    {
        ArrayList<Eachrow> arrayList=new ArrayList<Eachrow>();
        Eachrow eachrow;
        SQLiteDatabase database=this.myhelper.getReadableDatabase();
        String[]Columns={helper.COL_NAME, helper.COL_PHONE, helper.COL_DOB};
        Cursor cursor=database.query(helper.TABE_NAME,Columns,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex(helper.COL_NAME));
            String phone=cursor.getString(cursor.getColumnIndex(helper.COL_PHONE));
            String dob=cursor.getString(cursor.getColumnIndex(helper.COL_DOB));
            eachrow=new Eachrow(name,phone,dob);
            arrayList.add(eachrow);

        }
        return arrayList;
    }
    private static class helper extends SQLiteOpenHelper
    {
        private static final String DB_NAME="USERINFO.db";
        static final String TABE_NAME="USER_INFO";
        private static final int VERSION=1;
        private static final String COL_ID="ID";
        private static final String COL_NAME="NAME";
        private static final String COL_PHONE="PHONE";
        private static final String COL_DOB="DOB";

        helper(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        //Creating Table
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase)
        {
            String create_table="CREATE TABLE IF NOT EXISTS "+TABE_NAME+"("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_NAME+" NAME TEXT,"+COL_PHONE+" TEXT,"+COL_DOB+" TEXT);";
            sqLiteDatabase.execSQL(create_table);

        }

        // On Upgrading Database
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABE_NAME);
            onCreate(sqLiteDatabase);
        }
    }


}