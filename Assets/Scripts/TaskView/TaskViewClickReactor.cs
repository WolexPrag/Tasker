using UnityEngine;
using UnityEngine.EventSystems;
using UniRx;
using System;
using UnityEngine.UI;

public class TaskViewClickReactor : MonoBehaviour,
    IPointerDownHandler,
    IPointerUpHandler,
    IBeginDragHandler
{
    [SerializeField] private Toggle itemToggle;

    private const float LongPressDuration = 0.5f;

    private Subject<Unit> onClickSubject = new();
    private Subject<Unit> onLongClickSubject = new();
    private Subject<bool> onToggleSubject = new();

    public IObservable<Unit> OnItemClickAsObservable() => onClickSubject;
    public IObservable<Unit> OnItemLongClickAsObservable() => onLongClickSubject;
    public IObservable<bool> OnToggleAsObservable() => onToggleSubject;

    private bool isPressed = false;
    private IDisposable longPressSubscription;

    private void Awake()
    {
        if (itemToggle != null)
        {
            itemToggle.OnValueChangedAsObservable()
                .Subscribe(isOn =>
                {
                    onToggleSubject.OnNext(isOn);
                })
                .AddTo(this);
        }
    }

    public void OnPointerDown(PointerEventData eventData) { /* ... */ }
    public void OnPointerUp(PointerEventData eventData) { /* ... */ }
    public void OnBeginDrag(PointerEventData eventData) { /* ... */ }
    // Остальные методы такие же как выше
}