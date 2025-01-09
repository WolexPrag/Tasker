package com.wsc.tasker;

public abstract class ViewModel {
    protected Model _model;
    public ViewModel(Model model){
        _model = model;
    }
}
