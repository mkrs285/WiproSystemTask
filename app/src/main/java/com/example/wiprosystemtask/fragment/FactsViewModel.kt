package com.example.wiprosystemtask.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wiprosystemtask.R
import com.example.wiprosystemtask.base.BaseInterface
import com.example.wiprosystemtask.base.BaseViewModel
import com.example.wiprosystemtask.repo.factsResponse.FactsResponse
import com.example.wiprosystemtask.repository.FactsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class FactsViewModel @Inject constructor(private val factsRepository: FactsRepository) :
    BaseViewModel() {


    private val commonViewStateMutableLiveData: MutableLiveData<FactsUIModel> = MutableLiveData()
    val commonViewStateLiveData: LiveData<FactsUIModel>
        get() = commonViewStateMutableLiveData

      var factsResponse: FactsResponse?=null

    fun fetchFacts() {
        this.viewModelScope.launch {
            factsRepository.getFacts(object : BaseInterface.IResponseListener<FactsResponse> {
                override fun onProgress() {
                    commonViewStateMutableLiveData.value =
                        FactsUIModel.ShowProgress(true, R.string.loader)
                }

                override fun onSuccess(resp: FactsResponse) {

                        commonViewStateMutableLiveData.value =
                            FactsUIModel.FactsData(resp)

                    factsResponse = resp

                    commonViewStateMutableLiveData.value =
                        FactsUIModel.ShowProgress(false, R.string.loader)
                }

                override fun onFailure(t: Throwable) {
                    commonViewStateMutableLiveData.value = t.cause?.localizedMessage?.let {
                        FactsUIModel.ShowError(
                            it
                        )
                    }
                    commonViewStateMutableLiveData.value =
                        FactsUIModel.ShowProgress(false, R.string.loader)
                }

            })

        }

    }

}