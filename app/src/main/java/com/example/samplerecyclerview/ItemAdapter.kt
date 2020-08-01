package com.example.samplerecyclerview

import androidx.recyclerview.widget.DiffUtil

class ItemAdapter : DataBindingAdapter<RecyclerViewItem>(DiffCallback) {

    private var currentState = true
    
    override fun getItemViewType(position: Int) = R.layout.item_recycler_view

    override fun onBindViewHolder(holder: DataBindingViewHolder<RecyclerViewItem>, position: Int) {
        val item = getItem(position)
        holder.bind(item, currentState)
    }
    
    fun toggleState() {
        currentState = !currentState
        notifyDataSetChanged()
    }

    /**
     * The DiffCallback is used by the [DataBindingAdapter] to check which items are
     * completely new and which just changed its content. When defining the Boolean checks, be
     * as precise as possible to avoid strange behaviour of the RecyclerView items.
     */
    object DiffCallback : DiffUtil.ItemCallback<RecyclerViewItem>() {
        override fun areItemsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem) = false
        override fun areContentsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem) =
            false
    }
}