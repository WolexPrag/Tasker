package com.wsc.tasker.task;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wsc.tasker.R;
import com.wsc.tasker.core.DateTeTime;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;
    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_maket, parent, false);
        return new TaskViewHolder(view);
    }

    private Task getTask(int position){
        return tasks.get(position);
    }
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.text.setText(getTask(position).getName());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView text;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_to_complete);
            text = itemView.findViewById(R.id.text_task);
        }
    }
}
