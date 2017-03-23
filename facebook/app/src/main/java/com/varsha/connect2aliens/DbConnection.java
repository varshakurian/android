package com.varsha.connect2aliens;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by Varsha on 3/20/2017.
 */

public class DbConnection extends SQLiteOpenHelper {
    public DbConnection(Context context)
    {
        super(context, "fb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL("create table tbl_fbpage(username text,password text)");

        }
      catch ( SQLiteException e)
      {
          Log.d("error in creating table",e.getLocalizedMessage().toString());
      }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion)
    {
        db.execSQL("drop table if exists tbl_fbpage");
        onCreate(db);
    }
   public boolean insertedData(String uname,String pwd)
   {
       SQLiteDatabase db=this.getWritableDatabase();
       long result=0;
       ContentValues mycontent=new ContentValues();
       mycontent.put("username",uname);
       mycontent.put("password",pwd);
       try
       {
           result=db.insert("tbl_fbpage",null,mycontent);
       }
       catch (SQLiteException e)
       {
           Log.d("Insert Error",e.getLocalizedMessage().toString());
       }
       if (result==0)
       {
           return false;
       }
       else
       {
           return true;
       }
   }
    public Cursor getAllDatas(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from tbl_fbpage where username='"+username+"'",null);
        return res;
    }
}
