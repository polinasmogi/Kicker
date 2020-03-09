package com.polinasmogi.isskicker.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.player_item.view.*

class PlayerViewHolder (view : View) : RecyclerView.ViewHolder(view) {

    val playerPhoto = view.player_photo
    val playerName = view.player_name

}