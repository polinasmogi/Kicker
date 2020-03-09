package com.polinasmogi.isskicker.ui.selectPlayers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.polinasmogi.isskicker.R
import com.polinasmogi.isskicker.api.Requests
import com.polinasmogi.isskicker.api.RetrofitClientInstance
import com.polinasmogi.isskicker.api.Services
import com.polinasmogi.isskicker.api.models.Player
import com.polinasmogi.isskicker.interfaces.SelectPlayerExecutor
import com.polinasmogi.isskicker.ui.adapters.AllPlayersAdapter
import com.polinasmogi.isskicker.ui.adapters.BlueTeamAdapter
import com.polinasmogi.isskicker.ui.adapters.RedTeamAdapter
import kotlinx.android.synthetic.main.select_players_layout.*

class SelectPlayersActivity : AppCompatActivity(), SelectPlayersContract.View {

    val TAG = "SelectPlayersActivity"

    lateinit var apiClient: Services
    lateinit var presenter: SelectPlayersPresenter
    val requests = Requests()

    val players : ArrayList<Player> = ArrayList()
    val blueTeam : ArrayList<Player> = ArrayList()
    val redTeam : ArrayList<Player> = ArrayList()
    lateinit var allPlayersAdapter: AllPlayersAdapter
    lateinit var blueTeamAdapter: BlueTeamAdapter
    lateinit var redTeamAdapter: RedTeamAdapter

    val blue = 1
    val red = 2

    var selectedTeam = blue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_players_layout)

        apiClient = RetrofitClientInstance().getRetrofitInstance().create(Services::class.java)
        presenter = SelectPlayersPresenter()
        presenter.onAttach(this, apiClient, requests)

        presenter.getMembers()

        initBlueRecycler()
        initRedRecycler()

    }

    override fun showPlayers(players: ArrayList<Player>) {
        all_players_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        allPlayersAdapter = AllPlayersAdapter(players, this, Executor())
        all_players_recycler.adapter = allPlayersAdapter
    }

    private fun initBlueRecycler() {
        blue_team_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        blueTeamAdapter = BlueTeamAdapter(blueTeam, this)
        blue_team_recycler.adapter = blueTeamAdapter

        blue_team_layout.setOnClickListener {
            select_players_layout.setBackgroundColor(resources.getColor(R.color.lightBlue))
            selectedTeam = blue
        }
    }

    private fun initRedRecycler() {
        red_team_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        redTeamAdapter = RedTeamAdapter(redTeam, this)
        red_team_recycler.adapter = redTeamAdapter

        red_team_layout.setOnClickListener {
            select_players_layout.setBackgroundColor(resources.getColor(R.color.lightRed))
            selectedTeam = red
        }
    }

    inner class Executor : SelectPlayerExecutor {
        override fun execute(v: View, position: Int) {
            val selectedPlayer = allPlayersAdapter.selectPlayer(position)

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
            allPlayersAdapter.updatePlayersList(players)

        }
    }


}
