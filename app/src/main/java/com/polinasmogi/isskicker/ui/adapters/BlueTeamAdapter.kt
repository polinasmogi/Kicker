package com.polinasmogi.isskicker.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polinasmogi.isskicker.R
import com.polinasmogi.isskicker.api.models.Player

class BlueTeamAdapter (var blueTeamPlayers : ArrayList<Player>, val context: Context) : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.player_item, parent, false))
    }

    override fun getItemCount(): Int = blueTeamPlayers.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {

        val player = blueTeamPlayers.get(position)

        holder.playerName?.text = player.name

        Glide.with(context)
            .load(player.photo)
            .centerCrop()
            .into(holder.playerPhoto)

    }

    fun updatePlayersList(blueTeamPlayers: ArrayList<Player>) {
        this.blueTeamPlayers = ArrayList(blueTeamPlayers)
        notifyDataSetChanged()
    }


}