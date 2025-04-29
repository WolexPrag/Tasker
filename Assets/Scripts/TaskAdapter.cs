using Scripts.Delegates.Item;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;

public class TaskAdapter : MonoBehaviour
{
    [SerializeField] private TaskViewPool pool;
    [SerializeField] private List<TaskViewHolder> activeObject;
    [SerializeField] private List<Task> tasks;
    
    protected void Display(TaskViewHolder view,Task task)
    {
        
    }

    
}
