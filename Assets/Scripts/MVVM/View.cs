using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using UnityEngine.UI;
using UniRx;

public class View : MonoBehaviour
{
    [SerializeField] private ViewModel _viewModel;
    [SerializeField] private TaskAdapter _taskAdapter;
    [SerializeField] private Button _addButton;
    [SerializeField] private Button _removeButton;

    public void Init(ViewModel viewModel)
    {
        _viewModel = viewModel;
        _taskAdapter.SetObserver(viewModel.TaskSpace);
        _taskAdapter.OnLongClick.Subscribe(_viewModel.OnLongClick);
        _taskAdapter.OnShortClick.Subscribe(_viewModel.OnShortClick);
        _taskAdapter.OnCheckBoxValueChanged.Subscribe(_viewModel.OnCheckBoxValueChanged);
        _addButton.onClick.AddListener(_viewModel.OnClickAdd);
        _removeButton.onClick.AddListener(_viewModel.OnClickRemove);
    }
}
