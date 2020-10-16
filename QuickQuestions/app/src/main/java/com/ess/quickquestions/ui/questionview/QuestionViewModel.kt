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

    private var _questionIndex = MutableLiveData<Int>()
    val questionIndex : LiveData<Int>
        get() = _questionIndex

    private var _isSubmit = MutableLiveData<Boolean>()
    val isSubmit : LiveData<Boolean>
        get() = _isSubmit

    private var _isNext = MutableLiveData<Boolean>()
    val isNext : LiveData<Boolean>
        get() = _isNext


    init {
        _questionList.value = category.questions
        println(category.questions)
    }

    fun submit(){
        _isNext.value = false
        _isSubmit.value = true
    }

    fun next(){
        _isNext.value = true
        _isSubmit.value = false
    }

}