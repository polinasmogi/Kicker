package com.polinasmogi.isskicker.ui.selectPlayers

import com.polinasmogi.isskicker.api.Requests
import com.polinasmogi.isskicker.api.Services


class SelectPlayersPresenter: SelectPlayersContract.Presenter {

    private lateinit var view: SelectPlayersContract.View
    private lateinit var apiClient: Services
    private lateinit var requests: Requests

    override fun onAttach(
        view: SelectPlayersContract.View,
        apiClient: Services,
        requests: Requests
    ) {
        this.view = view
        this.apiClient = apiClient
        this.requests = requests
    }


    override fun getMembers() {
        requests.getMembers(
            apiClient,
            callback = {
                view.showPlayers(it)
            },
            errorHandler = {}
        )
    }

}