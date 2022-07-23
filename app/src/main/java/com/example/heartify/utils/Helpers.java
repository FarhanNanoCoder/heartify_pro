package com.example.heartify.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.heartify.models.Record;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Helpers {

    static  public  String getStatus(int systolicPressure, int diastolicPressure){
        if(systolicPressure<=120 && diastolicPressure<=80){
            return "normal";
        }else if(systolicPressure>120 && systolicPressure<130 && diastolicPressure<80){
            return "elevated";
        }else if(systolicPressure>130 && systolicPressure<140 && diastolicPressure>=80 && diastolicPressure<=89){
            return "high(stage 1)";
        }else if(systolicPressure>=140  && diastolicPressure>=90){
            return "high(stage 2)";
        }else{
            return "unusual";
        }
    }
    static public String getStatusColor(int systolicPressure,int diastolicPressure){
        String status = getStatus(systolicPressure, diastolicPressure);
        switch (status){
            case "normal":
                return "#00C897";
            case "elevated":
                return "#ECB390";
            case "high(stage 1)":
                return "#FFAB76";
            case "high(stage 2)":
                return "#FF6B6B";
            default:
                return "#78938A";
        }
    }
    static public List<Record> getRecordsFromDB(Context context){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("shared",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonString = sharedPreferences.getString("records","[]");
        Type type = new TypeToken<ArrayList<Record>>(){}.getType();
        return gson.fromJson(jsonString,type);
    }

    static public void setRecordsToDB(Context context,List<Record> records){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("shared",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(records);
        editor.putString("records",jsonString);
        editor.apply();
    }

}

