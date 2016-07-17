package com.example.reda.myapplication;

import android.app.Application;

import org.json.JSONObject;

/**
 * Created by reda on 02/07/16.
 */
public class userMessage extends Application {

    private JSONObject[] someVariable;
    public String[] ordre;
    public String[] last_message;
    public String[] amis = {"18","2","3","4","5","10","11","12","13","14","15","16"};
    public String[] url_photo;
    public String id = "1";
    public JSONObject nonLu;

    public JSONObject[] getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(JSONObject[] someVariable) {
        this.someVariable = someVariable;
    }

    public void setAmis(String[] a){
        this.amis = a;
    }
}