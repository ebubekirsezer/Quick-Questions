package com.ess.quickquestions.ui.signviews

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

enum class SignStatus {LOADING, ERROR, DONE}

class SignViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth

    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    private val _status = MutableLiveData<SignStatus>()
    val status : LiveData<SignStatus>
        get() = _status

    private val _loadingProcess = MutableLiveData<Boolean?>()
    val loadingProcess : LiveData<Boolean?>
        get() = _loadingProcess

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

        _loadingProcess.value = false
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
        val isMailValid = isEmailValid(emailText, emailInput)
        val isPasswordValid = isPasswordValid(passwordText, passwordInput)
        val isPasswordMatch = isPasswordMatch(passwordText, repasswordText, repasswordInput)

        if(isMailValid && isPasswordValid && isPasswordMatch){

            auth.createUserWithEmailAndPassword("ebubekir@gmail.com","123456789").addOnCompleteListener {
                if(it.isSuccessful){
                    println("Harika")
                } else{
                    println("Try again")
                }
            }

            println(emailText?.text.toString())
        }
    }

    //Email Validation Control
    private fun isEmailValid(email: TextInputEditText?, emailInput: TextInputLayout?): Boolean {

        return if (email?.text.isNullOrEmpty()) {
            emailInput?.error = "This field cannot be empty"
            false
        } else {
            if (email?.text != null && email.text!!.trim().matches(Regex(emailPattern))) {
                emailInput?.error = null
                true
            } else {
                emailInput?.error = "Email information must be true"
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
            passwordInput?.error = "This field cannot be empty"
            //password?.error = "Bu alan boş bırakılamaz"
            false
        } else {
            if (password?.text != null && password.text!!.length >= 8) {
                passwordInput?.error = null
                true
            } else {
                passwordInput?.error = "Password length must be at least 8 character"
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
            repasswordInput?.error = "This field cannot be empty"
            return false
        } else {
            if (passwordText?.text?.trim().toString() != repasswordText?.text?.trim().toString()) {
                repasswordInput?.error = "Passwords not match"
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