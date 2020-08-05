package com.example.wiprosystemtask

import android.app.Application
import com.example.wiprosystemtask.di.component.ApplicationComponent
import com.example.wiprosystemtask.di.component.DaggerApplicationComponent
import com.example.wiprosystemtask.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

// appComponent lives in the Application class to share its lifecycle
class WiproSystemTaskApplication : DaggerApplication() {

    // Reference to the application graph that is used across the whole app
    private val appComponent = DaggerApplicationComponent.factory().create(this)


    fun getComponent(): ApplicationComponent {
        return appComponent
    }

    override fun applicationInjector() =appComponent
}