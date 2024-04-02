package com.example.projet2cp.screens

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projet2cp.R
import com.example.projet2cp.data.LoginViewModel
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyPurple

@Composable
fun ProfileScreen(
    mbiNavController: NavHostController,
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
            val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu

            Surface(
                modifier = Modifier
                    .size(screenWidth * 0.4f, screenHeight * 0.4f)
                    .padding(
                        top = screenHeight * 0.05f,
                        bottom = screenHeight * 0.05f
                    ),
                shape = CircleShape,
                border = BorderStroke(2.dp, uiColor),
                shadowElevation = 4.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)


            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile image",
                    modifier = Modifier.size(screenWidth * 0.4f, screenHeight * 0.4f),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}