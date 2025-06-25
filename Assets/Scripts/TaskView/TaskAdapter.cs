using Scripts.Delegates.Item;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using UniRx;
using static UnityEditor.PlayerSettings;


public class TaskAdapter : MonoBehaviour
{
    private ITasksListObserver _tasksObserver;
    private int tasksCount => _tasksObserver.Tasks.Count;


    [SerializeField] private List<TaskViewHolder> _activeObject;
    [SerializeField] private Transform _content;
    
    private Subject<(int pos, Unit value)> _onShortClick;
    private Subject<(int pos, Unit value)> _onLongClick;
    private Subject<(int pos, bool value)> _onCheckBoxValueChanged;
    public IObservable<(int pos, Unit value)> OnShortClick => _onShortClick;
    public IObservable<(int pos, Unit value)> OnLongClick => _onLongClick;
    public IObservable<(int pos, bool value)> OnCheckBoxValueChanged => _onCheckBoxValueChanged;

    [Serializable]
    public class ViewHolderFactory
    {
        [SerializeField] private TaskViewHolder _prefab;
        public ViewHolderFactory(TaskViewHolder prefab)
        {
            _prefab = prefab;
        }
        public TaskViewHolder GetViewHolder(Transform parent)
        {
            return Instantiate(_prefab, parent);
        }
        public void ReturnViewHolder(TaskViewHolder viewHolder)
        {
            Destroy(viewHolder.gameObject);
        }
    }
    [SerializeField] private TaskViewHolder _prefab;
    [SerializeField] private ViewHolderFactory _factory;

    private void Awake()
    {
        _factory = new ViewHolderFactory(_prefab);
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
        _onLongClick = new Subject<(int pos, Unit value)>();
        _onShortClick = new Subject<(int pos, Unit value)>();
        _onCheckBoxValueChanged = new Subject<(int pos, bool value)>();
        RefreshRange(0, tasksCount);
    }
    protected void RefreshRange(int start, int end)
    {
        for (int pos = start; pos < end; pos++)
        {
            if (_activeObject.Count < end)
            {
                _activeObject.Add(_factory.GetViewHolder(_content));
            }
            RefreshItem(pos);
        }
    }

    protected void RefreshItem(int pos)
    {
        _activeObject[pos].Bind(_tasksObserver.Tasks[pos]);
        _activeObject[pos].OnShortClick.Subscribe(value => _onShortClick.OnNext((pos, value)));
        _activeObject[pos].OnLongClick.Subscribe(value => _onLongClick.OnNext((pos, value)));
        _activeObject[pos].OnCheckBoxValueChanged.Subscribe(value => _onCheckBoxValueChanged.OnNext((pos, value)));
    }
    protected void AddItem(int pos)
    {
        RefreshRange(pos, tasksCount);
    }
    protected void RemoveItem(int pos)
    {
        RefreshRange(pos, tasksCount);
    }
    protected void ReplaceItem(int fromPos, int toPos)
    {
        RefreshRange((fromPos < toPos)?(fromPos):(toPos), tasksCount);
    }
}
