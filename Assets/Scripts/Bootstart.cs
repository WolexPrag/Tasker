using Tasker;
using Tasker.Adapter;
using Tasker.MVVM;
using UnityEngine;
public class Bootstart : MonoBehaviour
{
    [SerializeField] private TaskViewAdapter _adapter;
    [SerializeField] private TaskSpaceView _view;
    [SerializeField] private TaskSpaceViewModel _viewModel;
    [SerializeField] private Task test = new();
    private void Awake()
    {
        TaskSpace space = new TaskSpace();
        _viewModel = new TaskSpaceViewModel(space);
        _view.Init(_viewModel, _adapter);
    }
}
