package com.example.projet2cp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.projet2cp.screens.ProfileScreen
import com.example.projet2cp.ui.Mbi
import com.example.projet2cp.ui.registration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

           Mbi(navController = navController)

        }
    }
}

