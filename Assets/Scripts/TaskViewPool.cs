using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class TaskViewPool : ObjectPool<GameObject>
{
    [SerializeField] private GameObject content;
    [SerializeField] private GameObject prefab;

    public TaskViewPool(int startCopacity) : base(startCopacity)
    {
    }
    protected override void OnGet(GameObject value)
    {
        value.SetActive(true);
    }
    protected override void OnReturn(GameObject value)
    {
        value.SetActive(false);
    }

    protected override void ExpandPool()
    {
        Return(GameObject.Instantiate(prefab, content.transform));
    }
}
