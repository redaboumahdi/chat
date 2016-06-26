package com.example.reda.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private Button button;
    private EditText etxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage s = new SendMessage(etxt);
                s.start();
            }
        });
        etxt = (EditText) findViewById(R.id.editText);
        txt = (TextView) findViewById(R.id.textView);
        MessageListener m = new MessageListener(MainActivity.this, txt);
        m.start();



    }
}
