package com.ess.quickquestions.ui.homeview

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

/*    public val categoryList: ArrayList<String> = arrayListOf(
        "C#",
        "Flutter",
        "Dart",
        "Xamarin",
        "C#",
        "Flutter",
        "Dart",
        "Xamarin",
        "C#",
        "Flutter",
        "Dart",
        "Xamarin"
    )*/

    private var _categoryList = MutableLiveData<ArrayList<CategoryX>>()
    val  categoryList : LiveData<ArrayList<CategoryX>>
        get() = _categoryList

/*
    public var categoryList: ArrayList<CategoryX> = arrayListOf()
*/

    val database = Firebase.database
    val myRef = database.getReference()

    fun readData(){
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val categories = snapshot.getValue(Category::class.java)
                _categoryList.value = (categories?.category as ArrayList<CategoryX>)!!
/*
                categoryList = (categories?.category as ArrayList<CategoryX>?)!!
*/
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}





