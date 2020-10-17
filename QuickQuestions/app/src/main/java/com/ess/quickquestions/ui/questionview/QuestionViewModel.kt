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

    private val _isCorrect = MutableLiveData<Boolean>()
    val isCorrect : LiveData<Boolean>
        get() = _isCorrect

    private val _inNotSelectedOption = MutableLiveData<Boolean>()
    val isNotSelectedOption : LiveData<Boolean>
        get() = _inNotSelectedOption

    private val _isTestFinish = MutableLiveData<Boolean>()
    val isTestFinish : LiveData<Boolean>
        get() = _isTestFinish

    var score = 0

    init {
        setQuestion()
    }

    fun submit(optionIndex: Int){

        if(optionIndex == 0){
            _inNotSelectedOption.value = true
        } else{
            _inNotSelectedOption.value = false
            _isCorrect.value = checkAnswer(optionIndex)
            if (_isCorrect.value!!)
                score = score + 20

            _isNext.value = false
            _isSubmit.value = true
        }
    }

    private fun checkAnswer(optionIndex: Int) : Boolean {
        when(optionIndex){
            1 -> {
                return _question.value?.option_1 == _question.value?.answer
            }
            2 -> {
                return _question.value?.option_2 == _question.value?.answer
            }
            3 ->{
                return _question.value?.option_3 == _question.value?.answer
            }
            4 ->{
                return _question.value?.option_4 == _question.value?.answer
            }
            else -> {
                return false
            }
        }
    }

    fun next(){
        questionIndex = questionIndex + 1
        if(questionIndex < 5){
            setQuestion()
            _isNext.value = true
            _isSubmit.value = false
        }
        else
            _isTestFinish.value = true
    }

    fun setQuestion(){
        if(questionIndex < category.questions.count()){
            _question.value = category.questions.get(questionIndex)
        }
    }



}