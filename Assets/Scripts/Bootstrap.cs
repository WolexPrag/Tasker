using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class Bootstrap : MonoBehaviour
{
    [SerializeField] private View _view;
    [SerializeField] private ViewModel _viewModel;
    void Awake()
    {
        List<string> a = new List<string>();

        _viewModel = new ViewModel(new TaskSpace(),new JsonToFileStorageService());
        _view.Init(_viewModel);
    }
    
}
