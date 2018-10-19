package com.dzakdzaks.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import org.jetbrains.anko.ctx
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        recyclerView {
            layoutManager = LinearLayoutManager(ctx)
            adapter = RecyclerViewAdapter(ctx, items) {
                startActivity<DetailClubActivity>(
                        "name" to it.name,
                        "image" to it.image,
                        "desc" to it.desc)
            }
        }
    }

    private fun getData() {
        val name = resources.getStringArray(R.array.club_name_list)
        val img = resources.obtainTypedArray(R.array.club_image_list)
        val desc = resources.getStringArray(R.array.club_desc_list)
        items.clear()
        for (i in name.indices) {
            items.add(Item(name[i], img.getResourceId(i, 0), desc[i]))
        }
        img.recycle()
    }
}
