package com.example.sopt_27_android.ProfileRV

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(val listener: ItemActionListener) : ItemTouchHelper.Callback(){

    // Drag 및 Swipe 이벤트의 방향을 지정
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    // 아이템이 Drag 되면 ItemTouchHelper는 onMove()를 호출함
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        listener.onItemMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    // 아이템이 Swipe 되면 ItemTouchHelper는 범위를 벗어날 때까지 애니메이션을 적용한 후 onSwiped()를 호출함
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwiped(viewHolder.adapterPosition)
    }

    // 아이템을 길게 누르면 Drag & Drop 작업을 시작해야 하는지를 반환
    override fun isLongPressDragEnabled(): Boolean = true

    // 스와이프하면 Swipe 작업을 시작해야 하는지를 반환
    override fun isItemViewSwipeEnabled(): Boolean = true
}