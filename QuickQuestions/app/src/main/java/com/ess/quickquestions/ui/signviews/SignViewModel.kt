package com.ess.quickquestions.ui.signviews

import android.text.Editable
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignViewModel : ViewModel() {

    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


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
            if (isPasswordMatch(signUpPasswordText,signUpRePasswordText, signUpRePasswordInput)) {
                signUpRePasswordInput?.error = null
            }
            false
        }
    }

    //Sign In Click Event
    fun onSignInClicked(
        emailText: TextInputEditText?,
        emailInput: TextInputLayout?,
        passwordText: TextInputEditText?,
        passwordInput: TextInputLayout?
    ) {

        val isMailValid = isEmailValid(emailText, emailInput)
        val isPasswordValid = isPasswordValid(passwordText, passwordInput)


        println("Email Validation: " + isMailValid)
        println("Password Validation: " + isPasswordValid)
    }

    //Sign Up Click Event
    fun onSignUpClicked(
        emailText: TextInputEditText?,
        emailInput: TextInputLayout?,
        passwordText: TextInputEditText?,
        passwordInput: TextInputLayout?,
        repasswordText: TextInputEditText?,
        repasswordInput: TextInputLayout?
    ){
        val isMailValid = isEmailValid(emailText,emailInput)
        val isPasswordValid= isPasswordValid(passwordText,passwordInput)
        val isPasswordMatch = isPasswordMatch(passwordText,repasswordText,repasswordInput)

        println("Email: " + isMailValid)
        println("Password: " + isPasswordValid)
        println("Password Match: " + isPasswordMatch)
    }

    //Email Validation Control
    private fun isEmailValid(email: TextInputEditText?, emailInput: TextInputLayout?): Boolean {

        return if (email?.text.isNullOrEmpty()) {
            emailInput?.error = "Bu alan boş bırakılamaz"
            false
        } else {
            if (email?.text != null && email.text!!.trim().matches(Regex(emailPattern))) {
                emailInput?.error = null
                true
            } else {
                emailInput?.error = "Email bilgilerini doğru girilmeli"
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
            passwordInput?.error = "Bu alan boş bırakılamaz"
            //password?.error = "Bu alan boş bırakılamaz"
            false
        } else {
            if (password?.text != null && password.text!!.length >= 8) {
                passwordInput?.error = null
                true
            } else {
                passwordInput?.error = "Şifre uzunluğu en az 8 karakter olmalıdır"
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
        if(repasswordText?.text.isNullOrEmpty()){
            repasswordInput?.error = "Bu alan boş bırakılmamalıdır"
            return false
        } else{
            if(passwordText?.text?.trim().toString() != repasswordText?.text?.trim().toString()){
                repasswordInput?.error = "Şifreler uyuşmamaktadır"
                return false
            } else{
                repasswordInput?.error = null
                return true
            }
        }
    }
}