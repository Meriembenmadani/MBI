import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.projet2cp.data.Course

class NavigationViewModel : ViewModel() {
    val navigateToBuyScreen = mutableStateOf(false)
    var selectedLanguage = ""
    var selectedLevel = ""
    var addCourse = mutableStateOf(false)
    val purchasedCourses = mutableStateListOf<Course>()
}