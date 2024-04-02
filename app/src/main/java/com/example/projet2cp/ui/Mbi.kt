package com.example.projet2cp.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projet2cp.screens.ForgotPassword
import com.example.projet2cp.screens.LoginScreen
import com.example.projet2cp.screens.ProfileScreen
import com.example.projet2cp.screens.SigninScreen
import com.example.projet2cp.ui.forgotPassword.Verification
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyGray
import com.example.projet2cp.ui.theme.MyPurple

@Preview
@Composable
fun registration(){

    val navController = rememberNavController()
    NavHost(navController = navController ,
        startDestination = "Login"  ){
        composable("Login"){
            LoginScreen(navController = navController)
        }
        composable("Signin"){
            SigninScreen(navController = navController)
        }
        composable("forgotPassword"){
            ForgotPassword(navController = navController)
        }
        composable("MBI"){
            Mbi(navController = navController)
        }
    }
}
@Composable
fun Mbi(navController: NavHostController) {

    Surface (
        modifier = Modifier.fillMaxSize()

    ){
        val mbiNavController = rememberNavController()

        Scaffold(
            bottomBar = {
             BottomBar(mbiNavController)
            },

        ){ it

            /*NavHost(navController = mbiNavController ,
                startDestination = "Profile"  ){
                composable("Profile"){
                    ProfileScreen(mbiNavController = mbiNavController)
                }

                }*/
            ProfileScreen()
            }

        }
    }

@Composable
fun BottomBar(mbiNavController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val uiCol = if (isSystemInDarkTheme()) MyPurple else MyBleu
        var profile by remember { mutableStateOf(true) }
        var search by remember { mutableStateOf(false) }
        var course by remember { mutableStateOf(false) }
        var infarmations by remember { mutableStateOf(false) }
            IconButton(
                onClick = {
                    profile  = true
                    search  = false
                    course = false
                    infarmations= false

                }) {
                Icon(
                    painter = painterResource(id = com.example.projet2cp.R.drawable.loginicon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (profile){uiCol}else {if (isSystemInDarkTheme()) Color.White else MyGray}
                )
            }
            IconButton(onClick = {
                profile  = false
                search  = true
                course = false
                infarmations= false
            }) {
                Icon(
                    painter = painterResource(id=  com.example.projet2cp.R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (search){uiCol}else {if (isSystemInDarkTheme()) Color.White else MyGray}
                )
            }
            IconButton(onClick = {
                profile  = false
                search  = false
                course = true
                infarmations= false
            }) {
                Icon(
                    painter = painterResource(id=  com.example.projet2cp.R.drawable.cours),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint =  if (course){uiCol}else {if (isSystemInDarkTheme()) Color.White else MyGray}
                )
            }
            IconButton(onClick = {
                profile  = false
                search  = false
                course = false
                infarmations= true
            }) {
                Icon(
                    painter = painterResource(id=  com.example.projet2cp.R.drawable.informations),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (infarmations){uiCol}else {if (isSystemInDarkTheme()) Color.White else MyGray}
                )
            }


    }
}