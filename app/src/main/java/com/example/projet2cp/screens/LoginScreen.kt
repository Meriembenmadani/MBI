package com.example.projet2cp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projet2cp.R
import com.example.projet2cp.data.LoginViewModel
import com.example.projet2cp.data.UIEvent
import com.example.projet2cp.login.ButtonComponent
import com.example.projet2cp.login.FunPassWordField
import com.example.projet2cp.login.FunTextField
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyPurple

@Composable
fun LoginScreen(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = if (isSystemInDarkTheme()) Black else Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    start = screenWidth * 0.05f,
                    end = screenWidth * 0.05f
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSection(screenWidth, screenHeight)
            Spacer(modifier = Modifier.height(screenHeight * 0.03f))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginSection(screenWidth, screenHeight,navController)
                Spacer(modifier = Modifier.height(screenHeight * 0.02f))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    /*Text(
                        text = "Or continue with",
                        fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                        fontSize = 14.sp,
                        color = Color(0xFF64748B)
                    )
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f)) // Increased to 2% of screen height
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SocialMediaLogIn(icon = R.drawable.google, text = "Google", modifier = Modifier.weight(1f)) {}
                        Spacer(modifier = Modifier.width(screenWidth * 0.02f)) // Increased to 2% of screen width
                        SocialMediaLogIn(icon = R.drawable.facebook, text = "Facebook", modifier = Modifier.weight(1f)) {}
                    }*/
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f)) // Increased to 2% of screen height
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Don't have account? ",
                                color = Color(0xFF94A3B8),
                                fontSize = 14.sp,
                                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = "Sign UP",
                                color = if (isSystemInDarkTheme()) Color.White else Color(0xFF000000),
                                fontSize = 14.sp,
                                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.clickable { navController.navigate("Signin") }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopSection(screenWidth: Dp, screenHeight: Dp) {
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(
                    top = screenHeight * 0.05f,
                    bottom = screenHeight * 0.025f
                )
                .size(screenWidth * 0.4f, screenHeight * 0.2f),
            painter = if (isSystemInDarkTheme()) painterResource(id = R.drawable.darklogo) else painterResource(id = R.drawable.singin),
            contentDescription = "",
        )
        Text(
            text = "Sign In",
            fontFamily = FontFamily(listOf(Font(R.font.poppins_extrabold))),
            fontSize = 32.sp,
            color = if (isSystemInDarkTheme()) Color.White else MyBleu
        )
    }
}

@Composable
fun LoginSection(screenWidth: Dp, screenHeight: Dp,navController: NavHostController,loginViewModel: LoginViewModel = viewModel()) {
    FunTextField(
        label = "Email",
        onTextSelected = {} ,
        modifier = Modifier.fillMaxWidth(),
        errorStatus = loginViewModel.registrationUIState.value.userNameError
        )
    Spacer(modifier = Modifier.height(screenHeight * 0.02f))
    FunPassWordField(
        label = "Password",
        onTextSelected = {},
        modifier = Modifier.fillMaxWidth(),
        errorStatus = loginViewModel.registrationUIState.value.passwordError
    )
    Spacer(modifier = Modifier.height(screenHeight * 0.02f))
    Text(
        text = "Forgot your password?",
        fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
        fontSize = 14.sp,
        color = Color(0xFF64748B),
        modifier = Modifier.clickable { navController.navigate("forgotPassword") }
    )
    Spacer(modifier = Modifier.height(screenHeight * 0.02f))
    ButtonComponent(value = "Sign In",
        onButtonClicked ={
            loginViewModel.onEvent(UIEvent.SignUpButtonClicked)
        }
    )
}

