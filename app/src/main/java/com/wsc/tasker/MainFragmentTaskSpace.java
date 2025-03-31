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

import com.wsc.tasker.MVVM.mainMode.IMainViewModeViewModel;
import com.wsc.tasker.MVVM.mainMode.LocalMainViewModeViewModel;
import com.wsc.tasker.task.TaskAdapter;

public class MainFragmentTaskSpace extends Fragment {
    public class Buttons {
        public Buttons(@NonNull View view) {
            select = view.findViewById(R.id.select_button);
            add = view.findViewById(R.id.add_button);
            edit = view.findViewById(R.id.edit_button);
            filter = view.findViewById(R.id.filter_button);
            analyze = view.findViewById(R.id.analyze_button);
            other = view.findViewById(R.id.other_button);
        }

        public Button select;
        public Button add;
        public Button edit;
        public Button filter;
        public Button analyze;
        public Button other;
    }

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private IMainViewModeViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.main_fragment_task_space, container, false);
    }

    public static MainFragmentTaskSpace getInstance(@NonNull IMainViewModeViewModel viewModel) {
        MainFragmentTaskSpace ret = new MainFragmentTaskSpace();
        ret.setViewModel(viewModel);
        ret.initTaskAdapter();
        return ret;
    }
    public void setViewModel(@NonNull IMainViewModeViewModel viewModel){
        this.viewModel = viewModel;
        if(taskAdapter!=null){
            taskAdapter.setViewModel(viewModel);
        }
    }
    private void initTaskAdapter(){
        this.taskAdapter = TaskAdapter.getInstance(viewModel);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new LocalMainViewModeViewModel();
        initRecycleView(view.findViewById(R.id.task_layout));
    }
    private void initRecycleView(@NonNull RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(taskAdapter);
    }

}
