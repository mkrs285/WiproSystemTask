package com.example.wiprosystemtask.di.component

import com.example.wiprosystemtask.di.module.ApiModule
import com.example.wiprosystemtask.di.module.AppModule
import com.example.wiprosystemtask.di.module.HttpClientModule
import com.example.wiprosystemtask.network.ApiHelper
import dagger.Component
import javax.inject.Singleton

/**
*  @Component Definition of the Application graph
 * @Singleton
**/

@Singleton
@Component(modules = [AppModule::class, HttpClientModule::class, ApiModule::class])
interface ApplicationComponent {

    fun api(): ApiHelper

}