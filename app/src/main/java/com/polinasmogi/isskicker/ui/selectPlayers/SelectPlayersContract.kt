package com.polinasmogi.isskicker.ui.selectPlayers

import com.polinasmogi.isskicker.api.Requests
import com.polinasmogi.isskicker.api.Services
import com.polinasmogi.isskicker.api.models.Player

interface SelectPlayersContract {

    interface View {
        fun showPlayers(players: ArrayList<Player>)
    }

    interface Presenter {
        fun onAttach(view: View, apiClient: Services, requests: Requests)
        fun getMembers()
    }

}