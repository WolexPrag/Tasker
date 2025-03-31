package com.wsc.tasker.MVVM.selectMode;

import com.wsc.tasker.MVVM.IModeViewModel;

import java.util.List;

public interface ISelectModeViewModel extends IModeViewModel {

    void addSelectPosition(Integer position);

    void addSelectPositionRange(List<Integer> positions);

    List<Integer> getPositionSelected();
}
