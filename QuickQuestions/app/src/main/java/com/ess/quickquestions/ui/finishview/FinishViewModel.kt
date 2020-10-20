package com.ess.quickquestions.ui.finishview

import androidx.lifecycle.ViewModel
import com.ess.quickquestions.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FinishViewModel : ViewModel() {

    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    var user: User? = User()

    init {
        auth = Firebase.auth
        database = Firebase.database
    }

    fun updateScore(score: Int, dbScore: Int?) {
        if (dbScore != null) {
            if (score > dbScore) {
                database.getReference("user").child(auth.currentUser?.uid.toString())
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            var user = snapshot.getValue(User::class.java)
                            user?.score = score
                            val childUpdates = hashMapOf<String, Any>(
                                "user/${auth.currentUser?.uid.toString()}/score" to score
                            )

                            database.reference.updateChildren(childUpdates)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    })
            }
        }
    }
}