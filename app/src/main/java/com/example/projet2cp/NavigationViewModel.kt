import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.firebase.database.FirebaseDatabase
import androidx.lifecycle.ViewModel
import com.example.projet2cp.R
import com.example.projet2cp.data.Course
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationViewModel : ViewModel() {


    private val db = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()


    var userName by mutableStateOf("")

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
    var selectedAvatar= R.drawable.profile
    // Get Firebase instance
    private val database = FirebaseDatabase.getInstance()

    // Get reference to the database
    private val myRef = database.getReference("users")

    // Save avatar when user selects it
    fun saveAvatar( avatarId: Int) {
        myRef.child("avatar").setValue(avatarId).addOnFailureListener { e ->
            Log.e("Firebase", "Error writing to database", e)
        }
    }

    // Get avatar when you need it
    fun getAvatar() {
        myRef.child("avatar").get().addOnSuccessListener {
            val avatarId = it.value as Long
            // Update the avatar state
            avatar.value = avatarId.toInt()
            Log.d("Firebase", "Successfully retrieved avatar: $avatarId")
        }.addOnFailureListener{
            // Handle any errors
            Log.e("Firebase", "Error retrieving avatar", it)
        }
    }
}