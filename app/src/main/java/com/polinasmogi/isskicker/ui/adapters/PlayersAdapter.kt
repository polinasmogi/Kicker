package com.polinasmogi.isskicker.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polinasmogi.isskicker.R
import com.polinasmogi.isskicker.api.models.Player
import com.polinasmogi.isskicker.interfaces.SelectPlayerExecutor

class PlayersAdapter (var players: ArrayList<Player>, val context: Context, val executor: SelectPlayerExecutor): RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.player_item, parent, false))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {

        val player = players.get(position)

        holder.playerName?.text = player.name

        Glide.with(context)
            .load(player.photo.small)
            .centerCrop()
            .into(holder.playerPhoto)

        holder.playerName.setOnClickListener {
            executor.execute(holder.playerName, position)
        }
    }

    fun selectPlayer(position: Int): Player {
        return players[position]
    }

    fun updatePlayersList(players: ArrayList<Player>) {
        this.players = ArrayList(players)
        notifyDataSetChanged()
    }
}