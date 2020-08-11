package com.example.wiprosystemtask.di.module

import com.example.wiprosystemtask.activity.MainActivity
import com.example.wiprosystemtask.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    //Alive until activity lives
    @ActivityScope
    @ContributesAndroidInjector(modules = [FactsModule::class,FragmentFactoryModule::class])
    internal abstract fun contributeMainActivity(): MainActivity


}