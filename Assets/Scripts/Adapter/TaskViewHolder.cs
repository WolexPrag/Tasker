using System;
using Tasker.InputSystem;
using TMPro;
using UniRx;
using UnityEngine;
using UnityEngine.Events;
using UnityEngine.InputSystem;
using UnityEngine.UI;

namespace Tasker.Adapter
{
    public class TaskViewHolder : MonoBehaviour
    {
        [SerializeField] private Task _task;
        [Header("View")]
        [SerializeField] private Toggle _isCompleteToggle;
        [SerializeField] private TMP_Text _name;
        [SerializeField] private TMP_Text _description;
        [SerializeField] private Image _clickablePart;
        [SerializeField] private Color _colorClickablePart;
        [SerializeField] private readonly Subject<bool> _isCompleteChanged = new();
        private readonly CompositeDisposable _taskUpdateDisposables = new();

        public IObservable<bool> IsCompleteChanged => _isCompleteChanged;

        public Task Task
        {
            get { return _task; }
        }
        public void Bind(Task task)
        {
            if (_task != null)
            {
                _taskUpdateDisposables.Dispose();
            }
            _task = task;
            SubscribeOnTaskUpdate(_task);
            _isCompleteToggle.onValueChanged.AsObservable()
                .Subscribe(v => _isCompleteChanged.OnNext(v))
                .AddTo(_taskUpdateDisposables);
        }
        private void SubscribeOnTaskUpdate(Task task)
        {
            _task.NameChanged
                .Subscribe(newName => _name.text = newName)
                .AddTo(_taskUpdateDisposables);
            _task.DescriptionChanged
                .Subscribe(newDesc => _description.text = newDesc)
                .AddTo(_taskUpdateDisposables);
            _task.IsCompleteChanged
                .Subscribe(isComplete => _isCompleteToggle.isOn = isComplete)
                .AddTo(_taskUpdateDisposables);
        }
        public void Select()
        {
            if (_colorClickablePart == null)
            {
                _colorClickablePart = _clickablePart.color;
            }
            _clickablePart.color = Color.blue;
        }
        public void UnSelect()
        {
            _clickablePart.color = _colorClickablePart;
        }
        public void Show()
        {
            gameObject.SetActive(true);
        }
        public void Hide()
        {
            gameObject.SetActive(false);
        }
        private void OnDestroy()
        {
            _taskUpdateDisposables.Dispose();
        }


    }
}
