using System;
using System.Collections;
using System.Collections.Generic;
using UniRx;
using UnityEngine;

[SerializeField]
public class ViewModel 
{
    private IStorageService _storageService;
    private TaskSpace _taskSpace;
    public TaskSpace TaskSpace { get { return _taskSpace; } }
    private List<int> _selected;
    public ViewModel(TaskSpace taskSpace,IStorageService storageService)
    {
        _storageService = storageService;
        _taskSpace = taskSpace;
        _selected = new List<int>();
    }
    public void OnLongClick((int pos, Unit unit) value)
    {
        if (!_selected.Contains(value.pos)){
            _selected.Add(value.pos);
        }
        Debug.Log($"Item on {value.pos} pos was long clicked");
    }
    public void OnShortClick((int pos, Unit unit) value)
    {
        if (_selected.Contains(value.pos))
        {
            _selected.Remove(value.pos);
        }
        Debug.Log($"Item on {value.pos} pos was short clicked");
    }
    public void OnCheckBoxValueChanged((int pos, bool value) value)
    {
        Debug.Log($"Item on {value.pos} pos was changed on {value.value}");
    }
    public void OnClickAdd()
    {
        _taskSpace.Add(new Task() { Name="Empty Task",Description="The Description also empty" });
    }
    public void OnClickRemove()
    {
        for (int i = 0; i < _selected.Count; i++)
        {
            _taskSpace.Remove(_selected[i]);   
        }
    }
    public void Replace(int from, int to)
    {             
        _taskSpace.Replace(from, to);
    }
    public void Change(int pos, Action<Task> action)
    {
        _taskSpace.ChangeItem(pos, action);
    }
    public void Save()
    {
        if (_taskSpace == null)
        {
            return;
        }
        _storageService.Save(_taskSpace.Name,_taskSpace);
    }
    public void Load(string nameSpace)
    {
        _storageService.Load<TaskSpace>(nameSpace, t => { _taskSpace = t; });
    }
}
