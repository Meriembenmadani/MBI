package com.example.projet2cp.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
import com.example.projet2cp.login.SocialMediaLogIn
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyPurple



@Composable
fun SigninScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel= viewModel()
) {
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
            val uiColor = if (isSystemInDarkTheme()) Color.White else Black

            TopSection(screenWidth, screenHeight,navController)
            Spacer(modifier = Modifier.height(screenHeight * 0.04f))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SinginSection(screenWidth, screenHeight)
                Spacer(modifier = Modifier.height(screenHeight * 0.03f)) // Increased to 3% of screen height
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Or continue with",
                        fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                        fontSize = 14.sp,
                        color = Color(0xFF64748B)
                    )
                    Spacer(modifier = Modifier.height(screenHeight * 0.03f)) // Increased to 3% of screen height
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SocialMediaLogIn(icon = R.drawable.google, text = "Google", modifier = Modifier.weight(1f)) {}
                        Spacer(modifier = Modifier.width(screenWidth * 0.03f))
                        SocialMediaLogIn(icon = R.drawable.facebook, text = "Facebook", modifier = Modifier.weight(1f)) {}
                    }
                    Spacer(modifier = Modifier.height(screenHeight * 0.04f))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Do you have account? ",
                                color = Color(0xFF94A3B8),
                                fontSize = 14.sp,
                                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = "Sign In",
                                color = if (isSystemInDarkTheme()) Color.White else Color(0xFF000000),
                                fontSize = 14.sp,
                                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.clickable { navController.navigate("Login") }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopSection(screenWidth: Dp, screenHeight: Dp,navController: NavHostController) {
    val uiCol = if (isSystemInDarkTheme()) MyPurple else MyBleu
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.09f))
        Text(
            text = "Sing UP",
            fontFamily = FontFamily(listOf(Font(R.font.poppins_extrabold))),
            fontSize = 32.sp,
            color =  if (isSystemInDarkTheme()) Color.White else MyBleu
        )
    }
}

@Composable
fun SinginSection(screenWidth: Dp, screenHeight: Dp, loginViewModel: LoginViewModel= viewModel()) {

    FunTextField(
        label = "User Name",
        onTextSelected = {
            loginViewModel.onEvent(UIEvent.UserNameChanged(it))
        } ,
        modifier = Modifier.fillMaxWidth(),
        errorStatus = loginViewModel.registrationUIState.value.userNameError
    )
    Spacer(modifier = Modifier.height(screenHeight * 0.03f))
    FunTextField(
        label = "Email",
        onTextSelected = {
            loginViewModel.onEvent(UIEvent.EmailChanged(it))
        } ,
        modifier = Modifier.fillMaxWidth(),
        errorStatus = loginViewModel.registrationUIState.value.emailError
    )
    Spacer(modifier = Modifier.height(screenHeight * 0.03f))
    FunPassWordField(
        label = "Password",
        onTextSelected = {
            loginViewModel.onEvent(UIEvent.PasswordChanged(it))
        } ,
        modifier = Modifier.fillMaxWidth(),
        errorStatus = loginViewModel.registrationUIState.value.passwordError
    )
    Spacer(modifier = Modifier.height(screenHeight * 0.03f))

    ButtonComponent(value = "Sign Up",
        onButtonClicked ={
            loginViewModel.onEvent(UIEvent.SignUpButtonClicked)
        },
        isEnabled = loginViewModel.allValidationPassed.value
    )
}
