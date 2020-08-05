package com.example.wiprosystemtask.fragment

import com.example.wiprosystemtask.repo.factsResponse.FactsResponse
import com.example.wiprosystemtask.repo.factsResponse.RowsItem

sealed class FactsUIModel {
    class ShowProgress(val flag: Boolean, val strRes: Int = 0) : FactsUIModel()
    class FactsData(val packs: FactsResponse?) : FactsUIModel()
    class ShowError(val status: String) : FactsUIModel()
}