using TMPro;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using UniRx;
using UniRx.Triggers;
using System;
using UnityEngine.Events;

public class TaskViewHolder : MonoBehaviour, IPoolable
{
    public TextMeshProUGUI nameText;
    public TextMeshProUGUI descriptionText;
    public Toggle checkBox;
    [SerializeField] private Image _background;

    public UnityAction<bool> OnCheckBoxValueChanged;

    private Subject<Unit> _onShortClick = new Subject<Unit>();
    private Subject<Unit> _onLongClick = new Subject<Unit>();
    private CompositeDisposable _disposables = new CompositeDisposable();

    [SerializeField] private float _longPressDuration = 1f;
    public IObservable<Unit> OnShortClick => _onShortClick;
    public IObservable<Unit> OnLongClick => _onLongClick;
    

    private void Start()
    { }
    public void Bind(Task task)
    {
        checkBox.isOn = false;
        nameText.SetText(task.Name);
        descriptionText.SetText(task.Description);
        InitSubscribe();
    }
    private void InitSubscribe()
    {
        checkBox.onValueChanged.AddListener(value => OnCheckBoxValueChanged?.Invoke(value));
        _background.OnPointerDownAsObservable()
            .Where(e => IsClickOnThisObject(e))
            .SelectMany(e =>
                Observable.Timer(System.TimeSpan.FromSeconds(_longPressDuration))
                    .TakeUntil(Observable.Merge(
                        _background.OnPointerUpAsObservable(),
                        _background.OnPointerExitAsObservable()
                    ))
                    .Take(1)
            )
            .Subscribe(
                _ => _onLongClick.OnNext(Unit.Default),
                () => _onShortClick.OnNext(Unit.Default)
            )
            .AddTo(_disposables);
    }
    private bool IsClickOnThisObject(PointerEventData eventData)
    {
        return eventData.pointerEnter == gameObject ||
               eventData.pointerPress == gameObject;
    }
    public void OnSpawn()
    {
        gameObject.SetActive(true);
    }
    public void OnDespawn()
    {
        gameObject.SetActive(false);
    }

    private void OnDestroy()
    {
        _disposables.Dispose();
        _onShortClick.OnCompleted();
        _onLongClick.OnCompleted();
    }

}


