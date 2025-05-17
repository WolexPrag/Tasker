using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using UnityEngine.UI;

public class View : MonoBehaviour
{
    [SerializeField] private ViewModel _viewModel;
    [SerializeField] private TaskAdapter _taskAdapter;

    
    public void Init(ViewModel viewModel)
    {
        _viewModel = viewModel;
        _taskAdapter.SetObserver(viewModel.TaskSpace);
    }
    public Task GetEmptyTask()
    {
        return new Task() {Name="Empty Name",Description="Empty Description" };
    }
    public void ClickOnAdd()
    {
        _viewModel.Add(GetEmptyTask());
    }
    public void ClickOnRemove()
    {
        List<int> positions = _taskAdapter.Selected;
        positions.Sort();
        foreach (int pos in positions)
        {
            _viewModel.Remove(pos);
        }
    }
}
