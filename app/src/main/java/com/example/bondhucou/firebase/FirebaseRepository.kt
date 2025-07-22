package com.example.bondhucou.firebase

import com.example.bondhucou.data.BondhuModel
import com.google.firebase.database.FirebaseDatabase

object FirebaseRepository {
    private val db = FirebaseDatabase.getInstance().getReference("users")

    fun addUser(user: BondhuModel) {
        val id= db.push().key!!
        db.child(id).setValue(user.copy(
            id=id
        ))
    }
}