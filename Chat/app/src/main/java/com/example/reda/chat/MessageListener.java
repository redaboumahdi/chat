package com.example.reda.chat;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by reda on 26/06/16.
 */
public class MessageListener extends Thread {
    Context context;
    TextView txt;
    private final Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            String aResponse = (String) msg.obj;

            if ((null != aResponse)) {

                // ALERT MESSAGE
                txt.setText(aResponse);

            }


        }
    };


    MessageListener(Context c, TextView t){
        this.context = c;
        this.txt = t;
    }

    public void run(){
        String login_url = "http://192.168.0.27/chatphp/launch.php";
        try {

            while(true) {
                String id = "1";
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Message msg = Message.obtain(); // Creates an new Message instance
                msg.obj = result;
                msg.setTarget(this.handler); // Set the Handler
                msg.sendToTarget(); //Send the message
            }

        } catch( MalformedURLException e){
            e.printStackTrace();
        } catch ( IOException e){
            e.printStackTrace();

        }
    }



}
