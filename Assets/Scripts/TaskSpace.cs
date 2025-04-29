using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Scripts.Delegates.Item;
using System.Linq;
using System;
public class TaskSpace
{
    protected List<Task> tasks = new List<Task>();
    public List<Task> Tasks { get { return  tasks; } }
    
    
    public event ItemAddedHandler OnItemAdded;
    public event ItemRemovedHandler itemRemoved;
    public event ItemReplacedHandler itemReplaced;
    public event ItemChangedHandler itemChanged;


    public void Add(Task task)
    {
        tasks.Add(task);
        OnItemAdded?.Invoke(tasks.Count - 1);
    }
    public void Remove(int pos)
    {
        tasks.RemoveAt(pos);
        itemRemoved?.Invoke(pos);
    }
    public void Replace(int fromPos,int toPos)
    {
        itemReplaced?.Invoke(fromPos,toPos);
        Task task = tasks[fromPos];
        tasks.RemoveAt(fromPos);
        tasks.Insert(toPos,task);

    }
    public void ChangeItem(int pos,Action<Task> action)
    {
        action?.Invoke(tasks[pos]);
        itemChanged?.Invoke(pos);
    }
}
