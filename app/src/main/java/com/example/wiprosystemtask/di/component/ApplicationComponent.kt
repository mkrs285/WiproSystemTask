package com.example.wiprosystemtask.di.component

import android.app.Application
import com.example.wiprosystemtask.di.module.*
import com.example.wiprosystemtask.di.module.ViewModelModule
import com.example.wiprosystemtask.network.ApiHelper
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

/**
*  @Component Definition of the Application graph
 * @Singleton Single Instance
**/
@Singleton
@Component(modules = [AndroidInjectionModule::class,AppModule::class, HttpClientModule::class, ApiModule::class,ViewModelModule::class,ActivityModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {


    //A factory here is basically a more versatile (and less verbose!) builder.
    //compile time safety
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

    fun api(): ApiHelper
}