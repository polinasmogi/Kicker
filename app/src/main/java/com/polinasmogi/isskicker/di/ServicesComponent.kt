package com.polinasmogi.isskicker.di

import com.polinasmogi.isskicker.api.Requests
import com.polinasmogi.isskicker.api.Services
import dagger.Component

@Component(modules = [ServicesModule::class])
interface ServicesComponent {
    fun getServices(): Services
    fun getRequests(): Requests
}