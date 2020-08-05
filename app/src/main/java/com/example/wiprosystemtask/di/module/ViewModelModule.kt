package com.example.wiprosystemtask.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wiprosystemtask.di.scope.WiproViewModelKey
import com.example.wiprosystemtask.fragment.FactsViewModel
import com.example.wiprosystemtask.viewModelFactory.WiproSystemTaskViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @WiproViewModelKey(FactsViewModel::class)
    internal abstract fun bindFactsViewModels(factsViewModel: FactsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: WiproSystemTaskViewModelFactory): ViewModelProvider.Factory
}