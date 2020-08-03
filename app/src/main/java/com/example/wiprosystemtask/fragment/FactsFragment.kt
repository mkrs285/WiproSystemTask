package com.example.wiprosystemtask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.wiprosystemtask.R
import com.example.wiprosystemtask.base.BaseActivity
import com.example.wiprosystemtask.base.BaseFragment
import javax.inject.Inject

class FactsFragment : BaseFragment() {

    //Function that can be called without having a class instance
    companion object {
        fun newInstance() = FactsFragment()
    }

    //TAG
    private val className = FactsFragment::class.simpleName


    // Reference to the ViewModel scoped to its Activity
    val viewModel: FactsViewModel by activityViewModels<FactsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.facts_layout_fragment, container, false)
    }



    override fun injectDependencies(mActivity: BaseActivity?) {
        // Make Dagger instantiate @Inject fields in FactsFragment
        mActivity?.createFactsComponent()
        mActivity?.getFactsComponent()?.inject(this)
    }

    override fun setup(view: View) {
        TODO("Not yet implemented")
    }
}