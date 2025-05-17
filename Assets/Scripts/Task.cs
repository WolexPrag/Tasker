using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Task
{
    public Task()
    {
        _creationDate = DateTime.Now;
        _status = new TaskStatus();
    }
    private DateTime _creationDate;
    protected string _name;
    protected string _description;
    protected TaskStatus _status;
    public DateTime CreationDate
    {
        get => _creationDate;
    }
    public string Name
    {
        get =>_name; 
        set => _name = value;
    }
    public string Description
    {
        get => _description;
        set => _description = value;

    }
    public TaskStatus Status
    {
        get => _status;
        set => _status = value;
    }
}
