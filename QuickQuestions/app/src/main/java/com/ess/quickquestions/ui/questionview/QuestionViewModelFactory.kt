package com.ess.quickquestions.ui.questionview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ess.quickquestions.model.CategoryX
import java.lang.IllegalArgumentException

class QuestionViewModelFactory(
    private val category: CategoryX,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(QuestionViewModel::class.java)){
            return QuestionViewModel(category,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}