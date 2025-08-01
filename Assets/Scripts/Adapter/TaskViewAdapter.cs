using System;
using System.Collections.Generic;
using UniRx;
using UnityEngine;
namespace Tasker.Adapter
{
    public class TaskViewAdapter : MonoBehaviour
    {
        [SerializeField] private Dictionary<Task, TaskViewHolder> _viewHolders;
        private TaskViewHolder _prefabViewHolder;
        [SerializeField] private Transform _content;
        private ITaskAdapterData _data;

        private ReactiveCommand<Task> _shortClick;
        private ReactiveCommand<Task> _longClick;
        private Subject<(Task task, bool isComplete)> _isCompleteToggled;
        public IObservable<Task> ShortClick => _shortClick;
        public IObservable<Task> LongClick => _longClick;
        public IObservable<(Task task, bool isComplete)> IsCompleteToggled => _isCompleteToggled;
        public void Init(ITaskAdapterData data, TaskViewHolder prefabViewHolder)
        {
            _prefabViewHolder = prefabViewHolder;
            _viewHolders = new Dictionary<Task, TaskViewHolder>();
            _data = data;
            data.AddTask.Subscribe(_ => AddTask(_.task, _.index));
            data.RemoveTask.Subscribe(task => RemoveTask(task));
            DisplayTasks(); 
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
            InitNewHolder(holder, task);
            _viewHolders.Add(task, holder);
        }
        private void InitNewHolder(TaskViewHolder holder, Task task)
        {
            holder.ShortClick.Subscribe(value => _shortClick.Execute(task));
            holder.LongClick.Subscribe(value => _longClick.Execute(task));
            holder.IsCompleteToggled.Subscribe(value => _isCompleteToggled.OnNext((task, value)));
            holder.Bind(task);
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
