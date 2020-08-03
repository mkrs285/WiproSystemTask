package com.example.wiprosystemtask.fragment

import com.example.wiprosystemtask.base.BaseInterface
import com.example.wiprosystemtask.network.ApiHelper
import com.example.wiprosystemtask.repo.factsResponse.FactsResponse
import javax.inject.Inject


class FactsModel @Inject constructor(private val apiHelper: ApiHelper) : FactsInterface {

    override fun getFacts(responseListener: BaseInterface.IResponseListener<FactsResponse>) {
        TODO("Not yet implemented")
    }

}