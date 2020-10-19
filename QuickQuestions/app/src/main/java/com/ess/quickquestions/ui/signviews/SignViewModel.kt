package com.ess.quickquestions.ui.signviews

import android.app.Application
import android.text.Editable
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ess.quickquestions.R
import com.ess.quickquestions.repository.FirebaseRepository
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

enum class SignStatus { LOADING, ERROR, DONE }

class SignViewModel(val application: Application) : ViewModel() {

    private lateinit var auth: FirebaseAuth

    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    private val _status = MutableLiveData<SignStatus>()
    val status: LiveData<SignStatus>
        get() = _status

    private val _loadingProcess = MutableLiveData<Boolean?>()
    val loadingProcess: LiveData<Boolean?>
        get() = _loadingProcess

    private val _navigateToSignIn = MutableLiveData<Boolean>()
    val navigateToSignIn: LiveData<Boolean>
        get() = _navigateToSignIn

    private val _navigateToSignUp = MutableLiveData<Boolean>()
    val navigateToSignUp: LiveData<Boolean>
        get() = _navigateToSignUp

    init {
        auth = Firebase.auth
    }

    //Sign In Click Event
    fun onSignInClicked(
        emailText: TextInputEditText?,
        emailInput: TextInputLayout?,
        passwordText: TextInputEditText?,
        passwordInput: TextInputLayout?
    ) {
        _loadingProcess.value = true

        val isMailValid = isEmailValid(emailText, emailInput)
        val isPasswordValid = isPasswordValid(passwordText, passwordInput)

        if (isMailValid && isPasswordValid) {
            auth.signInWithEmailAndPassword(
                emailText?.text.toString(),
                passwordText?.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    _navigateToSignIn.value = true
                    _loadingProcess.value = false
                    println("Successfully Login")
                } else {
                    _loadingProcess.value = false
                    Toast.makeText(
                        application.applicationContext,
                        it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    println("Unlucky")
                }
            }
        }
    }

    fun onNavigatedToSignIn() {
        _navigateToSignIn.value = false
    }

    //Sign Up Click Event
    fun onSignUpClicked(
        emailText: TextInputEditText?,
        emailInput: TextInputLayout?,
        passwordText: TextInputEditText?,
        passwordInput: TextInputLayout?,
        repasswordText: TextInputEditText?,
        repasswordInput: TextInputLayout?
    ) {
        _loadingProcess.value = true
        val isMailValid = isEmailValid(emailText, emailInput)
        val isPasswordValid = isPasswordValid(passwordText, passwordInput)
        val isPasswordMatch = isPasswordMatch(passwordText, repasswordText, repasswordInput)

        if (isMailValid && isPasswordValid && isPasswordMatch) {

            auth.createUserWithEmailAndPassword(
                emailText?.text.toString(),
                passwordText?.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    _loadingProcess.value = false
                    _navigateToSignUp.value = true
                    println("Awesome")
                } else {
                    _loadingProcess.value = false
                    Toast.makeText(
                        application.applicationContext,
                        it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else
            _loadingProcess.value = false
    }

    fun onNavigatedToSignUp() {
        _navigateToSignUp.value = false
    }

    //Email Validation Control
    private fun isEmailValid(email: TextInputEditText?, emailInput: TextInputLayout?): Boolean {

        return if (email?.text.isNullOrEmpty()) {
            emailInput?.error = application.applicationContext.getString(R.string.cannot_empty)
            false
        } else {
            if (email?.text != null && email.text!!.trim().matches(Regex(emailPattern))) {
                emailInput?.error = null
                true
            } else {
                emailInput?.error = application.applicationContext.getString(R.string.email_information)
                false
            }
        }
    }

    //Password Validation Control
    private fun isPasswordValid(
        password: TextInputEditText?,
        passwordInput: TextInputLayout?
    ): Boolean {
        return if (password?.text.isNullOrEmpty()) {
            passwordInput?.error = application.applicationContext.getString(R.string.text_cannot_empty_warning)
            false
        } else {
            if (password?.text != null && password.text!!.length >= 8) {
                passwordInput?.error = null
                true
            } else {
                passwordInput?.error = application.applicationContext.getString(R.string.password_length_warning)
                false
            }
        }
    }

    //Password and RePassowrd Match Control
    private fun isPasswordMatch(
        passwordText: TextInputEditText?,
        repasswordText: TextInputEditText?,
        repasswordInput: TextInputLayout?
    ): Boolean {
        if (repasswordText?.text.isNullOrEmpty()) {
            repasswordInput?.error = application.applicationContext.getString(R.string.text_cannot_empty_warning)
            return false
        } else {
            if (passwordText?.text?.trim().toString() != repasswordText?.text?.trim().toString()) {
                repasswordInput?.error = application.applicationContext.getString(R.string.password_not_match)
                return false
            } else {
                repasswordInput?.error = null
                return true
            }
        }
    }

    //Input Text Change Control
    fun onTextKeyListener(
        emailText: TextInputEditText?,
        emailInput: TextInputLayout?,
        passwordText: TextInputEditText?,
        passwordInput: TextInputLayout?,
        signUpEmailText: TextInputEditText?,
        signUpEmailInput: TextInputLayout?,
        signUpPasswordText: TextInputEditText?,
        signUpPasswordInput: TextInputLayout?,
        signUpRePasswordText: TextInputEditText?,
        signUpRePasswordInput: TextInputLayout?,
    ) {

        //Sign In Email Text change
        emailText?.setOnKeyListener { view, i, keyEvent ->
            if (isEmailValid(emailText, emailInput)) {
                emailInput?.error = null
            }
            false
        }

        //Sign In Password Text Change
        passwordText?.setOnKeyListener { view, i, keyEvent ->
            if (isPasswordValid(passwordText, passwordInput)) {
                passwordInput?.error = null
            }
            false
        }

        //Sign Up Email Text change
        signUpEmailText?.setOnKeyListener { view, i, keyEvent ->
            if (isEmailValid(signUpEmailText, signUpEmailInput)) {
                signUpEmailInput?.error = null
            }
            false
        }

        //Sign Up Password Text change
        signUpPasswordText?.setOnKeyListener { view, i, keyEvent ->
            if (isPasswordValid(signUpPasswordText, signUpPasswordInput)) {
                signUpPasswordInput?.error = null
            }
            false
        }

        //Sign Up Re-Password Text change
        signUpRePasswordText?.setOnKeyListener { view, i, keyEvent ->
            if (isPasswordMatch(signUpPasswordText, signUpRePasswordText, signUpRePasswordInput)) {
                signUpRePasswordInput?.error = null
            }
            false
        }
    }
}