package com.wsc.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {

            recyclerView = findViewById(R.id.task_layout);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            List<Task> tasks = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                tasks.add(new Task("Task " + i));
            }
            adapter = new TaskAdapter(tasks);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            try{
            Intent intent = new Intent(MainActivity.this, ErrorActivity.class);
            ErrorActivity.error = e;
            startActivity(intent);
            }
            catch (Exception e2){
                System.out.println(e2);
            }
        }

    }


}


