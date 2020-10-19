package com.ess.quickquestions.ui.signviews

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SignViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignViewModel::class.java)) {
            return SignViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}