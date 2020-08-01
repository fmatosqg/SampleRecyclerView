package com.example.samplerecyclerview

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.BindingAdapter

@BindingAdapter("layoutState")
fun setLayoutState(motionLayout: MotionLayout, layoutState: Boolean?) {
    if (layoutState == null)  return
    if (layoutState) {
        motionLayout.transitionToEnd()
    } else {
        motionLayout.transitionToStart()
    }
}