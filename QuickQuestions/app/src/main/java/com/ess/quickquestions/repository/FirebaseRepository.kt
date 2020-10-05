package com.ess.quickquestions.repository

import com.google.firebase.auth.FirebaseAuth

class FirebaseRepository {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun signIn(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {task ->
            if (task.isSuccessful){
                println("başarılı")
            } else{
                println("hata")
            }
        }
    }
}