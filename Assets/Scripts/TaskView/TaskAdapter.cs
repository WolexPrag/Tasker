using Scripts.Delegates.Item;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using UniRx;


public class TaskAdapter : MonoBehaviour
{
    [SerializeField] private TaskViewPool _pool;
    [SerializeField] private List<TaskViewHolder> _activeObject;
    private ITasksListObserver _tasksObserver;
    public event Action<int> OnClickItem;
    public TaskAdapter()
    {
        
    }
    public void SetObserver(ITasksListObserver tasksObserver)
    {
        if (_tasksObserver != null)
        {
            tasksObserver.OnItemAdded -= AddItem;
            tasksObserver.OnItemRemoved -= RemoveItem;
            tasksObserver.OnItemReplaced -= ReplaceItem;
            tasksObserver.OnItemChanged -= RefreshItem;
        }
        _tasksObserver = tasksObserver;
        tasksObserver.OnItemAdded += AddItem;
        tasksObserver.OnItemRemoved += RemoveItem;
        tasksObserver.OnItemReplaced += ReplaceItem;
        tasksObserver.OnItemChanged += RefreshItem;
        RefreshAllItems();
    }

    protected void RefreshAllItems()
    {
        for (int i = 0; i < _tasksObserver.Tasks.Count; i++)
        {
            if(_activeObject.Count <= i)
            {
                _activeObject.Add(_pool.Get());
            }
            RefreshItem(i);
        }   
    }
    protected void RefreshItem(int pos)
    {
        _activeObject[pos].Bind(_tasksObserver.Tasks[pos]);
    }
    protected void AddItem(int pos)
    {
        _activeObject.Insert(pos,_pool.Get());
        _activeObject[pos].transform.SetSiblingIndex(pos);
    }
    protected void RemoveItem(int pos)
    {
        _pool.Return(_activeObject[pos]);
        _activeObject.RemoveAt(pos);

    }
    protected void ReplaceItem(int fromPos,int toPos)
    {

        TaskViewHolder replaceItem = _activeObject[fromPos]; 
        _activeObject.RemoveAt(fromPos);
        _activeObject.Insert(toPos, replaceItem);

    }
}
