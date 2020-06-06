package com.polinasmogi.isskicker.di

import android.app.Activity
import com.polinasmogi.isskicker.ui.selectPlayers.SelectPlayersPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providesSelectPlayerPresenter(): SelectPlayersPresenter {
        return SelectPlayersPresenter()
    }

}