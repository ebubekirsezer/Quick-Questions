package com.ess.quickquestions.ui.finishview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _loadingProcess = MutableLiveData<Boolean>()
    val loadingProcess: LiveData<Boolean>
        get() = _loadingProcess

    private val _isError = MutableLiveData<Boolean>()
    val isError : LiveData<Boolean>
        get() = _isError

    private val _isUpdated = MutableLiveData<Boolean>()
    val isUpdated: LiveData<Boolean>
        get() = _isUpdated

    init {
        auth = Firebase.auth
        database = Firebase.database
    }

    fun updateScore(score: Int, dbScore: Int?) {
        if (dbScore != null) {
            if (score > dbScore) {
                _loadingProcess.value = true
                _isUpdated.value = false
                database.getReference("user").child(auth.currentUser?.uid.toString())
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            var user = snapshot.getValue(User::class.java)
                            user?.score = score
                            val childUpdates = hashMapOf<String, Any>(
                                "user/${auth.currentUser?.uid.toString()}/score" to score
                            )
                            database.reference.updateChildren(childUpdates)
                            _loadingProcess.value = false
                            _isUpdated.value = true
                            _isError.value = false
                        }

                        override fun onCancelled(error: DatabaseError) {
                            _loadingProcess.value = false
                            _isUpdated.value = true
                            _isError.value = true
                        }
                    })
            }
        }
    }
}