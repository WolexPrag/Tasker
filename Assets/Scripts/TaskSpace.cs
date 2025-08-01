using System;
using System.Collections;
using System.Collections.Generic;
using Tasker.Adapter;
using UniRx;
using UnityEngine;
namespace Tasker
{
    [Serializable]
    public class TaskSpace : ITaskAdapterData
    {

        [SerializeField] private List<Task> _tasks;
        public List<Task> Tasks { get { return _tasks; } }

        private readonly Subject<(Task task, int index)> _AddTask = new();
        public IObservable<(Task task, int index)> AddTask => _AddTask;
        private readonly Subject<Task> _RemoveTask = new();
        public IObservable<Task> RemoveTask => _RemoveTask;
     
        public TaskSpace()
        {
            _tasks = new List<Task>();
        }
        public void Add(Task task)
        {
            _tasks.Add(task);
            _AddTask.OnNext((task, _tasks.Count - 1));
        }
        public void Remove(Task task)
        {
            _tasks.Remove(task);
            _RemoveTask.OnNext(task);
        }


    }
}