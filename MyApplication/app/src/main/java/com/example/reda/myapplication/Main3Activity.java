package com.example.reda.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private ListView ls;
    private Button button4;
    private EditText et;
    //userMessage u = (userMessage) getApplication();
    ArrayAdapter<String> adapter; //= u.amis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        userMessage u = (userMessage) getApplication();
        ls = (ListView) findViewById(R.id.exp1);
        String[] aResponse = u.amis;
        this.adapter = new ArrayAdapter<String>(this,R.layout.contact_listview,aResponse);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView< ? > adapter, View view, int position, long arg){
                // TODO Auto-generated method stub
                TextView v = (TextView) view.findViewById(R.id.label1);
                String res = v.getText().toString();
                Toast.makeText(getApplicationContext(), "selected Item Name is " + res, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                intent.putExtra("id_conversation",res);
                startActivity(intent);
            }
        });
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        et = (EditText) findViewById(R.id.editText2);
        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Main3Activity.this.adapter.getFilter().filter(cs);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }

}
