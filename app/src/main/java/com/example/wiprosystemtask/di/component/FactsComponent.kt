package com.example.wiprosystemtask.di.component

import com.example.wiprosystemtask.di.module.FactsModule
import com.example.wiprosystemtask.di.scope.FactsScope
import com.example.wiprosystemtask.fragment.FactsFragment
import dagger.Component

@FactsScope
@Component(dependencies = [ApplicationComponent::class], modules = [FactsModule::class])
 interface FactsComponent {

    // This tells Dagger that FactsFragment requests injection so the graph needs to
    // satisfy all the dependencies of the fields that FactsFragment is requesting.

    fun inject(fragment: FactsFragment)
}