package com.example.example2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call 이벤트
        Button buttonCall = findViewById(R.id.btn_call);
        buttonCall.setOnClickListener(this);

        // Intent 이벤트
//        Button buttonIndent = findViewById(R.id.btn_intent);
//        buttonIndent.setOnClickListener(this);
    }


//    @Override
//    public void onClick(View v) {
//        // Call 이벤트
//        Toast.makeText(this, "Click!!", Toast.LENGTH_SHORT).show();
//
//        // Indent 이벤트
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_ALL_APPS);
//        startActivity(intent);
//    }

    // 명시적 Intent
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, CallActivity.class);
        intent.putExtra("intent-message", "jjunpro");
        startActivity(intent);
    }
}
