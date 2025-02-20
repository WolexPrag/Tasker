package com.wsc.tasker.task;
import android.util.Log;
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
import com.wsc.tasker.event.INotifier;
import com.wsc.tasker.event.ISubscriber;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;
    private INotifier<Task> clickOnTask;
    private INotifier<Task> updateStateTask;

    public TaskAdapter() {
        this.tasks = new ArrayList<>();
    }
    public TaskAdapter(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_maket, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.text.setText(tasks.get(position).getName());


        holder.itemView.setOnClickListener(v ->{
            Log.i("adapter click on task","cliked on item (" + tasks.get(position).getName() + " ; " + tasks.get(position).getName() + ")");
        });
    }
    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }
    public void addTask(Task task){
        tasks.add(task);
        notifyDataSetChanged();
    }
    public void removeTask(@NonNull Predicate<Task> condition){
        tasks.removeIf(condition);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }
    public void subscribeOnClickTask(ISubscriber<Task> subscriber){
        clickOnTask.subscribe(subscriber);
    }
    public void unsubscribeOnClickTask(ISubscriber<Task> subscriber){
        clickOnTask.subscribe(subscriber);
    }
    public void subscribeOnUpdateStateTask(ISubscriber<Task> subscriber){
        clickOnTask.subscribe(subscriber);
    }
    public void unsubscribeOnUpdateStateTask(ISubscriber<Task> subscriber){
        clickOnTask.subscribe(subscriber);
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
