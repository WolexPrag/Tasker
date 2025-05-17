using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TaskStatus
{
    private bool _isComplete;
    public bool IsComplete
    {
        get { return _isComplete; }
        set { _isComplete = value; }
    }
}
