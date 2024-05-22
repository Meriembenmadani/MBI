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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projet2cp.NavigationViewModel
import com.example.projet2cp.R
import com.example.projet2cp.data.LoginViewModel
import com.example.projet2cp.login.FunTextField
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyPurple


@Composable
fun Verifications(navController: NavHostController,loginViewModel: LoginViewModel = viewModel() ) {
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = if (isSystemInDarkTheme()) Black else Color.White

    ){
        val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    start = 32.dp,
                    end = 32.dp,
                    top = 66.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,

            ){
            Text(
                text = "New Password",
                fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                fontSize = 20.sp,
                color =  if (isSystemInDarkTheme()) MyPurple else Color(0xFF444444)
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(

                        top = 106.dp
                    ),
            ) {
                Text(
                    text = " Success! We've sent an email to your registered address with a link to reset your password. Please check your inbox (and spam/junk folder) for the email. Click the link provided in the email to create a new password.",
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                    fontSize = 15.2.sp,
                    color =  if (isSystemInDarkTheme()) Color.White else Color(0xB3000000)
                )
                Spacer(modifier = Modifier.height(30.dp))

                Spacer(modifier = Modifier.height(27.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    onClick = { navController.navigate("Login")},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = uiColor,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Sign in",
                        fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(300.dp))
                Resend(navController,loginViewModel)

            }





        }
    }
}
@Composable
fun Resend(navController: NavHostController,loginViewModel: LoginViewModel ) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter

    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            val viewModel1: NavigationViewModel = viewModel()
            val email = viewModel1.verification

            Text(
                text ="If you didn’t receive a link,",
                color = Color(0xFF94A3B8),
                fontSize = 14.sp,
                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                fontWeight = FontWeight.Normal
            )
            Text(
                text = " Resend",
                color =  if (isSystemInDarkTheme()) Color.White else  Color(0xFF000000),
                fontSize = 14.sp,
                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {  loginViewModel.sendPasswordResetEmail(email) }
            )
        }
    }
}