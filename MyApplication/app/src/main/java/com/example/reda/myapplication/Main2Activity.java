package com.example.reda.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private TextView txt;
    private Button button;
    private EditText etxt;
    private Button but2;
    private Intent i;
    private printMessage pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        i = getIntent();
        Toast.makeText(getApplicationContext(), "selected Item Name is " + i.getStringExtra("id_conversation"), Toast.LENGTH_LONG).show();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage s = new SendMessage(etxt, i.getStringExtra("id_conversation"));
                s.start();
            }
        });

        etxt = (EditText) findViewById(R.id.editText);
        txt = (TextView) findViewById(R.id.textView);
        txt.setMovementMethod(new ScrollingMovementMethod());


        pm = new printMessage(Main2Activity.this, txt, i.getStringExtra("id_conversation"));
        pm.start();
    }
    @Override
    public void onBackPressed() {
        pm.flag = 1;
        super.onBackPressed();
    }

}

