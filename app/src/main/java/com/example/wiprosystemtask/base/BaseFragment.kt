package com.example.wiprosystemtask.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected abstract fun injectDependencies(mActivity: BaseActivity?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    protected abstract fun setup(view: View)
}