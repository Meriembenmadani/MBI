package com.example.projet2cp.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavHostController
import com.example.projet2cp.R
import com.example.projet2cp.login.FunPassWordField
import com.example.projet2cp.login.FunTextField
import com.example.projet2cp.login.SocialMediaLogIn
import com.example.projet2cp.ui.theme.MyBlue


@Composable
fun SigninScreen(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    start = screenWidth * 0.05f, // 5% of screen width
                    end = screenWidth * 0.05f // 5% of screen width
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSection(screenWidth, screenHeight,navController)
            Spacer(modifier = Modifier.height(screenHeight * 0.04f)) // Increased to 4% of screen height
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
                        Spacer(modifier = Modifier.width(screenWidth * 0.03f)) // Increased to 3% of screen width
                        SocialMediaLogIn(icon = R.drawable.facebook, text = "Facebook", modifier = Modifier.weight(1f)) {}
                    }
                    Spacer(modifier = Modifier.height(screenHeight * 0.03f)) // Increased to 3% of screen height
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Do you have account?",
                                color = Color(0xFF94A3B8),
                                fontSize = 14.sp,
                                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = "Log In",
                                color = Color(0xFF000000),
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.09f))
        Text(
            text = "Sing In",
            fontFamily = FontFamily(listOf(Font(R.font.poppins_extrabold))),
            fontSize = 32.sp, // Adjusted to a fixed size for consistency
            color = MyBlue
        )
    }
}

@Composable
fun SinginSection(screenWidth: Dp, screenHeight: Dp) {

    FunTextField(label = "User Name", trailing = "", modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(screenHeight * 0.03f))
    FunTextField(label = "Email", trailing = "", modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(screenHeight * 0.03f))
    FunPassWordField(label = "Password",  modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(screenHeight * 0.03f))
    val isEnableed : Boolean = false
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = MyBlue,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        enabled = isEnableed
    ) {
        Text(
            text = "Sing In",
            fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
            fontSize = 14.sp
        )
    }
}
