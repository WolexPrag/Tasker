using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class TaskViewPool : ObjectPool<TaskViewHolder>
{
    [SerializeField] private GameObject content;
    [SerializeField] private GameObject prefab;

    public TaskViewPool(int startCopacity) : base(startCopacity)
    {
    }
    protected override void ExpandPool()
    {
        Return(TaskViewHolder.Instantiate(prefab, content.transform).GetComponent<TaskViewHolder>());
    }
}
                                    