package com.example.bondhucou.viewModel

import androidx.lifecycle.ViewModel
import com.example.bondhucou.data.BondhuModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow

class DonorViewModel : ViewModel() {
    private val db = FirebaseDatabase.getInstance().getReference("users")

    private val _donors = MutableStateFlow<List<BondhuModel>>(emptyList())
    val donors = _donors

    val isLoading = MutableStateFlow(false)

    fun search(group: String) {
        val cleanGroup = group.trim().uppercase()
        println("Starting search for blood group: '$cleanGroup'")
        isLoading.value = true

        val query = db.orderByChild("bloodGroup").equalTo(cleanGroup)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                println("Firebase query returned ${snapshot.childrenCount} results")
                val donorList = mutableListOf<BondhuModel>()
                for (donorSnapshot in snapshot.children) {
                    val donor = donorSnapshot.getValue(BondhuModel::class.java)
                    println("Donor found: $donor")
                    donor?.let { donorList.add(it) }
                }
                println("Found donors count: ${donorList.size}")
                _donors.value = donorList
                isLoading.value = false
            }

                override fun onCancelled(error: DatabaseError) {
                    println("Search cancelled: ${error.message}")
                    _donors.value = emptyList()
                    isLoading.value = false
                }
            })
        }
    }
