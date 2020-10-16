package com.ess.quickquestions.ui.questionview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ess.quickquestions.model.CategoryX
import com.ess.quickquestions.model.Question

class QuestionViewModel(
    val category: CategoryX,
    application: Application
) : ViewModel(){

    private var _questionList = MutableLiveData<List<Question>>()
    val questionList: LiveData<List<Question>>
        get() = _questionList

    init {
        _questionList.value = category.questions
        println(category.questions)
    }
}