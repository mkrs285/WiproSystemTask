package com.example.wiprosystemtask.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class FactsFragment : BaseFragment() {

    //Function that can be called without having a class instance
    companion object {
        fun newInstance() = FactsFragment()
    }

    // Reference to the ViewModel scoped to its Activity
    private val viewModel: FactsViewModel by injectActivityVIewModels()


    //TAG
    private val className = FactsFragment::class.simpleName



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.facts_layout_fragment, container, false)
    }


    override fun setup(view: View) {
        fetchFacts()

        swipeToRefresh.setOnRefreshListener {
            checkInternetConnection()
            swipeToRefresh.isRefreshing = false
        }
    }

    private fun fetchFacts() {
        viewModel.commonViewStateLiveData.removeObservers(this)
        viewModel.commonViewStateLiveData.observe(this, Observer(this::handleCommonViewState))



        if (viewModel.factsResponse != null) {

            viewModel.factsResponse?.rows?.let { setAdapter(it) }
            activity?.title = viewModel.factsResponse?.title
        } else {
        checkInternetConnection()
        }

    }

    private fun checkInternetConnection(){
        if (activity?.let { ConnectionManager.singletonInstance?.isConnectingToInternet(it) }!!) {
            viewModel.fetchFacts()
        }else{
            showDialog()
        }
    }

    private fun showDialog() {

        val builder = AlertDialog.Builder(activity)
        builder.setMessage("No Internet Connection")
        builder.setCancelable(false)
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

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
        when (factsUIModel) {
            is FactsUIModel.ShowProgress -> {
                if (factsUIModel.flag) {
                    CustomDialog.updateText(resources.getString(factsUIModel.strRes))
                    (activity as MainActivity).dialog.show()
                } else {
                    (activity as MainActivity).dialog.dismiss()
                }

            }
            is FactsUIModel.FactsData -> {
                listFacts.visible()
                tvError.gone()
                factsUIModel.packs?.rows?.let { setAdapter(it) }
                activity?.title = factsUIModel.packs?.title
            }

            is FactsUIModel.ShowError -> {
                listFacts.gone()
                tvError.visible()
                tvError.text = factsUIModel.status
            }

        }

    }

    private fun setAdapter(packs: List<RowsItem>) {
        val  factsAdapter = activity?.let { FactsAdapter(packs, it) }!!
        listFacts.adapter = factsAdapter
        factsAdapter.notifyDataSetChanged()
    }
}