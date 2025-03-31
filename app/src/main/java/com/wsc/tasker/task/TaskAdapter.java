package com.wsc.tasker.task;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wsc.tasker.R;
import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.event.SimpleDisposableObserver;

import java.util.List;

@SuppressWarnings("unused")
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private Notifier.Subscriber<List<Task>> subscriberOnUpdateTasks;
    private Notifier.Subscriber<Integer> subscriberOnAddTask;
    private Notifier.Subscriber<Integer> subscriberOnRemoveTask;
    private Notifier.Subscriber<Integer> subscriberOnUpdateTask;

    public static TaskAdapter getInstance() {
        TaskAdapter ret = new TaskAdapter();
        ret.initSubscriber();
        return ret;
    }

    private void initSubscriber() {
        subscriberOnUpdateTasks = new Notifier.Subscriber<>(new SimpleDisposableObserver<List<Task>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Task> tasks) {
                notifyDataSetChanged();
            }
        });
        subscriberOnAddTask = new Notifier.Subscriber<>(new SimpleDisposableObserver<Integer>() {
            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Integer position) {
                notifyItemInserted(position);
            }

        });
        subscriberOnRemoveTask = new Notifier.Subscriber<>(new SimpleDisposableObserver<Integer>() {
            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Integer position) {
                notifyItemRemoved(position);
            }
        });
        subscriberOnUpdateTask = new Notifier.Subscriber<>(new SimpleDisposableObserver<Integer>() {
            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Integer position) {
                notifyItemChanged(position);
            }
        });
    }



    private Task getTask(int position) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_maket, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.text.setText(getTask(position).getName());
        holder.checkBox.setActivated(getTask(position).isComplete());

        holder.itemView.setOnClickListener(v -> {

        });
        holder.checkBox.setOnClickListener(v -> {

        });
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
