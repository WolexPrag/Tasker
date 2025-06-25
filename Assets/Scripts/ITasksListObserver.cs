using Scripts.Delegates.Item;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public interface ITasksListObserver
{
    public IReadOnlyList<Task> Tasks { get; }
    public event ItemAddedHandler OnItemAdded;
    public event ItemRemovedHandler OnItemRemoved;
    public event ItemReplacedHandler OnItemReplaced;
    public event ItemChangedHandler OnItemChanged;
}
