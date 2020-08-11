package com.example.wiprosystemtask.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.wiprosystemtask.R
import com.example.wiprosystemtask.activity.MainActivity
import com.example.wiprosystemtask.adapter.FactsAdapter
import com.example.wiprosystemtask.base.BaseFragment
import com.example.wiprosystemtask.dialog.CustomDialog
import com.example.wiprosystemtask.repo.factsResponse.RowsItem
import com.example.wiprosystemtask.utils.ConnectionManager
import com.example.wiprosystemtask.utils.gone
import com.example.wiprosystemtask.utils.visible
import kotlinx.android.synthetic.main.facts_layout_fragment.*
import javax.inject.Inject

class FactsFragment @Inject constructor() : BaseFragment() {

    //Function that can be called without having a class instance
    companion object {
        fun newInstance() = FactsFragment()
    }

    // Reference to the ViewModel scoped to its Activity
    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact
    private val viewModel: FactsViewModel by injectActivityVIewModels()

    //TAG
    private val className = FactsFragment::class.simpleName

    lateinit var dialog: androidx.appcompat.app.AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.facts_layout_fragment, container, false)
    }


    override fun setup(view: View) {
        fetchFacts()

        //Progress bar instance
        dialog = context?.let { CustomDialog.getProgressDialog(it, resources.getString(R.string.loader)) }!!
        //swipe to refresh to get new data
        swipeToRefresh.setOnRefreshListener {
            checkInternetConnection()
            swipeToRefresh.isRefreshing = false
        }
    }

    private fun fetchFacts() {
        viewModel.commonViewStateLiveData.removeObservers(this)
        viewModel.commonViewStateLiveData.observe(this, Observer(this::handleCommonViewState))


        //checking viewModel for data present
        if (viewModel.factsResponse != null) {

            viewModel.factsResponse?.rows?.let { setAdapter(it) }
            activity?.title = viewModel.factsResponse?.title
        } else {
            checkInternetConnection()
        }

    }

    private fun checkInternetConnection() {
        //Checking internet connection using connectivity service
        if (activity?.let { ConnectionManager.singletonInstance?.isConnectingToInternet(it) }!!) {
            viewModel.fetchFacts()
        } else {
            showDialog()
        }
    }

    private fun showDialog() {

        val builder = AlertDialog.Builder(activity)
        builder.setMessage("No Internet Connection")
        builder.setCancelable(false)

        builder.setPositiveButton(android.R.string.yes) { dialog, _ ->
            checkInternetConnection()
            dialog.dismiss()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, _ ->
            activity?.finish()
            dialog.dismiss()
        }

        builder.show()
    }


    private fun handleCommonViewState(factsUIModel: FactsUIModel) {
        //observing data from viewModel using liveData
        when (factsUIModel) {
            is FactsUIModel.ShowProgress -> {
                if (factsUIModel.flag) {
                    CustomDialog.updateText("")
                    dialog.show()
                } else {
                    dialog.dismiss()
                }

            }
            is FactsUIModel.FactsData -> {
                // Displaying data if reponse is success
                listFacts.visible()
                tvError.gone()
                factsUIModel.packs?.rows?.let { setAdapter(it) }
                activity?.title = factsUIModel.packs?.title
            }

            is FactsUIModel.ShowError -> {
                //Displaying error message
                //Using view extensions to Display Views
                listFacts.gone()
                tvError.visible()
                tvError.text = factsUIModel.status
            }

        }

    }

    private fun setAdapter(packs: List<RowsItem>) {
        val factsAdapter = activity?.let { FactsAdapter(packs, it) }!!
        listFacts.adapter = factsAdapter
        factsAdapter.notifyDataSetChanged()
    }

}