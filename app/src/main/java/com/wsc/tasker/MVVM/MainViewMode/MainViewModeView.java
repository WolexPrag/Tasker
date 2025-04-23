package com.wsc.tasker.MVVM.MainViewMode;


import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wsc.tasker.MVVM.IView;
import com.wsc.tasker.event.IListTaskOperationsHandler;

public class MainViewModeView implements IView {
    private MainViewModeTaskAdapter taskAdapter;
    public MainViewModeView(RecyclerView taskRecycler, Context context,IListTaskOperationsHandler taskHandler){
        initAdapter(taskRecycler,context,taskHandler);
    }
    
    private void initAdapter(RecyclerView taskRecycler, Context context, IListTaskOperationsHandler handler){
        if(taskAdapter == null){
            taskAdapter = MainViewModeTaskAdapter.getInstance(handler);
        }
        taskRecycler.setLayoutManager(new LinearLayoutManager(context));
        taskRecycler.setAdapter(taskAdapter);
    }
    @Override
    public void Display() {
        
    }



}
