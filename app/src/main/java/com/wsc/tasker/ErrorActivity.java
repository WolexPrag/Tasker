package com.wsc.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Executable;

public class ErrorActivity extends AppCompatActivity{
    public static Exception error;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_main);
        TextView errorText = findViewById(R.id.error_text);
        Button returnButton = findViewById(R.id.return_button);
        if(error != null){
        errorText.setText("Error {[ " + error.getMessage()+" ]}");
        }
        returnButton.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

}
