using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UniRx;
using UniRx.Triggers;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

namespace Tasker.Adapter
{
    public class TaskViewHolder : MonoBehaviour, IBeginDragHandler, IDragHandler, IEndDragHandler
    {
        [SerializeField] private Task _task;
        [SerializeField] private Toggle _isComplete;
        [SerializeField] private TMP_Text _name;
        [SerializeField] private TMP_Text _description;
        [SerializeField] private Image _clickablePart;
        [SerializeField] private bool _longClickTriggered = false;
        [SerializeField] private float _longPressThreshold = 0.5f;

        [SerializeField] private readonly Subject<bool> _isCompleteToggled = new();
        [SerializeField] private readonly ReactiveCommand _longClick = new();
        [SerializeField] private readonly ReactiveCommand _shortClick = new();
        [SerializeField] private readonly ReactiveProperty<Vector2> _drag = new();

        private readonly CompositeDisposable _disposables = new();

        public IObservable<Unit> ShortClick => _shortClick;
        public IObservable<Unit> LongClick => _longClick;
        public IObservable<bool> IsCompleteToggled => _isCompleteToggled;
        public void Awake()
        {
            AddPointerEvents();
            
            //TODO: Make check
        }
        public void Bind(Task task)
        {
            if (_task != null)
            {
                _disposables.Dispose();
            }
            _task = task;
            _name.text = task.Name;
            _isComplete.isOn = task.IsComplete;
            _description.text = task.Description;
            SubscribeOnTaskUpdate(task);
        }
        private void SubscribeOnTaskUpdate(Task task)
        {
            _task.NameChanged
                .Subscribe(newName => _name.text = newName)
                .AddTo(_disposables);
            _task.DescriptionChanged
                .Subscribe(newDesc => _description.text = newDesc)
                .AddTo(_disposables);
            _task.IsCompleteChanged
                .Subscribe(isComplete => _isComplete.isOn = isComplete)
                .AddTo(_disposables);
        }
        private void AddPointerEvents()
        {
            


        }
        public void OnBeginDrag(PointerEventData eventData)
        {

        }

        public void OnDrag(PointerEventData eventData)
        {

        }

        public void OnEndDrag(PointerEventData eventData)
        {
            
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
            _disposables.Dispose();
        }
    }
}
