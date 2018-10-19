package com.dzakdzaks.footballclub

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class DetailClubActivity : AppCompatActivity() {

    var name = ""
    var img = 0
    var desc = ""
    lateinit var imgClub: ImageView
    lateinit var tvName: TextView
    lateinit var tvDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            gravity = Gravity.CENTER_HORIZONTAL
            imgClub = imageView(R.mipmap.img_madrid).lparams(width = 300, height = 300) {
                padding = dip(20)
                margin = dip(15)
            }

            tvName = textView() {
                text = "Real Madrid FC"
                textSize = 18f
                typeface = Typeface.DEFAULT_BOLD
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }.lparams {
                width = wrapContent
                height = wrapContent
                topMargin = dip(5)
            }

            tvDesc = textView() {
                text = "Description"
                textSize = 12f
            }.lparams {
                width = wrapContent
                height = wrapContent
                topMargin = dip(5)
            }
        }

        val intent = intent
        name = intent.getStringExtra("name")
        img = intent.getIntExtra("image", 0)
        desc = intent.getStringExtra("desc")
        tvName.text = name
        tvDesc.text = desc
        Glide.with(applicationContext).load(img).into(imgClub)

    }
}
