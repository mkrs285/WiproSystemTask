package com.example.wiprosystemtask.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wiprosystemtask.di.scope.FactsScope
import com.example.wiprosystemtask.fragment.FactsInterface
import com.example.wiprosystemtask.fragment.FactsModel
import com.example.wiprosystemtask.fragment.FactsViewModel
import com.example.wiprosystemtask.viewModelFactory.WiproSystemTaskViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
class FactsModule {

    @Provides
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
        return WiproSystemTaskViewModelFactory(providerMap)
    }

    @FactsScope
    @Provides
    @IntoMap
    @WiproViewModelKey(FactsViewModel::class)
    fun provideFactsViewModel(factsInterface: FactsInterface): ViewModel {
        return FactsViewModel(factsInterface)
    }

    @Provides
    fun provideFactsModel(factsModel: FactsModel): FactsInterface {
        return factsModel
    }
}