package com.example.samplerecyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * This ViewHolder is an addition to the [DataBindingAdapter]. From this, it receives a ViewDataBinding which
 * allows us to connect the data class T with the data-bindings defined in the item layout.
 */
class DataBindingViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T, layoutState: Boolean) {
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.layoutState, layoutState)
        binding.executePendingBindings()
    }
}
