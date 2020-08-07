package com.example.samplerecyclerview

import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.DEBUG_SHOW_PATH
import androidx.constraintlayout.motion.widget.MotionLayout.DEBUG_SHOW_PROGRESS
import androidx.core.view.ViewCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * This ViewHolder is an addition to the [DataBindingAdapter]. From this, it receives a ViewDataBinding which
 * allows us to connect the data class T with the data-bindings defined in the item layout.
 */
class DataBindingViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val motionLayout: MotionLayout =
        binding.root.findViewById<MotionLayout>(R.id.root_item_recycler_view)
            .also {
                it.setTransitionDuration(1_000)
                it.setDebugMode(DEBUG_SHOW_PROGRESS or DEBUG_SHOW_PATH)
            }

    var lastPosition: Int = -1


    fun bind(item: T, position: Int, layoutState: Boolean) {

        if (position != lastPosition)
            Log.i(
                "OnBind",
                "Position=$position lastPosition=$lastPosition - $layoutState "
            )



        lastPosition = position

        setMotionLayoutState(layoutState)

        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }

    //    https://stackoverflow.com/questions/51929153/when-manually-set-progress-to-motionlayout-it-clear-all-constraints
    fun safeRunBlock(block: () -> Unit) {

        if (ViewCompat.isLaidOut(motionLayout)) {
            block()
        } else {
            motionLayout.post(block)
        }

    }

    fun setMotionLayoutState(currentState: Boolean) {


        val goalProgress =
            if (currentState) 1f
            else 0f

        safeRunBlock {
            startTransition(currentState)
        }

        if (motionLayout.progress != goalProgress) {

            val desiredState =
                if (currentState) motionLayout.startState
                else motionLayout.endState

            if (motionLayout.currentState != desiredState) {
                Log.i("Pprogress", "Desired doesn't match at position $lastPosition")
                safeRunBlock {
                    startTransition(currentState)
                }
            }
        }
    }

    fun startTransition(currentState: Boolean) {
        if (currentState) {
            motionLayout.transitionToStart()
        } else {
            motionLayout.transitionToEnd()
        }
    }

}
