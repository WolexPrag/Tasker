using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Task
{
    private DateTime _creationDate;
    private string _name;
    private string _description;
    public DateTime CreationDate
    {
        get
        {
            return _creationDate;
        }
    }
    public string Name
    {
        get
        { return _name; }
        set
        {
            _name = value;
        }
    }
    public string Description
    {
        get
        {
            return _description;
        }
        set
        {
            _description = value;
        }

    }
}
