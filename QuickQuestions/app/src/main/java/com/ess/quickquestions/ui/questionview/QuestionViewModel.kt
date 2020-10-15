package com.ess.quickquestions.ui.questionview

import android.app.Application
import androidx.lifecycle.ViewModel
import com.ess.quickquestions.model.CategoryX

class QuestionViewModel(
    val category: CategoryX,
    application: Application
) : ViewModel(){

    init {
        println(category)
    }
}