package com.varsha.connect2aliens;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {


    Button login, create;
    EditText user, pass;

    TextView txt1, txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.customactionbarlayout);
        View view=getSupportActionBar().getCustomView();
        login = (Button) findViewById(R.id.log);
        create = (Button) findViewById(R.id.create);
        user = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText1);
        txt1 = (TextView) findViewById(R.id.text);
        txt2 = (TextView) findViewById(R.id.text1);
        final DbConnection mydb = new DbConnection(this);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Cursor res = mydb.getAllDatas(user.getText().toString());

                if (res.getCount() == 0)
                {
                    txt1.setVisibility(View.VISIBLE);
                    txt1.setText("The email address that u have entered does not match any account..Sign up for another account");
                   // showAllData("error", "no data inserted");
                   // return;
                }
                StringBuffer str = new StringBuffer();
                while (res.moveToNext()) {
                    // str.append("Id:" + res.getString(0) + "/n");
                    // str.append("firstname:" + res.getString(1) + "/n");

                   // String name = user.getText().toString();
                    String pwd = pass.getText().toString();
                  //  String unames = res.getString(0);
                    String passs = res.getString(1);


                    if (pwd.equals(passs))
                    {
                        Intent i = new Intent(MainActivity.this, homepage.class);
                        startActivity(i);
                    }
                   // if (!name.equals(unames) && pwd.equals(passs))
                    //{
                       // txt1.setText("The email address that u have entered does not match any account..Sign up for another account");
                   // }
                    if (!pwd.equals(passs))
                    {
                        txt1.setVisibility(View.VISIBLE);
                        txt1.setText("The password that u have entered is incorrect..Forgetten password?");
                    }

                  //  if (!name.equals(unames) && !pwd.equals(passs))
                   // {
                   //     txt1.setText("The usename and password is incorrect please check");
                   // }
                }
               // showAllData("success", str.toString());
            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean ins = mydb.insertedData(user.getText().toString(), pass.getText().toString());
                if (ins == true)
                {
                    Toast.makeText(getApplicationContext(), "data inserted successfully", Toast.LENGTH_SHORT).show();
                }
                else if(ins == false)
                {
                    Toast.makeText(getApplicationContext(), "data not inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

   // public void showAllData(String title, String message) {
    //    AlertDialog.Builder alertMsg = new AlertDialog.Builder(this);
     //   alertMsg.setCancelable(true);
     //   alertMsg.setTitle(title);
      //  alertMsg.setMessage(message);
      //  alertMsg.show();

   // }
}





