using System;
using Tasker.InputSystem;
using TMPro;
using UniRx;
using UnityEngine;
using UnityEngine.InputSystem;
using UnityEngine.UI;

namespace Tasker.Adapter
{
    public class TaskViewHolder : MonoBehaviour
    {
        [SerializeField] private Task _task;
        [Header("View")]
        [SerializeField] private Toggle _isComplete;
        [SerializeField] private TMP_Text _name;
        [SerializeField] private TMP_Text _description;
        [SerializeField] private Image _clickablePart;
        [SerializeField] private readonly Subject<bool> _isCompleteToggled = new();
        [SerializeField] private readonly ReactiveCommand _longClick = new();
        [SerializeField] private readonly ReactiveCommand _shortClick = new();

        private readonly CompositeDisposable _taskUpdateDisposables = new();
        private readonly CompositeDisposable _inputActionDisposables = new();

        public IObservable<Unit> ShortClick => _shortClick;
        public IObservable<Unit> LongClick => _longClick;
        public IObservable<bool> IsCompleteToggled => _isCompleteToggled;

        public void Init(MainInputAction inputAction)
        {
            SubscribeOnInputAction(inputAction);
        }
        public void Bind(Task task)
        {
            if (_task != null)
            {
                _taskUpdateDisposables.Dispose();
            }
            _task = task;
            SubscribeOnTaskUpdate(_task);
        }
        private void SubscribeOnInputAction(MainInputAction inputAction)
        {
            Observable.FromEvent<InputAction.CallbackContext>(
                handler => inputAction.UI.Tap.performed += handler,
                handler => inputAction.UI.Tap.performed -= handler
            ).Subscribe(ctx => _shortClick.Execute()).AddTo(_inputActionDisposables);
            Observable.FromEvent<InputAction.CallbackContext>(
                handler => inputAction.UI.SlowTap.performed += handler,
                handler => inputAction.UI.SlowTap.performed -= handler
            ).Subscribe(ctx => _longClick.Execute()).AddTo(_inputActionDisposables);
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
                .Subscribe(isComplete => _isComplete.isOn = isComplete)
                .AddTo(_taskUpdateDisposables);
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
