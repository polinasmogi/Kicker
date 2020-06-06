package com.polinasmogi.isskicker.di

import com.polinasmogi.isskicker.ui.selectPlayers.SelectPlayersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun getSelectPlayersPresenter() : SelectPlayersPresenter

}