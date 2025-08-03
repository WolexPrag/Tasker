using System;
using System.Collections.Generic;
using Tasker.Adapter;
using UnityEngine;
namespace Tasker.MVVM
{
    [Serializable]
    public class TaskSpaceViewModel
    {
        [SerializeField] private TaskSpace _taskSpace;
        [SerializeField] private List<Task> _selected;
        public ITaskAdapterData adapterData => _taskSpace;
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
            }
            else
            {
                _selected.Add(task);
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