using System;
using UniRx;
using UnityEngine;

namespace Tasker
{
    [Serializable] 
    public class Task
    {

         private ReactiveProperty<string> _name = new();
        [SerializeField] private ReactiveProperty<string> _description = new();
        [SerializeField] private ReactiveProperty<bool> _isComplete = new();

        public IObservable<string> NameChanged => _name;
        public IObservable<string> DescriptionChanged => _description;
        public IObservable<bool> IsCompleteChanged => _isComplete;
        public string Name
        {
            get => _name.Value;
            set => _name.Value = value;
        }
        public string Description
        {
            get => _description.Value;
            set => _description.Value = value;
        }
        public bool IsComplete
        {
            get => _isComplete.Value;
            set => _isComplete.Value = value;
        }
        public Task(string name = "", string description = "", bool isComplete = false)
        {
            _name.Value = name;
            _description.Value = description;
            _isComplete.Value = isComplete;
        }
    }
}
