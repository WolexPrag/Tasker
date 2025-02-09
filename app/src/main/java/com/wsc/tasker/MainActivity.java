package com.wsc.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.wsc.tasker.MVVM.TasksMenuViewModel;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskAdapter;
import com.wsc.tasker.task.TaskSpace;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            List<Task> tasks = new ArrayList<>();
            for (int i = 0; i <= 100; i++) {
                tasks.add(new Task().setName("Task number: "+ i));
            }

            TaskSpace taskSpace = new TaskSpace(tasks);
            TasksMenuViewModel modelView = new TasksMenuViewModel(taskSpace);
        }
        catch (Exception e){
            ErrorActivity.error = e;
            Intent intent = new Intent(this, ErrorActivity.class);
            startActivity(intent);
        }


    }


}


