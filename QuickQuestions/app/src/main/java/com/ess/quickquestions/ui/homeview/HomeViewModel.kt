package com.ess.quickquestions.ui.homeview

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ess.quickquestions.model.Category
import com.ess.quickquestions.model.CategoryX
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel : ViewModel() {


    private var _categoryList = MutableLiveData<ArrayList<CategoryX>>()
    val  categoryList : LiveData<ArrayList<CategoryX>>
        get() = _categoryList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _isErrorFetchingModels = MutableLiveData<Boolean>()
    val isErrorFetchingModels : LiveData<Boolean>
        get() = _isErrorFetchingModels


    val database = Firebase.database
    val myRef = database.getReference()

    fun readData(){
        _isLoading.value = true
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val categories = snapshot.getValue(Category::class.java)
                _categoryList.value = (categories?.category as ArrayList<CategoryX>)!!
                _isLoading.value = false
                _isErrorFetchingModels.value = false
            }

            override fun onCancelled(error: DatabaseError) {
                _isErrorFetchingModels.value = true
            }

        })
    }
}





