package com.example.projet2cp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.database.FirebaseDatabase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet2cp.data.Course
import com.example.projet2cp.screens.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.coroutines.flow.MutableStateFlow
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.MutableData
import com.google.firebase.firestore.core.Transaction

class NavigationViewModel : ViewModel() {

    private val db = FirebaseDatabase.getInstance()
    private val usersRef = db.getReference("users")
    private val activitiesRef = db.getReference("activities")
    val auth = FirebaseAuth.getInstance()


    var userName by mutableStateOf("")
    var verification by mutableStateOf("")
    fun fetchUserName() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.getReference("users")
                .child(userId)
                .get()
                .addOnSuccessListener { dataSnapshot ->
                    userName = dataSnapshot.child("username").value.toString()
                }
                .addOnFailureListener {
                    // Handle any errors
                }
        }
    }
    fun getUserEmail(): String {
        return auth.currentUser?.email ?: ""
    }

    val navigateToBuyScreen = mutableStateOf(false)
    var selectedLanguage by mutableStateOf("English")
    var selectedLevel by mutableStateOf("A0")
    var avatar = MutableStateFlow<Int?>(null)

    var addCourse:Course =         Course("Grammar" , "20/02/2024", R.drawable.gramma, 20, 12, 40000.0f)

    val purchasedCourses = mutableStateListOf<Course>()
    var selectedAvatar= null
    // Get Firebase instance

    // Save avatar when user selects it
    fun saveAvatar( userId: String,avatarId: Int) {
       usersRef.child(userId).child("avatar").setValue(avatarId).addOnFailureListener { e ->
            Log.e("Firebase", "Error writing to database", e)
        }
    }

    // Get avatar when you need it
    fun getAvatar(userId: String,) {
        usersRef.child(userId).child("avatar").get().addOnSuccessListener {
            val avatarId = it.value
            if (avatarId != null) {
                // Update the avatar state
                avatar.value = (avatarId as Long).toInt()
                Log.d("Firebase", "Successfully retrieved avatar: $avatarId")
            } else {
                Log.d("Firebase", "Avatar is null")
            }
        }.addOnFailureListener{
            // Handle any errors
            Log.e("Firebase", "Error retrieving avatar", it)
        }
    }
    fun updateUserName(newUserName: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.getReference("users")
                .child(userId)
                .child("username")
                .setValue(newUserName)
                .addOnSuccessListener {
                    userName = newUserName
                }
                .addOnFailureListener {
                    // Handle any errors
                }
        }
    }
    fun savePurchasedCourse(userId: String, course: Course) {
        usersRef.child(userId).child("courses").push().setValue(course)
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error writing to database", e)
            }
    }
    fun getPurchasedCourses(userId: String) {
        usersRef.child(userId).child("courses").get().addOnSuccessListener { dataSnapshot ->
            val courses = dataSnapshot.children.mapNotNull { it.getValue(Course::class.java) }
            purchasedCourses.clear()
            purchasedCourses.addAll(courses)
        }.addOnFailureListener { e ->
            Log.e("Firebase", "Error reading from database", e)
        }
    }
    fun createActivityReference(activity: Activity) {
        activitiesRef.child(activity.id).setValue(activity.id)

    }



}