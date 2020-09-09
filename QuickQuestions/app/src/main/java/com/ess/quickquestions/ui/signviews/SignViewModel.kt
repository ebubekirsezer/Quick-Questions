package com.ess.quickquestions.ui.signviews

import android.text.Editable
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignViewModel : ViewModel() {

    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


    //Input Text Change Control
    fun onTextKeyListener(emailText: TextInputEditText?, emailInput: TextInputLayout?, passwordText: TextInputEditText?, passwordInput: TextInputLayout?){

        //Sign In Email Text change
        emailText?.setOnKeyListener { view, i, keyEvent ->
            if(isEmailValid(emailText,emailInput)){
                emailInput?.error = null
            }
            false
        }

        //Sign In Password Text Change
        passwordText?.setOnKeyListener { view, i, keyEvent ->
            if(isPasswordValid(passwordText,passwordInput)){
                passwordInput?.error = null
            }
            false
        }
    }

    //Sign In Click Event
    fun onSignInClicked(emailText: TextInputEditText?, emailInput: TextInputLayout?, passwordText: TextInputEditText?, passwordInput: TextInputLayout?) {

        val isMailValid = isEmailValid(emailText,emailInput)
        val isPasswordValid = isPasswordValid(passwordText,passwordInput)


        println("Email Validation: " + isMailValid)
        println("Password Validation: " + isPasswordValid)
    }

    //Email Validation Control
    private fun isEmailValid(email: TextInputEditText?,emailInput: TextInputLayout?): Boolean {

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
    private fun isPasswordValid(password: TextInputEditText?, passwordInput: TextInputLayout?): Boolean {
        return if(password?.text.isNullOrEmpty()){
            passwordInput?.error = "Bu alan boş bırakılamaz"
            //password?.error = "Bu alan boş bırakılamaz"
            false
        } else{
            if(password?.text != null && password.text!!.length >= 8){
                passwordInput?.error = null
                true
            } else{
                passwordInput?.error = "Şifre uzunluğu en az 8 karakter olmalıdır"
                false
            }
        }
    }
}