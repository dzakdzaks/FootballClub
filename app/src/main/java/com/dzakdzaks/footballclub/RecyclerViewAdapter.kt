package com.dzakdzaks.footballclub

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import com.dzakdzaks.footballclub.R.id.team_badge
import com.dzakdzaks.footballclub.R.id.team_name
import org.jetbrains.anko.*

class RecyclerViewAdapter(private val context: Context, private val items: List<Item>, private val listener: (Item) -> Unit)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
            ViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }


    override fun getItemCount(): Int = items.size

    class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            verticalLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView() {
                    id = team_badge
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }
                textView {
                    id = team_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }
            }
        }
    }

    //layout container with android extensions experimental = true
    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        private val teamBadge: ImageView = containerView.find(team_badge)
        private val teamName: TextView = containerView.find(team_name)

        fun bindItem(items: Item, listener: (Item) -> Unit) {
            teamName.text = items.name
            Glide.with(containerView).load(items.image).into(teamBadge)
            containerView.setOnClickListener { listener(items) }
        }
    }
}
