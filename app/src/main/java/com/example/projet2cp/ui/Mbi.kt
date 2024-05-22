package com.example.projet2cp.ui

import com.example.projet2cp.NavigationViewModel
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projet2cp.screens.BuyScreen
import com.example.projet2cp.screens.ForgotPassword
import com.example.projet2cp.screens.HomePage
import com.example.projet2cp.screens.LoginScreen
import com.example.projet2cp.screens.MyCoursesScreen
import com.example.projet2cp.screens.NewPassword
import com.example.projet2cp.screens.PaymentScreen
import com.example.projet2cp.screens.ProfileScreen
import com.example.projet2cp.screens.ResearchScreen
import com.example.projet2cp.screens.SigninScreen
import com.example.projet2cp.screens.SuccessfulPaymentScreen
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyGray
import com.example.projet2cp.ui.theme.MyPurple

@Preview
@Composable
fun Registration(){

    val navController = rememberNavController()
    NavHost(navController = navController ,
        startDestination = "Signin"  ){
        composable("Login"){
            LoginScreen(navController = navController)
        }
        composable("Signin"){
            SigninScreen(navController = navController)
        }
        composable("forgotPassword"){
            ForgotPassword(navController = navController)
        }
        composable("NewPassword"){
          NewPassword(navController =navController)
        }
        composable("MBI"){
            Mbi()
        }
    }
}
@Composable
fun Mbi() {
    val viewModel: NavigationViewModel = viewModel()
    val uiColor = if (isSystemInDarkTheme()) Black else White
    Surface (
        modifier = Modifier.fillMaxSize()

    ){
        val mbiNavController = rememberNavController()

        Scaffold(
            bottomBar = {
             BottomBar(mbiNavController)
            },
            containerColor = uiColor

        ){ it

            NavHost(navController = mbiNavController ,
                startDestination = "HomeScreen" ){
                composable("Profile"){
                    ProfileScreen(mbiNavController = mbiNavController)
                }
                composable("Research"){
                    ResearchScreen(mbiNavController = mbiNavController, viewModel )
                }
                composable("buyScreen") {
                    BuyScreen(mbiNavController,viewModel)
                }
                composable("PaymentScreen") {
                   PaymentScreen(navController = mbiNavController,viewModel)
                }
                composable("SuccessfulPaymentScreen") {
                    SuccessfulPaymentScreen(mbiNavController = mbiNavController)
                }
                composable("MyCoursesScreen"){
                    MyCoursesScreen(navController = mbiNavController, viewModel = viewModel)
                }
                composable("HomeScreen"){
                    HomePage(mbiNavController= mbiNavController, viewModel = viewModel)
                }

                }
            }

        }
    }

@Composable
fun BottomBar(mbiNavController: NavHostController) {
    val uiColor = if (isSystemInDarkTheme()) Black else White

    Surface(color = uiColor) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val uiCol = if (isSystemInDarkTheme()) MyPurple else MyBleu
        var profile by remember { mutableStateOf(false) }
        var search by remember { mutableStateOf(false) }
        var course by remember { mutableStateOf(false) }
        var home by remember { mutableStateOf(true) }
        val bgColor = if (isSystemInDarkTheme()) White else MyGray
        IconButton(
            onClick = {
                home = true
                profile  = false
                search  = false
                course = false
                mbiNavController.navigate("HomeScreen")

            }) {
            Icon(
                painter = painterResource(id = com.example.projet2cp.R.drawable.home),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = if (home){uiCol}else { bgColor }
            )
        }


            IconButton(onClick = {
                home = false
                profile  = false
                search  = true
                course = false
                mbiNavController.navigate("Research")
            }) {
                Icon(
                    painter = painterResource(id=  com.example.projet2cp.R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (search){uiCol}else {bgColor}
                )
            }
            IconButton(onClick = {
                home = false
                profile  = false
                search  = false
                course = true
                mbiNavController.navigate("MyCoursesScreen")

            }) {
                Icon(
                    painter = painterResource(id=  com.example.projet2cp.R.drawable.cours),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint =  if (course){uiCol}else {bgColor}
                )
            }
        IconButton(
            onClick = {
                home = false
                profile  = true
                search  = false
                course = false
                mbiNavController.navigate("Profile")

            }) {
            Icon(
                painter = painterResource(id = com.example.projet2cp.R.drawable.loginicon),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = if (profile){uiCol}else { bgColor }
            )
        }


    }
    }
}