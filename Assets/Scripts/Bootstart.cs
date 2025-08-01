using System.Collections;
using System.Collections.Generic;
using Tasker;
using Tasker.Adapter;
using Tasker.MVVM;
using UnityEngine;
public class Bootstart : MonoBehaviour
{
    [SerializeField] private TaskViewAdapter _adapter;
    [SerializeField] private TaskSpaceView _view;
    [SerializeField] private TaskSpaceViewModel _viewModel;
    private void Awake()
    {
        TaskSpace space = new TaskSpace();
        space.Add(new Task("Wowowowowow1", "Wow1"));
        space.Add(new Task("Wowowowowow2", "Wow2"));
        space.Add(new Task("Wowowowowow3", "Wow3"));
        _viewModel = new TaskSpaceViewModel(space);
        _view.Init(_viewModel, _adapter);
    }
}
