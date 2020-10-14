package com.ess.quickquestions.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class CategoryX(
    val id: String = "",
    val name: String = "",
    val questions: @RawValue List<Question> = listOf()
) : Parcelable