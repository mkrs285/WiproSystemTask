package com.example.wiprosystemtask.di.component

import android.app.Application
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wiprosystemtask.WiproSystemTaskApplication
import com.example.wiprosystemtask.di.module.*
import com.example.wiprosystemtask.network.ApiHelper
import com.example.wiprosystemtask.viewModelFactory.WiproSystemTaskViewModelFactory
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
@Component(modules = [AndroidInjectionModule::class, AppModule::class, HttpClientModule::class, ApiModule::class, ViewModelModule::class, ActivityModule::class, FragmentFactoryModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {


    //A Builder here is basically a more versatile (and less verbose!) builder.
    //compile time safety
    //For testing
    @Component.Builder
    interface TestBuilder  {
        fun create(@BindsInstance application: Application): TestBuilder
        fun build(): ApplicationComponent
    }

    fun api(): ApiHelper
    fun fragmentFactory(): FragmentFactory
}