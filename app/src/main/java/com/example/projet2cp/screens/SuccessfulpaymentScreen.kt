package com.example.projet2cp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projet2cp.R
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyPurple

@Composable
fun SuccessfulPaymentScreen( mbiNavController: NavHostController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize(),
        color = if (isSystemInDarkTheme()) Black else Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(
                    top = screenHeight * 0.05f,
                    start = screenWidth * 0.05f,
                    end = screenWidth * 0.05f,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Image(painter = painterResource(id = R.drawable.successfulpayment), contentDescription = "")
        }
    }
}