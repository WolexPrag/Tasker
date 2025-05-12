using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Scripts.Delegates.Item{
    public delegate void ItemChangedHandler(int pos);
    public delegate void ItemAddedHandler(int pos);
    public delegate void ItemRemovedHandler(int pos);
    public delegate void ItemReplacedHandler(int fromPos, int toPos);
}
