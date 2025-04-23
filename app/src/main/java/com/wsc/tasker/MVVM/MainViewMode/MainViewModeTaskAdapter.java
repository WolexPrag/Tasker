package com.wsc.tasker.MVVM.MainViewMode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wsc.tasker.R;
import com.wsc.tasker.event.IListTaskOperationsHandler;
import com.wsc.tasker.event.ListOperationsSubscriber;
import com.wsc.tasker.task.Task;

import java.util.List;
import java.util.Map;


public class MainViewModeTaskAdapter extends RecyclerView.Adapter<MainViewModeTaskAdapter.TaskViewHolder> {
    private ListOperationsSubscriber subscriberTasks;
    private IListTaskOperationsHandler handler;
    private List<Task> tasks;

    public static MainViewModeTaskAdapter getInstance(IListTaskOperationsHandler handler) {
        MainViewModeTaskAdapter ret = new MainViewModeTaskAdapter();
        ret.initSubscriber();
        return ret;
    }


    private void subscribeOnListOperationsHandler(IListTaskOperationsHandler handler) {
        subscriberTasks.subscribe(handler);
    }

    private void updateData(int pos) {
        tasks.set(pos, handler.getCopyItem(pos));
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_maket, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.text.setText(getTask(position).getName());
        holder.checkBox.setActivated(getTask(position).isComplete());

        holder.itemView.setOnClickListener(v -> {
            clickOnItemView(position);
        });
        holder.checkBox.setOnClickListener(v -> {
            clickOnCheckBox(position);
        });
    }

    public void clickOnItemView(int pos) {

    }

    public void clickOnCheckBox(int pos) {

    }

    private Task getTask(int position) {
        return tasks.get(position);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    private void initSubscriber() {
        subscriberTasks = new ListOperationsSubscriber() {
            @Override
            public void onUpdate(List<Integer> positionsUpdate) {
                for (int i = 0; i < positionsUpdate.size(); i++) {
                    updateData(i);
                    notifyItemChanged(positionsUpdate.get(i));
                }
            }

            @Override
            public void onAdd(Integer positionAdd) {
                notifyItemInserted(positionAdd);
            }

            @Override
            public void onRemove(Integer positionRemove) {
                notifyItemRemoved(positionRemove);
            }

            @Override
            public void onMove(Map<Integer, Integer> positionFromTo) {
                for (Map.Entry<Integer, Integer> entry : positionFromTo.entrySet()) {
                    notifyItemMoved(entry.getKey(), entry.getValue());
                }
            }
        };
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