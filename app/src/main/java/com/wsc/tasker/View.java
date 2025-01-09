package com.wsc.tasker;

public abstract class View {
    protected ViewModel _viewModel;
    public void Init(ViewModel viewModel){
        _viewModel = viewModel;
    }

}
