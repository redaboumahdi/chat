package com.example.reda.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    ListView ex;
    Button but3;
    private final String id="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MessageListener m = new MessageListener(MainActivity.this,txt,"2");
        m.start();
        ex = (ListView) findViewById(R.id.exp);
        ConversationUI c = new ConversationUI(ex, this);
        c.start();
        but3 = (Button) findViewById(R.id.button3);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);


            }
        });

        ex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick (AdapterView< ? > adapter, View view, int position, long arg){
              // TODO Auto-generated method stub
              TextView v = (TextView) view.findViewById(R.id.label);
              String id_conv = v.getText().toString();
                                          StringTokenizer stk = new StringTokenizer(id_conv);
                                          String res = stk.nextToken();
              Toast.makeText(getApplicationContext(), "selected Item Name is " + res, Toast.LENGTH_LONG).show();
              Intent intent = new Intent(MainActivity.this, Main2Activity.class);

              intent.putExtra("id_conversation",res);
              startActivity(intent);
          }
      }
        );
    }

}
