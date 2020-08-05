package com.example.wiprosystemtask.repo.factsResponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FactsResponse(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("rows")
    val rows: List<RowsItem>
) : Parcelable

@Parcelize
data class RowsItem(

    @field:SerializedName("imageHref")
    val imageHref: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("title")
    val title: String
) : Parcelable
