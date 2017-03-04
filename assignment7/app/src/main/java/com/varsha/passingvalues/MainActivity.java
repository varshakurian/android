package com.varsha.passingvalues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    Button btn;
    EditText edtx1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edtx1 = (EditText) findViewById(R.id.editText2);
                TextView tv1 = (TextView) findViewById(R.id.textView2);
                tv1.setText(edtx1.getText().toString());
            }
        });
    }
}