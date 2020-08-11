package com.example.wiprosystemtask.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wiprosystemtask.di.scope.WiproViewModelKey
import com.example.wiprosystemtask.fragment.FactsFragment
import com.example.wiprosystemtask.fragment.FactsViewModel
import com.example.wiprosystemtask.utils.MyFragmentFactory
import com.example.wiprosystemtask.viewModelFactory.WiproSystemTaskViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @WiproViewModelKey(FactsViewModel::class)
    internal abstract fun bindFactsViewModels(factsViewModel: FactsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: WiproSystemTaskViewModelFactory): ViewModelProvider.Factory




}

