using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ViewModel 
{
    private IStorageService _storageService;
    private TaskSpace _taskSpace;
    public TaskSpace TaskSpace { get { return _taskSpace; } }
    public ViewModel(TaskSpace taskSpace,IStorageService storageService)
    {
        _storageService = storageService;
        _taskSpace = taskSpace;
    }
   

    public void Add(Task task)
    {
        _taskSpace.Add(task);
    }
    public void Remove(int pos)
    {
        _taskSpace.Remove(pos);
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
