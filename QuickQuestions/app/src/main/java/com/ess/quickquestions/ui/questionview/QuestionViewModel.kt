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

    private var _isSubmit = MutableLiveData<Boolean>()
    val isSubmit : LiveData<Boolean>
        get() = _isSubmit

    private var _isNext = MutableLiveData<Boolean>()
    val isNext : LiveData<Boolean>
        get() = _isNext

    var questionIndex = 0

    private var _question = MutableLiveData<Question>()
    val question : LiveData<Question>
        get() = _question

    init {
        setQuestion()
    }

    fun submit(){
        _isNext.value = false
        _isSubmit.value = true
    }

    fun next(){
        if(questionIndex >= 5){

        }
        else{
            questionIndex = questionIndex + 1
            setQuestion()
            _isNext.value = true
            _isSubmit.value = false
        }
    }

    fun setQuestion(){
        if(questionIndex < category.questions.count()){
            _question.value = category.questions.get(questionIndex)
        }
    }



}