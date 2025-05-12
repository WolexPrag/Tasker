using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using UnityEngine.UI;

public class View : MonoBehaviour
{
    [SerializeField] private ViewModel _viewModel;
    [SerializeField] private TaskAdapter _taskAdapter;

    [SerializeField] private List<int> selected; 
    
    public void Init(ViewModel viewModel)
    {
        _viewModel = viewModel;
        _taskAdapter.SetObserver(viewModel.TaskSpace);
    }
    
}
