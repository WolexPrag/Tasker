package com.wsc.tasker;


import android.os.Bundle;
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

        recyclerView = findViewById(R.id.task_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("wo1"));
        tasks.add(new Task("wawa"));
        tasks.add(new Task("niwaw"));

        adapter = new TaskAdapter(tasks);
        recyclerView.setAdapter(adapter);

    }


}


