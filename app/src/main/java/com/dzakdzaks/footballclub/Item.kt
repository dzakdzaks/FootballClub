package com.dzakdzaks.footballclub

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//parcelable
@Parcelize
data class Item (val name: String?, val image: Int?, val desc: String?): Parcelable