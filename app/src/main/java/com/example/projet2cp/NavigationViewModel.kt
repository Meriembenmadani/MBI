import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    val navigateToBuyScreen = mutableStateOf(false)
    var selectedLanguage = ""
    var selectedLevel = ""
}