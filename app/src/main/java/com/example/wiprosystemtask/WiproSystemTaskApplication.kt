package com.example.wiprosystemtask

import android.app.Application
import com.example.wiprosystemtask.di.component.ApplicationComponent
import com.example.wiprosystemtask.di.component.DaggerApplicationComponent
import com.example.wiprosystemtask.di.module.AppModule

// appComponent lives in the Application class to share its lifecycle
class WiproSystemTaskApplication : Application() {

    private lateinit var mApplicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        // Reference to the application graph that is used across the whole app
        mApplicationComponent = DaggerApplicationComponent.builder().appModule(AppModule()).build()
    }

    fun getComponent(): ApplicationComponent {
        return mApplicationComponent
    }

}