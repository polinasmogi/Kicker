package com.polinasmogi.isskicker.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polinasmogi.isskicker.R
import com.polinasmogi.isskicker.api.models.Player

class RedTeamAdapter (var redTeamPlayers : ArrayList<Player>, val context: Context) : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.player_item, parent, false))
    }

    override fun getItemCount(): Int = redTeamPlayers.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = redTeamPlayers.get(position)

        holder.playerName?.text = player.name

        Glide.with(context)
            .load(player.photo)
            .centerCrop()
            .into(holder.playerPhoto)

    }

    fun updatePlayersList(redTeamPlayers: ArrayList<Player>) {
        this.redTeamPlayers = ArrayList(redTeamPlayers)
        notifyDataSetChanged()
    }


}