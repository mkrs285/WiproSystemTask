package com.example.wiprosystemtask.di.module

import com.example.wiprosystemtask.di.scope.FactsScope
import com.example.wiprosystemtask.fragment.FactsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FactsModule {

    @FactsScope
    @ContributesAndroidInjector
    abstract fun contributeFactsFragment(): FactsFragment
}