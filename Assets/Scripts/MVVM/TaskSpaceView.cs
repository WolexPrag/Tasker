using System;
using System.Collections;
using System.Collections.Generic;
using Tasker.Adapter;
using UnityEngine;
using UniRx;
namespace Tasker.MVVM
{
    public class TaskSpaceView : MonoBehaviour
    {
        private TaskSpaceViewModel _viewModel;
        private TaskViewAdapter _adapter;
        [SerializeField] private TaskViewHolder _prefab;
        public void Init(TaskSpaceViewModel viewModel, TaskViewAdapter adapter)
        {
            _viewModel = viewModel;
            _adapter = adapter;
            adapter.Init(_viewModel.adapterData, _prefab);
            adapter.ShortClick.Subscribe(v => Debug.Log(v.Name));
            adapter.LongClick.Subscribe(v => Debug.Log(v.Description));
        }

        public void OnSelect(Task task)
        {
            _viewModel.Select(task);
        }
        public void OnCreateNew()
        {
            _viewModel.CreateNew();
        }
        public void OnRemove()
        {
            _viewModel.RemoveTasks();
        }
    }
}