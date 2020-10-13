package com.ess.quickquestions.ui.homeview

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

    public var categoryList: ArrayList<CategoryX> = arrayListOf()

    val database = Firebase.database
    val myRef = database.getReference()

    fun readData(){
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val categories = snapshot.getValue(Category::class.java)
                categoryList = (categories?.category as ArrayList<CategoryX>?)!!
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}





