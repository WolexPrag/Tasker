using System;
using System.Collections.Generic;
using Tasker.InputSystem;
using UniRx;
using UniRx.Triggers;
using UnityEngine;
using UnityEngine.UI;
namespace Tasker.Adapter
{
    public class TaskViewAdapter : MonoBehaviour
    {
        [SerializeField] private Dictionary<Task, TaskViewHolder> _viewHolders = new Dictionary<Task, TaskViewHolder>();
        [SerializeField] private Transform _content;
        [SerializeField] private ScrollRect _scrollRect;

        public IObservable<(Task task, bool isComplete)> IsCompleteToggled => _isCompleteToggled;
        public IObservable<Task> ShortClick => _shortClick;
        public IObservable<Task> LongClick => _longClick;
        public IObservable<bool> Scroll => _scroll;

        private Subject<(Task task, bool isComplete)> _isCompleteToggled;
        private readonly ReactiveCommand<Task> _shortClick = new();
        private readonly ReactiveCommand<Task> _longClick = new();
        private readonly ReactiveProperty<bool> _scroll = new();
        private MainInputAction _inputActions;
        private TaskViewHolder _prefabViewHolder;
        private ITaskAdapterData _data;

        public void Init(ITaskAdapterData data, TaskViewHolder prefabViewHolder)
        {
            
            _prefabViewHolder = prefabViewHolder;
            _data = data;
            InitInputAction();
            _scrollRect.OnBeginDragAsObservable().Subscribe(_ => _scroll.Value = true);
            _scrollRect.OnEndDragAsObservable().Subscribe(_ => _scroll.Value = false);
            data.AddTask.Subscribe(_ => AddTask(_.task, _.index));
            data.RemoveTask.Subscribe(task => RemoveTask(task));
            DisplayTasks();
        }
        private void InitInputAction()
        {
            _inputActions = new MainInputAction();
            _inputActions.UI.Enable();
        }
        private void DisplayTasks()
        {
            if (_viewHolders.Count > 0)
            {
                foreach (TaskViewHolder viewHolder in _viewHolders.Values)
                {
                    Delete(viewHolder);
                }
                _viewHolders.Clear();
            }
            for (int i = 0; i < _data.Tasks.Count; i++)
            {
                DisplayTask(i);
            }
        }
        private void DisplayTask(int index)
        {
            Task task = _data.Tasks[index];
            TaskViewHolder holder = Create(_content);

            holder.transform.SetSiblingIndex(index);
            holder.Init(_inputActions);
            holder.Bind(task);
            AddSubscribesOnHolder(holder, task);
            _viewHolders.Add(task, holder);
        }
        private void AddSubscribesOnHolder(TaskViewHolder holder, Task task)
        {
            holder.ShortClick.Where(v => _scroll.Value == false)
                .Subscribe(value => _shortClick.Execute(task));

            holder.LongClick.Where(v => _scroll.Value == false)
                .Subscribe(value => _longClick.Execute(task));

            holder.IsCompleteToggled.Where(v => _scroll.Value == false)
                .Subscribe(value => _isCompleteToggled.OnNext((task, value)));
        }
        public void AddTask(Task task, int index)
        {
            DisplayTask(index);
        }
        public void RemoveTask(Task task)
        {
            Delete(_viewHolders[task]);
            _viewHolders.Remove(task);
        }
        public TaskViewHolder Create(Transform parent)
        {
            return Instantiate(_prefabViewHolder, parent);
        }
        public void Delete(TaskViewHolder holder)
        {
            Destroy(holder);
        }
    }
}
