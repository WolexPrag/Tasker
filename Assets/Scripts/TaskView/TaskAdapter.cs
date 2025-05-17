using Scripts.Delegates.Item;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using UniRx;
using static UnityEditor.PlayerSettings;


public class TaskAdapter : MonoBehaviour
{
    [SerializeField] private TaskViewPool _pool;
    [SerializeField] private List<TaskViewHolder> _activeObject;
    private ITasksListObserver _tasksObserver;
    private List<int> _selected;
    public List<int> Selected
    {
        get { return _selected; }
    } 
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
        RefreshRange(0,tasksObserver.Tasks.Count);
    }
    protected void RefreshRange(int start,int end)
    {
        for (int pos = start; pos < end; pos++)
        {
            if (_activeObject.Count <= pos)
            {
                _activeObject.Add(_pool.Get());
                _activeObject[pos].OnLongClick.Subscribe(t => { LongClick(pos); });
                _activeObject[pos].OnShortClick.Subscribe(t => { ShortClick(pos); });
            }
            
            RefreshItem(pos);
        }
    }
    protected void RefreshItem(int pos)
    {
        _activeObject[pos].Bind(_tasksObserver.Tasks[pos]);
    }
    private void ShortClick(int pos)
    {
        if (_selected.Contains(pos))
        {
            Debug.Log("Open Aditional settings");
        }
        else
        {
            _activeObject[pos].Select();
            _selected.Add(pos);
        }
    }
    private void LongClick(int pos)
    {
        if (_selected.Contains(pos))
        {
            _activeObject[pos].UnSelect();
            _selected.Remove(pos);
        }
    }
    protected void AddItem(int pos)
    {
        _activeObject.Insert(pos, _pool.Get());
        _activeObject[pos].transform.SetSiblingIndex(pos);
        RefreshRange(pos,_tasksObserver.Tasks.Count);
    }
    protected void RemoveItem(int pos)
    {
        _pool.Return(_activeObject[pos]);
        _activeObject.RemoveAt(pos);
        RefreshRange(pos, _tasksObserver.Tasks.Count);
    }
    protected void ReplaceItem(int fromPos, int toPos)
    {
        TaskViewHolder replaceItem = _activeObject[fromPos];
        _activeObject.RemoveAt(fromPos);
        _activeObject.Insert(toPos, replaceItem);

        RefreshRange(fromPos, _tasksObserver.Tasks.Count);
    }
}
