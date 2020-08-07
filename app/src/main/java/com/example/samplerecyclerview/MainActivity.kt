package com.example.samplerecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        adapter = ItemAdapter()
        val pageSnapHelper = PagerSnapHelper()
        recyclerView.adapter = adapter
        pageSnapHelper.attachToRecyclerView(recyclerView)
        recyclerView.setItemViewCacheSize(0)

        adapter.submitList(
            listOf(
                RecyclerViewItem("Flower"),
                RecyclerViewItem("Bear"),
                RecyclerViewItem("Car"),
                RecyclerViewItem("House"),
                RecyclerViewItem("Sport"),
                RecyclerViewItem("Sport 2"),
                RecyclerViewItem("Sport 3"),
                RecyclerViewItem("Garden")
            )
        )

        button.setOnClickListener {
            adapter.toggleState()
        }
    }
}