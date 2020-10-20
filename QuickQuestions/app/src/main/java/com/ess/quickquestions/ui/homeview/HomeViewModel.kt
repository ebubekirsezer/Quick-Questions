package com.ess.quickquestions.ui.homeview

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ess.quickquestions.model.Category
import com.ess.quickquestions.model.CategoryX
import com.ess.quickquestions.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel : ViewModel() {


    private var _categoryList = MutableLiveData<ArrayList<CategoryX>>()
    val categoryList: LiveData<ArrayList<CategoryX>>
        get() = _categoryList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _isErrorFetchingModels = MutableLiveData<Boolean>()
    val isErrorFetchingModels: LiveData<Boolean>
        get() = _isErrorFetchingModels

    private var _isErrorFetchingUser = MutableLiveData<Boolean>()
    val isErrorFetchingUser : LiveData<Boolean>
        get() = _isErrorFetchingUser

    private var _naviagetToSign = MutableLiveData<Boolean>()
    val navigateToSign: LiveData<Boolean>
        get() = _naviagetToSign

    private var _highScore = MutableLiveData<Int>()
    val highScore: LiveData<Int>
        get() = _highScore

    private lateinit var auth: FirebaseAuth

    val database = Firebase.database
    val myRef = database.getReference()

    init {
        auth = Firebase.auth
    }

    fun readData() {
        _isLoading.value = true

        database.getReference("user").child(auth.currentUser?.uid.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    _highScore.value = user?.score
                    _isErrorFetchingUser.value = false
                }

                override fun onCancelled(error: DatabaseError) {
                    _isErrorFetchingUser.value = true
                }

            })


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val categories = snapshot.getValue(Category::class.java)
                println(categories)
                _categoryList.value = (categories?.category as ArrayList<CategoryX>)!!
                _isLoading.value = false
                _isErrorFetchingModels.value = false
            }

            override fun onCancelled(error: DatabaseError) {
                _isErrorFetchingModels.value = true
            }

        })
    }

    fun signOut() {
        auth.signOut()
        _naviagetToSign.value = true
    }

    fun onNavigatedToSign() {
        _naviagetToSign.value = false
    }
}





