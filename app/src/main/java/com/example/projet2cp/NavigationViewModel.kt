package com.example.projet2cp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.database.FirebaseDatabase
import androidx.lifecycle.ViewModel
import com.example.projet2cp.data.Course
import com.example.projet2cp.screens.Activity
import com.example.projet2cp.screens.Commentaire
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow

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

    val activities = mutableStateListOf<Activity>()


    // Save a comment
    fun saveComment(activityId: String, comment: Commentaire) {
        Log.d("saveComment", "Saving comment for activityId: $activityId, comment: $comment")
        val userCommentsRef = FirebaseDatabase.getInstance().getReference("activities").child(activityId).child("comments")
        val commentId = userCommentsRef.push().key
        if (commentId!= null) {
            comment.commentId = commentId
            // Use setValue() to add the new comment without overwriting existing ones
            userCommentsRef.child(commentId).setValue(comment)
        }
    }

    // Function to get all comments for a specific activity and user
    val comments = MutableStateFlow<List<Commentaire>>(listOf())

    fun getComments(activityId: String) {
        val userCommentsRef = FirebaseDatabase.getInstance().getReference("activities").child(activityId).child("comments")
        userCommentsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val commentsList = dataSnapshot.children.mapNotNull { it.getValue(Commentaire::class.java) }
                comments.value = commentsList
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error...
            }
        })
    }

    fun deleteComment(activityId: String, commentId: String) {
        // Get reference to the comment
        val commentRef = activitiesRef.child(activityId).child("comments").child(commentId)

        // Remove the comment from Firebase
        commentRef.removeValue().addOnSuccessListener {
            // Successfully deleted comment
        }.addOnFailureListener { e ->
            // Handle any errors
        }
    }

    fun updateComment(activityId: String, commentId: String, newComment: Commentaire) {
        // Get reference to the comment
        val commentRef = activitiesRef.child(activityId).child("comments").child(commentId)

        // Update the comment in Firebase
        commentRef.setValue(newComment).addOnSuccessListener {
            // Successfully updated comment
        }.addOnFailureListener { e ->
            // Handle any errors
        }
    }
    init {
        // Initialize activities...
        activities.forEach { activity ->
            getComments(activity.id)
        }
    }

}