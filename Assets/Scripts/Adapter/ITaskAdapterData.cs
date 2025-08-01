using System;
using System.Collections.Generic;
namespace Tasker.Adapter
{
    public interface ITaskAdapterData
    {
        List<Task> Tasks { get; }
        IObservable<(Task task, int index)> AddTask { get; }
        IObservable<Task> RemoveTask { get; }
    }
}