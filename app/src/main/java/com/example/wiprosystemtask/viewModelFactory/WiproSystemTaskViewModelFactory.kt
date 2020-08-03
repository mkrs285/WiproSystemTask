package com.example.wiprosystemtask.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

open class WiproSystemTaskViewModelFactory
    (private val mProviderMap: Map<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    private val className = this.javaClass.simpleName

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mProviderMap[modelClass]!!.get() as T
    }

}