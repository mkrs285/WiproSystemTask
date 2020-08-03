package com.example.wiprosystemtask.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wiprosystemtask.BuildConfig
import com.example.wiprosystemtask.constants.NetworkConstants
import com.example.wiprosystemtask.viewModelFactory.WiproSystemTaskViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

@Module
class AppModule {


    @Singleton
    @Provides
    fun provideIsDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    @Named("connectionTimeoutInSeconds")
    fun provideConnectionTimeOutInSeconds(): Int {
        return NetworkConstants.CONNECTION_TIME_OUT
    }

    @Singleton
    @Provides
    @Named("readTimeoutInSeconds")
    fun provideReadTimeOutInSeconds(): Int {
        return NetworkConstants.READ_TIME_OUT
    }

    @Provides
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return WiproSystemTaskViewModelFactory(providerMap)
    }
}