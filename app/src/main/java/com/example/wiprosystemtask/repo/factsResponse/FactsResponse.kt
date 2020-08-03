package com.example.wiprosystemtask.repo.factsResponse

import android.os.Parcelable
import com.example.wiprosystemtask.repo.BaseResponse
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FactsResponse(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("rows")
    val rows: List<RowsItem?>? = null
) : Parcelable, BaseResponse()

@Parcelize
data class RowsItem(

    @field:SerializedName("imageHref")
    val imageHref: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null
) : Parcelable
