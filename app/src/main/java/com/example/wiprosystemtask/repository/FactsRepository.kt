package com.example.wiprosystemtask.repository

import com.example.wiprosystemtask.base.BaseInterface
import com.example.wiprosystemtask.fragment.FactsInterface
import com.example.wiprosystemtask.network.ApiHelper
import com.example.wiprosystemtask.repo.factsResponse.FactsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FactsRepository @Inject constructor(
    private val apiHelper: ApiHelper
) : FactsInterface {

    private lateinit var disposable: Disposable

    override fun getFacts(responseListener: BaseInterface.IResponseListener<FactsResponse>) {

        responseListener.onProgress()

        disposable = apiHelper.getFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(responseListener::onSuccess, responseListener::onFailure)
    }

}