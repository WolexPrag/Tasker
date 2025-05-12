using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class Bootstrap : MonoBehaviour
{
    [SerializeField] private View _view;
    [SerializeField] private ViewModel _viewModel;
    void Awake()
    {
        

        _viewModel = new ViewModel(GetTestData(),new JsonToFileStorageService());
        _view.Init(_viewModel);
    }
    public TaskSpace GetTestData()
    {
        TaskSpace ret = new TaskSpace();
        for (int i = 0; i < 100; i++)
        {
            ret.Add(new Task() {Name=$"Name {i}" , Description=$"Description {i}"});
        }
        return ret;
    }
    
}
