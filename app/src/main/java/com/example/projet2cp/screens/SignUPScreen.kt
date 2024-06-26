package com.example.projet2cp.screens

import android.app.Activity
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projet2cp.R
import com.example.projet2cp.data.SignUpUIEvent
import com.example.projet2cp.data.SignUpViewModel
import com.example.projet2cp.login.ButtonComponent
import com.example.projet2cp.login.FunPassWordField
import com.example.projet2cp.login.FunTextField
import com.example.projet2cp.login.SocialMediaLogIn
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyPurple
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun SigninScreen(
    navController: NavHostController,
    loginViewModel: SignUpViewModel= viewModel()
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val context = LocalContext.current
    val activity = context as? Activity

    val signInResultLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            loginViewModel.handleSignInResult(task)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
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
                SinginSection(screenWidth, screenHeight, navController = navController)
                Spacer(modifier = Modifier.height(screenHeight * 0.03f))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Or continue with",
                        fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                        fontSize = 14.sp,
                        color = Color(0xFF64748B)
                    )
                    Spacer(modifier = Modifier.height(screenHeight * 0.03f))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SocialMediaLogIn(icon = R.drawable.google, text = "Google", modifier = Modifier.weight(1f)) {loginViewModel.signInWithGoogle(signInResultLauncher)}

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

        if (loginViewModel.signUpInProgress.value){
        CircularProgressIndicator()}

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
fun SinginSection(screenWidth: Dp, screenHeight: Dp, loginViewModel: SignUpViewModel= viewModel(), navController: NavHostController) {
    FunTextField(
        label = "User Name",
        onTextSelected = {
            loginViewModel.onEvent(SignUpUIEvent.UserNameChanged(it),navController)
        } ,
        modifier = Modifier.fillMaxWidth(),
        errorStatus = loginViewModel.registrationUIState.value.userNameError
    )
    Spacer(modifier = Modifier.height(screenHeight * 0.03f))
     FunTextField(
        label = "Email",
        onTextSelected = {
            loginViewModel.onEvent(SignUpUIEvent.EmailChanged(it),navController)
        } ,
        modifier = Modifier.fillMaxWidth(),
        errorStatus = loginViewModel.registrationUIState.value.emailError
    )
    Spacer(modifier = Modifier.height(screenHeight * 0.03f))
    FunPassWordField(
        label = "Password",
        onTextSelected = {
            loginViewModel.onEvent(SignUpUIEvent.PasswordChanged(it),navController)
        } ,
        modifier = Modifier.fillMaxWidth(),
        errorStatus = loginViewModel.registrationUIState.value.passwordError
    )
    Spacer(modifier = Modifier.height(screenHeight * 0.03f))
    ButtonComponent(value = "Sign Up",
        onButtonClicked ={
            loginViewModel.onEvent(SignUpUIEvent.SignUpButtonClicked,navController)

        },
        isEnabled = loginViewModel.allValidationPassed.value
    )
}

