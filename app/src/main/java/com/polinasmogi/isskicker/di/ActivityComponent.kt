package com.polinasmogi.isskicker.di

import com.polinasmogi.isskicker.ui.selectPlayers.SelectPlayersPresenter
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun getSelectPlayersPresenter() : SelectPlayersPresenter

}