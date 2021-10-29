package by.budanitskaya.l.quilixtest.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.budanitskaya.l.quilixtest.presentation.ui.settings.adapters.SettingsAdapter

class CustomItemTounchCallback : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
    0
) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val adapter = recyclerView.adapter as SettingsAdapter
        val from = viewHolder.adapterPosition
        val to = target.adapterPosition
        adapter.moveItem(from, to)
        adapter.notifyItemMoved(from, to)

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)

        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            viewHolder?.itemView?.alpha = 0.5f
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)

        viewHolder.itemView.alpha = 1.0f
    }
}


