package com.example.study_group;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void click_intro_login(View view) {
        startActivity( new Intent(this, com.example.study_group.login.class) );
        finish();
    }

    public void start_main(View view) {
        startActivity( new Intent(this, com.example.study_group.MainActivity.class) );
        finish();
    }
}