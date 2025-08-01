using System;
using System.Collections;
using System.Collections.Generic;
using UniRx;
using UnityEngine;

namespace Tasker
{
    [Serializable]
    public class Task
    {
        [SerializeField] private readonly ReactiveProperty<string> _name;
        [SerializeField] private readonly ReactiveProperty<string> _description;
        [SerializeField] private readonly ReactiveProperty<bool> _isComplete;

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
            _name = new ReactiveProperty<string>(name);
            _description = new ReactiveProperty<string>(description);
            _isComplete = new ReactiveProperty<bool>(isComplete);
        }
    }
}
