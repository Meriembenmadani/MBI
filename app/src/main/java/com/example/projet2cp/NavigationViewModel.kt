import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.lifecycle.ViewModel
import com.example.projet2cp.R
import com.example.projet2cp.data.Course

class NavigationViewModel : ViewModel() {
    val navigateToBuyScreen = mutableStateOf(false)
    var selectedLanguage = ""
    var selectedLevel = ""
    var addCourse:Course =         Course("Grammar" , "20/02/2024", R.drawable.gramma, 20, 12, 40000.0f)

    val purchasedCourses = mutableStateListOf<Course>()

}