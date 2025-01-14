package com.wsc.tasker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    private LinearLayout container;
    private Button addButton;
    private Button homeButton;
    private int c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.task_layout);
        addButton = findViewById(R.id.add);
        homeButton = findViewById(R.id.home_button);
        c = 1;
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTextView(String.valueOf(c));
            }});
        addButton.setOnClickListener(t->{c=c+1;});
    }
    public void addTextView(String text){
        TextView txtv = new TextView(this);
        txtv.setText(text);
        container.addView(txtv);

    }
}