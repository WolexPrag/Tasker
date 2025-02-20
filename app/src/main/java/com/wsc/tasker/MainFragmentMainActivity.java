package com.wsc.tasker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wsc.tasker.MVVM.IMainModeViewModel;
import com.wsc.tasker.event.ISubscriber;
import com.wsc.tasker.event.LocalSingleSubscriber;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.observers.DisposableObserver;

public class MainFragmentMainActivity extends Fragment {


    public class Buttons {
        public Buttons(View rootView) {
            this.add = rootView.findViewById(R.id.add_button);
            this.select = rootView.findViewById(R.id.select_button);
            this.edit = rootView.findViewById(R.id.edit_button);
            this.filter = rootView.findViewById(R.id.filter_button);
            this.analyze = rootView.findViewById(R.id.analyze_button);
            this.other = rootView.findViewById(R.id.other_button);
        }

        Button add;
        Button select;
        Button edit;
        Button filter;
        Button analyze;
        Button other;
    }

    private Buttons buttons;

    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;

    private IMainModeViewModel viewModel;

    private ISubscriber<List<Task>> subscriberOnUpdateTasks;

    public static MainFragmentMainActivity newInstance(IMainModeViewModel viewModel){
        MainFragmentMainActivity ret = new MainFragmentMainActivity();
        ret.setViewModel(viewModel);
        return ret;
    }

    public void setViewModel(IMainModeViewModel viewModel){
        this.viewModel = viewModel;
    }

    public void Awake(Bundle savedInstanceState, View rootView) {
        buttons = new Buttons(rootView);

        taskAdapter = new TaskAdapter(new ArrayList<>(viewModel.getCopyTasks()));
        taskRecyclerView = rootView.findViewById(R.id.task_layout);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        taskRecyclerView.setAdapter(taskAdapter);

        subscriberOnUpdateTasks =new LocalSingleSubscriber<>(new DisposableObserver<List<Task>>() {
            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Task> tasks) {
                taskAdapter.setTasks(tasks);
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        viewModel.subscribeOnUpdateTasks(subscriberOnUpdateTasks);
        buttons.add.setOnClickListener(v->{
            viewModel.createNewTask();
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_main_activity, container, false);
        Awake(savedInstanceState, rootView);


        return rootView;
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();

    }




}
