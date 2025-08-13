using System;
using System.Collections.Generic;
using Tasker.Adapter;
using UniRx;
using UnityEngine;
namespace Tasker.MVVM
{
    [Serializable]
    public class TaskSpaceViewModel
    {
        [SerializeField] private TaskSpace _taskSpace;
        [SerializeField] private List<Task> _selected;
        public ITaskAdapterData adapterData => _taskSpace;
        private ReactiveCommand<Task> _OnSelect;
        private ReactiveCommand<Task> _OnUnSelect;
        public IObservable<Task> OnSelect => _OnSelect;
        public IObservable<Task> OnUnSelect => _OnUnSelect;

        public TaskSpaceViewModel(TaskSpace taskSpace)
        {
            _taskSpace = taskSpace;
            _selected = new List<Task>();
        }
        public void Select(Task task)
        {
            if (_selected.Contains(task))
            {
                _selected.Remove(task);
                _OnUnSelect.Execute(task);
            }
            else
            {
                _selected.Add(task);
                _OnSelect.Execute(task);
            }
        }
        public void CreateNew()
        {
            _taskSpace.Add(new Task());
        }
        public void RemoveTasks()
        {
            for (int i = 0; i < _selected.Count; i++)
            {
                _taskSpace.Remove(_selected[i]);
            }
        }
        public void CreateNewTest()
        {
            _taskSpace.Add(new Task($"Task{_taskSpace.Tasks.Count}", $"Description{_taskSpace.Tasks.Count}.Start", _taskSpace.Tasks.Count % 2 == 0));
        }
        public void ChangeTask()
        {

        }
    }
}