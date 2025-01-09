package com.wsc.tasker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         container = findViewById(R.id.upper_layout);

    }
    public void addTextView(String text){
        TextView txtv = new TextView(this);
        txtv.setText(text);
        container.addView(txtv);

    }
}