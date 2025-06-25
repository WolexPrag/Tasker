using TMPro;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using UniRx;
using UniRx.Triggers;
using System;
using UnityEngine.Events;

public class TaskViewHolder : MonoBehaviour
{
    public TextMeshProUGUI nameText;
    public TextMeshProUGUI descriptionText;
    public Toggle checkBox;
    [SerializeField] private Image _background;


    private CompositeDisposable _disposables = new CompositeDisposable();

    private Subject<Unit> _onShortClick = new Subject<Unit>();
    private Subject<Unit> _onLongClick = new Subject<Unit>();
    private Subject<bool> _onCheckBoxValueChanged = new Subject<bool>();

    public IObservable<Unit> OnShortClick => _onShortClick;
    public IObservable<Unit> OnLongClick => _onLongClick;
    public IObservable<bool> OnCheckBoxValueChanged =>_onCheckBoxValueChanged;
    
    [SerializeField] private float _longPressDuration = 0.5f;

   

    public void Awake()
    {
        InitSubscribtions();
    }
    public void Bind(Task task)
    {
        checkBox.isOn = task.Status.IsComplete;
        nameText.SetText(task.Name);
        descriptionText.SetText(task.Description);
        InitSubscribtions();
    }
    private void InitSubscribtions()
    {
        checkBox.onValueChanged.AddListener(value => _onCheckBoxValueChanged?.OnNext(value));
        _background.OnPointerDownAsObservable()
           .SelectMany(e =>
           {
               return Observable.Timer(System.TimeSpan.FromSeconds(_longPressDuration))
                   .TakeUntil(_background.OnPointerUpAsObservable()) 
                   .DoOnCompleted(() => _onShortClick.OnNext(Unit.Default)) 
                   .Take(1);
           })
           .Subscribe(_ => _onLongClick.OnNext(Unit.Default)) 
           .AddTo(_disposables);
    }
    private bool IsClickOnThisObject(PointerEventData eventData)
    {
        return eventData.pointerCurrentRaycast.gameObject != checkBox.gameObject;
    }
    public void UnSubscribeOnClicks()
    {
        _onCheckBoxValueChanged.OnCompleted();
        _onShortClick.OnCompleted();
        _onLongClick.OnCompleted();
    }
    public void Select()
    {
        transform.localScale = new Vector3(2, 2, 2);
    }
    public void UnSelect()
    {
        transform.localScale = new Vector3(1, 1, 1);
    }

    private void OnDestroy()
    {
        _disposables.Dispose();
        _onShortClick.OnCompleted();
        _onLongClick.OnCompleted();
        UnSelect();
    }

}


