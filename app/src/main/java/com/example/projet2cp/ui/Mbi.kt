package com.example.projet2cp.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projet2cp.screens.ForgotPassword
import com.example.projet2cp.screens.LoginScreen
import com.example.projet2cp.screens.SigninScreen

@Preview
@Composable
fun Mbi() {
    Surface (
        modifier = Modifier.fillMaxSize()

    ){

        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
             BottomBar(navController)
            }
        ){ it

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
            }
        }
    }
}
@Composable
fun BottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

            IconButton(onClick = { /* Handle navigation or selection here */ }) {
                Icon(
                    painter = painterResource(id = com.example.projet2cp.R.drawable.loginicon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.LightGray,
                )
            }
            IconButton(onClick = { /* Handle navigation or selection here */ }) {
                Icon(
                    painter = painterResource(id=  com.example.projet2cp.R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.LightGray,
                )
            }
            IconButton(onClick = { /* Handle navigation or selection here */ }) {
                Icon(
                    painter = painterResource(id=  com.example.projet2cp.R.drawable.cours),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.LightGray,
                )
            }
            IconButton(onClick = { /* Handle navigation or selection here */ }) {
                Icon(
                    painter = painterResource(id=  com.example.projet2cp.R.drawable.informations),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.LightGray,
                )
            }


    }
}