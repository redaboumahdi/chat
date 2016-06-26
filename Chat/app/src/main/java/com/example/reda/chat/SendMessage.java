package com.example.reda.chat;

import android.widget.EditText;

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




public class SendMessage extends Thread {
    EditText etxt;


    SendMessage(EditText e){
        this.etxt = e;
    }

    public void run(){
        String login_url = "http://192.168.0.27/chatphp/send.php";
        try {
            String id_send = "2";
            String id_rec = "1";
            String message =  etxt.getText().toString();
            System.out.println(message);
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(id_send, "UTF-8") + "&" +  URLEncoder.encode("ID_REC", "UTF-8")+"=" + URLEncoder.encode(id_rec, "UTF-8")
                    +"&" + URLEncoder.encode("MESSAGE", "UTF-8")+"=" + URLEncoder.encode(message, "UTF-8");
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
            System.out.println(result);

            System.out.println("coucou");

        } catch( MalformedURLException e){
            e.printStackTrace();
        } catch ( IOException e){
            e.printStackTrace();

        }
    }
}
