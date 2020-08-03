package com.example.wiprosystemtask.base

import androidx.appcompat.app.AppCompatActivity
import com.example.wiprosystemtask.WiproSystemTaskApplication
import com.example.wiprosystemtask.di.component.DaggerFactsComponent
import com.example.wiprosystemtask.di.component.FactsComponent

open class BaseActivity : AppCompatActivity() {

    // factsComponent lives in the Activity class to share its lifecycle
    private lateinit var factsComponent: FactsComponent


    /**
     * For Initializing Caf Component
     */
    fun createFactsComponent() {
        // Reference to the application graph that is used across the FactsScope
        factsComponent = DaggerFactsComponent.builder()
            .applicationComponent((application as WiproSystemTaskApplication).getComponent())
            .build()
    }

    fun getFactsComponent() = factsComponent


}