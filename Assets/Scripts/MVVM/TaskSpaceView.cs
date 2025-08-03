using Tasker.Adapter;
using UniRx;
using UnityEngine;
using UnityEngine.UI;

namespace Tasker.MVVM
{
    public class TaskSpaceView : MonoBehaviour
    {
        private TaskSpaceViewModel _viewModel;
        private TaskViewAdapter _adapter;
        [SerializeField] private TaskViewHolder _prefab;
        [SerializeField] private Button add;
        [SerializeField] private Button remove;

        public void Init(TaskSpaceViewModel viewModel, TaskViewAdapter adapter)
        {
            _viewModel = viewModel;
            _adapter = adapter;
            adapter.Init(_viewModel.adapterData, _prefab);
            adapter.ShortClick.Subscribe(v => Debug.Log($"(ShortClick)Name: {v.Name} ("+(v.IsComplete==true?("IsComplete"):("In Process"))+")"));
            adapter.LongClick.Subscribe(v => Debug.Log($"(LongClick)Description: {v.Description}"));
            adapter.LongClick.Subscribe(OnSelect);
            add.onClick.AddListener(OnAdd);
            remove.onClick.AddListener(OnRemove);
        }

        private void OnSelect(Task task)
        {
            _viewModel.Select(task);
        }
        private void OnAdd()
        {
            _viewModel.CreateNewTest();
        }
        private void OnRemove()
        {
            _viewModel.RemoveTasks();
        }
    }
}