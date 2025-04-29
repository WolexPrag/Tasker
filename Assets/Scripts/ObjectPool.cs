using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class ObjectPool<T>
{
    [SerializeField] private List<T> storage;
    public ObjectPool(int startCopacity)
    {
        storage = new List<T>(startCopacity);
        ExpandPool(startCopacity);
    }
    public T Get()
    {
        if (storage.Count < 1)
        {
            ExpandPool();
        }
        T ret = storage[GetLastId()];
        storage.RemoveAt(GetLastId());
        OnGet(ret);
        return ret;
    }
    public void Return(T value)
    {
        storage.Add(value);
        OnReturn(value);
    }
    private int GetLastId()
    {
        return storage.Count - 1;
    }
    protected void ExpandPool(int count)
    {
        for (int posCreate = GetLastId() + 1; posCreate <= GetLastId() + count; posCreate++)
        {
            ExpandPool();
        }
    }
    protected abstract void OnGet(T value);
    protected abstract void OnReturn(T value);
    protected abstract void ExpandPool();
}
