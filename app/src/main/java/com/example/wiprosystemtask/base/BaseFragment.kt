package com.example.wiprosystemtask.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open abstract class BaseFragment : Fragment() {

    private var mActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            mActivity = context
        }
        injectDependencies(mActivity)


    }

    protected abstract fun injectDependencies(mActivity: BaseActivity?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    protected abstract fun setup(view: View)
}