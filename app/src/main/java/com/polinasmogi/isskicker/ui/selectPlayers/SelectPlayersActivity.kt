package com.polinasmogi.isskicker.ui.selectPlayers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.polinasmogi.isskicker.R
import com.polinasmogi.isskicker.api.Services
import com.polinasmogi.isskicker.api.models.Player
import com.polinasmogi.isskicker.di.ActivityModule
import com.polinasmogi.isskicker.di.DaggerActivityComponent
import com.polinasmogi.isskicker.di.DaggerServicesComponent
import com.polinasmogi.isskicker.di.ServicesModule
import com.polinasmogi.isskicker.interfaces.SelectPlayerExecutor
import com.polinasmogi.isskicker.ui.adapters.PlayersAdapter
import kotlinx.android.synthetic.main.select_players_layout.*
import com.polinasmogi.isskicker.api.Requests as Requests

class SelectPlayersActivity : AppCompatActivity(), SelectPlayersContract.View {

    val TAG = "SelectPlayersActivity"

    private lateinit var apiClient: Services
    private lateinit var presenter: SelectPlayersPresenter
    private lateinit var requests: Requests

    var players : ArrayList<Player> = ArrayList()
    val blueTeam : ArrayList<Player> = ArrayList()
    val redTeam : ArrayList<Player> = ArrayList()
    lateinit var playersAdapter: PlayersAdapter
    lateinit var blueTeamAdapter: PlayersAdapter
    lateinit var redTeamAdapter: PlayersAdapter

    private val blue = 1
    private val red = 2

    var selectedTeam = blue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_players_layout)

        injectDependency()

        presenter.onAttach(this, apiClient, requests)
        presenter.getMembers()

        initBlueRecycler()
        initRedRecycler()

    }

    private fun injectDependency() {
        val servicesComponent = DaggerServicesComponent.builder()
            .servicesModule(ServicesModule())
            .build()

        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        apiClient = servicesComponent.getServices()
        requests = servicesComponent.getRequests()
        presenter = activityComponent.getSelectPlayersPresenter()
    }

    override fun showPlayers(players: ArrayList<Player>) {
        this.players = players

        all_players_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        playersAdapter = PlayersAdapter(players, this, Executor())
        all_players_recycler.adapter = playersAdapter
    }

    private fun initBlueRecycler() {
        blue_team_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        blueTeamAdapter = PlayersAdapter(blueTeam, this, Executor())
        blue_team_recycler.adapter = blueTeamAdapter

        blue_team_layout.setOnClickListener {
            select_players_layout.setBackgroundColor(resources.getColor(R.color.lightBlue))
            selectedTeam = blue
        }
    }

    private fun initRedRecycler() {
        red_team_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        redTeamAdapter = PlayersAdapter(redTeam, this, Executor())
        red_team_recycler.adapter = redTeamAdapter

        red_team_layout.setOnClickListener {
            select_players_layout.setBackgroundColor(resources.getColor(R.color.lightRed))
            selectedTeam = red
        }
    }

    inner class Executor : SelectPlayerExecutor {
        override fun execute(v: View, position: Int) {
            val selectedPlayer = playersAdapter.selectPlayer(position)

            if (selectedTeam == blue) {
                if (blueTeam.size < 2) {
                    blueTeam.add(selectedPlayer)
                    blueTeamAdapter.updatePlayersList(blueTeam)
                }
            } else {
                if (redTeam.size < 2) {
                    redTeam.add(selectedPlayer)
                    redTeamAdapter.updatePlayersList(redTeam)
                }
            }

            players.remove(selectedPlayer)
            playersAdapter.updatePlayersList(players)

        }
    }


}
