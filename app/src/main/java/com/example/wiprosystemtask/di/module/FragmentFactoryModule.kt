package com.example.wiprosystemtask.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.wiprosystemtask.di.scope.FragmentKey
import com.example.wiprosystemtask.fragment.FactsFragment
import com.example.wiprosystemtask.utils.MyFragmentFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class FragmentFactoryModule {

    @Binds
    internal abstract fun bindFragmentFactroy(factory: MyFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(FactsFragment::class)
    internal abstract fun bindMainFragment(fragment: FactsFragment): Fragment
}