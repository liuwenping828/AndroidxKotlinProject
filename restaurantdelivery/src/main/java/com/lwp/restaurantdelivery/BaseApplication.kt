package com.lwp.restaurantdelivery

import android.app.Application
import com.lwp.restaurantdelivery.di.application.ApplicationComponent
import com.lwp.restaurantdelivery.di.application.ApplicationModule
import com.lwp.restaurantdelivery.di.application.DaggerApplicationComponent

class BaseApplication : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        inject()
    }

    private fun inject() {
        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()

        component.inject(this)
    }



}