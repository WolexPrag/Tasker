package com.wsc.tasker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wsc.tasker.MVVM.IMainModeViewModel;
import com.wsc.tasker.MVVM.LocalMainModeViewModel;
import com.wsc.tasker.task.TaskAdapter;

public class MainFragmentTaskSpace extends Fragment {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private IMainModeViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.main_fragment_task_space, container, false);
    }
    public static MainFragmentTaskSpace init(IMainModeViewModel viewModel){
        MainFragmentTaskSpace ret = new MainFragmentTaskSpace();
        ret.viewModel = viewModel;
        ret.taskAdapter = TaskAdapter.getInstance(viewModel);
        return ret;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new LocalMainModeViewModel();
        recyclerView = view.findViewById(R.id.task_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(taskAdapter);
    }
}
