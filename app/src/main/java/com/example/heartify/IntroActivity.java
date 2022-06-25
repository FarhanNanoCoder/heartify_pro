package com.example.heartify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class IntroActivity extends AppCompatActivity {

    Button nextButton;
    TextView title;
    TextView desc;
    ImageView imageView;
    int state =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Objects.requireNonNull(getSupportActionBar()).hide();

        nextButton = findViewById(R.id.intro_next_button);
        title = findViewById(R.id.intro_title);
        desc = findViewById(R.id.intro_desc);
        imageView = findViewById(R.id.intro_image);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state==0){
                    //heading to the next intro
                    state++;
                    title.setText(R.string.intro_title_2);
                    desc.setText(R.string.intro_desc_2);
                    imageView.setImageResource(R.drawable.ic_intro_2);
                }else if(state==1){
                    Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}