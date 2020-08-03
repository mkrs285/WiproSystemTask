package com.example.wiprosystemtask.fragment

import com.example.wiprosystemtask.base.BaseInterface
import com.example.wiprosystemtask.repo.factsResponse.FactsResponse

interface FactsInterface {
    fun getFacts(
        responseListener: BaseInterface.IResponseListener<FactsResponse>
    )
}