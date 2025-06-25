using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Scripts.Delegates.Item;
using System.Linq;
using System;
public class TaskSpace : ITasksListObserver
{
    protected List<Task> tasks = new List<Task>();
    public IReadOnlyList<Task> Tasks { get { return  tasks.AsReadOnly(); } }

    private string name;
    public String Name { get; set; }
    
    public event ItemAddedHandler OnItemAdded;
    public event ItemRemovedHandler OnItemRemoved;
    public event ItemReplacedHandler OnItemReplaced;
    public event ItemChangedHandler OnItemChanged;


    public void Add(Task task)
    {
        tasks.Add(task);
        OnItemAdded?.Invoke(tasks.Count - 1);
    }
    public void Remove(int pos)
    {
        tasks.RemoveAt(pos);
        OnItemRemoved?.Invoke(pos);
    }
    public void Replace(int fromPos,int toPos)
    {
        OnItemReplaced?.Invoke(fromPos,toPos);
        Task task = tasks[fromPos];
        tasks.RemoveAt(fromPos);
        tasks.Insert(toPos,task);

    }
    public void ChangeItem(int pos,Action<Task> action)
    {
        action?.Invoke(tasks[pos]);
        OnItemChanged?.Invoke(pos);
    }
}
