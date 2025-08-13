using System;
using System.Collections.Generic;
using System.Linq;
using Tasker.InputSystem;
using UniRx;
using UniRx.Triggers;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.InputSystem;
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
        private readonly CompositeDisposable _disposables = new();
        
        private MainInputAction _inputActions;
        private TaskViewHolder _prefabViewHolder;
        private ITaskAdapterData _data;

        public void Init(ITaskAdapterData data, TaskViewHolder prefabViewHolder)
        {   
            _prefabViewHolder = prefabViewHolder;
            _scrollRect.OnBeginDragAsObservable().Subscribe(_ => _scroll.Value = true);
            _scrollRect.OnEndDragAsObservable().Subscribe(_ => _scroll.Value = false);
            
            SetTaskAdapterData(data);
            InitInputAction();
            DisplayTasks();
        }
        public void SelectHolder(Task task)
        {
            _viewHolders[task].Select();
        }
        public void UnSelectHolder(Task task)
        {
            _viewHolders[task].UnSelect();
        }
        public void SetTaskAdapterData(ITaskAdapterData data)
        {
            _data = data;
            data.AddTask.Subscribe(_ => AddTask(_.task, _.index));
            data.RemoveTask.Subscribe(task => RemoveTask(task));
        }
        private void InitInputAction()
        {
            _inputActions = new MainInputAction();
            _inputActions.UI.Enable();
            Observable.FromEvent<InputAction.CallbackContext>(
                handler => _inputActions.UI.Tap.performed += handler,
                handler => _inputActions.UI.Tap.performed -= handler
                ).Subscribe(ctx => DetectShortClick(ctx)).AddTo(_disposables);
            Observable.FromEvent<InputAction.CallbackContext>(
                handler => _inputActions.UI.SlowTap.performed += handler,
                handler => _inputActions.UI.SlowTap.performed -= handler
                ).Subscribe(ctx => DetectLongClick(ctx)).AddTo(_disposables);
        }
        private void DetectShortClick(InputAction.CallbackContext context)
        {
            Task task = GetTask(context);
            if (task == null)
            {
                return; 
            }
            _shortClick.Execute(task);
        }
        private void DetectLongClick(InputAction.CallbackContext context)
        {
            Task task = GetTask(context);
            if (task == null)
            {
                return; 
            }
            _longClick.Execute(task);
        }
        private Task GetTask(InputAction.CallbackContext context)
        {
            TaskViewHolder viewHolder= CheckClickedHolder(context, _inputActions.UI.Point.ReadValue<Vector2>());
            if(viewHolder == null) {
                return null;
            }
            return viewHolder.Task;
        }
        private TaskViewHolder CheckClickedHolder(InputAction.CallbackContext context,Vector2 clickPos)
        {
            var eventData = new PointerEventData(EventSystem.current) { position = clickPos };
            var results = new List<RaycastResult>();
            EventSystem.current.RaycastAll(eventData, results);

            foreach (var result in results)
            {
                var current = result.gameObject.transform;
                while (current != null)
                {
                    if (current.TryGetComponent<TaskViewHolder>(out var viewHolder))
                    {
                        return viewHolder;
                    }
                    current = current.parent;
                }
            }
            return null;
        }
        private void DisplayTasks()
        {
            if (_viewHolders.Count > 0)
            {
                ClearViewHolders();
            }
            for (int i = 0; i < _data.Tasks.Count; i++)
            {
                DisplayTask(i);
            }
        }
        private void ClearViewHolders()
        {
            foreach (TaskViewHolder viewHolder in _viewHolders.Values)
            {
                Delete(viewHolder);
            }
            _viewHolders.Clear();
        }
        private void DisplayTask(int index)
        {
            Task task = _data.Tasks[index];
            TaskViewHolder holder = Create(_content);

            holder.transform.SetSiblingIndex(index);
            holder.Bind(task);

            holder.IsCompleteChanged.Where(v => _scroll.Value == false)
                .Subscribe(value => _isCompleteToggled.OnNext((task, value)));

            _viewHolders.Add(task, holder);
        }
        private void RemoveTask(Task task)
        {
            Delete(_viewHolders[task]);
            _viewHolders.Remove(task);
        }
        private void Delete(TaskViewHolder holder)
        {
            Destroy(holder);
        }
        private void AddTask(Task task, int index)
        {
            DisplayTask(index);
        }
        private TaskViewHolder Create(Transform parent)
        {
            return Instantiate(_prefabViewHolder, parent);
        }
        private void OnDestroy()
        {
            _disposables.Dispose();
        }
    }
}
