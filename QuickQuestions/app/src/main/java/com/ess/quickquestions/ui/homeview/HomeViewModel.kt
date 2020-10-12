package com.ess.quickquestions.ui.homeview

import androidx.lifecycle.ViewModel
import com.ess.quickquestions.model.Categories
import com.ess.quickquestions.model.Category
import com.ess.quickquestions.model.Questions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class HomeViewModel : ViewModel() {

    public val categoryList: ArrayList<String> = arrayListOf(
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
    )

    val database = Firebase.database
    val myRef = database.getReference("categories")

    fun getCategories() {
        val userListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val myCategory = arrayListOf<Category>()
                val category = dataSnapshot.getValue<Array<Category>>()
                println(category)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                // ...
            }
        }
        myRef.addValueEventListener(userListener)
    }

/*    fun saveValue(){
*//*        val user1 = User(name = "Ebubekir", surname = "Sezer")
        val user2 = User(name = "Ömer", surname = "Sezer")
        val user3 = User(name = "Erol", surname = "Sezer")*//*
    *//*    val question = Questions(question = "language?",option_1 = "kotlin",option_2 = "kotlin",option_3 = "kotlin",option_4 = "kotlin",answer = "kotlin")
        val category = Category(name = "ebu",questions = question)*//*
        database.reference.child("users").child("user1").setValue(user1)
        database.reference.child("users").child("user2").setValue(user2)
        database.reference.child("users").child("user3").setValue(user3)
*//*
        database.reference.child("categories").child("catehory_ebu").setValue(category)
*//*
    }*/
}





