package com.example.heartify.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.heartify.R;
import com.example.heartify.models.Record;
import com.example.heartify.models.RecordList;

public class RecordDetailsActivity extends AppCompatActivity {
    TextView sysPressure,diaPressure,heartRate,timeMeasured,dateMeasured,comment;
    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_details);
        sysPressure = findViewById(R.id.activity_record_details_sys_pressure);
        diaPressure = findViewById(R.id.activity_record_details_dia_pressure);
        heartRate = findViewById(R.id.activity_record_details_heart_rate);
        timeMeasured = findViewById(R.id.activity_record_details_time_measured);
        dateMeasured = findViewById(R.id.activity_record_details_date_measured);
        comment= findViewById(R.id.activity_record_details_comment);
        backButton = findViewById(R.id.activity_record_details_back_button);

        Intent intent = getIntent();
        if(intent.hasExtra("index")){
            int index = intent.getIntExtra("index",-1);
            if(index>-1 && index< RecordList.getInstance().getCount()){
                Record record = RecordList.getInstance().getRecord(index);
                sysPressure.setText(String.valueOf(record.getSystolicPressure()));
                diaPressure.setText(String.valueOf(record.getDiastolicPressure()));
                heartRate.setText(String.valueOf(record.getHeartRate()));
                timeMeasured.setText(record.getTimeMeasured());
                dateMeasured.setText(record.getDateMeasured());
                comment.setText(String.valueOf(record.getComment()));
            }else{
                Toast.makeText(this, "Invalid operation request", Toast.LENGTH_SHORT).show();
            }
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}