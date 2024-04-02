package com.example.projet2cp.screens

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projet2cp.R
import com.example.projet2cp.data.LoginViewModel
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyPurple



@Composable
fun ProfileScreen( mbiNavController: NavHostController,) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize(),
        color = if (isSystemInDarkTheme()) Black else Color.White

    ) {

            Column(modifier = Modifier
                .fillMaxHeight()
                .padding(
                    top = screenHeight * 0.05f,
                    start = screenWidth * 0.05f,
                    end = screenWidth * 0.05f,
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                 ) {
                CreateImageProfile(Modifier,screenWidth, screenHeight)
                Spacer(modifier = Modifier.height(5.dp))
                CreateInfo()
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    onClick = {
                        buttonClickedState.value= ! buttonClickedState.value

                    },
                    colors = ButtonDefaults.buttonColors(
                            containerColor = uiColor,
                            contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
               ) {
                    Text(
                        text = "Certificates",
                        fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                        fontSize = 14.sp
                    )

                }
                if (buttonClickedState.value){
                    Content(screenWidth,screenHeight)
                } else{
                    Box {}
                }
            }
    }

}


@Composable
private fun CreateInfo() {
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    Column (
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){ Text(
        text = "Meriem Benmadani",
        fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
        fontSize = 20.sp,
        color = uiColor
    )

        Text(
            text = "meriem@gmail.com",
            modifier = Modifier.padding(2.dp),
            fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
            fontSize = 13.6.sp,
            color = if (isSystemInDarkTheme()) Color.White else Black
        )
    }
}
@Composable
private fun CreateImageProfile(modifier: Modifier= Modifier, screenWidth: Dp, screenHeight: Dp) {
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    Surface(
        modifier = Modifier
            .size(screenWidth * 0.35f),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, uiColor),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)


    ) {
        Image(
            painter = painterResource(id = R.drawable.profile), contentDescription = "profile image",
            modifier = modifier.size(130.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CreateImageProject(modifier: Modifier= Modifier) {
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu

    Surface(
        modifier = Modifier
            .size(90.dp)
            .padding(2.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)


    ) {
        Image(
            painter = painterResource(id = R.drawable.certificate), contentDescription = "profile image",
            modifier = modifier.size(5.dp),
            contentScale = ContentScale.Crop
        )
    }
}



@Composable
fun  Content( screenWidth: Dp, screenHeight: Dp){
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu

    Box (modifier= Modifier
        .fillMaxSize()
        .fillMaxWidth()
        .padding(bottom = screenHeight * 0.07f, top = 5.dp, start = 5.dp, end = 5.dp)){
        Surface(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(12.dp)),
            border =  BorderStroke(width = 1.dp , color = uiColor),
            color = if (isSystemInDarkTheme()) Black else Color.White
        ) {
            Portfolio(data = listOf("English A0", "English A1" , "English A2"))

        }

    }
}


@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->

            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth()
                    .height(150.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                colors = CardDefaults.cardColors(containerColor =  if (isSystemInDarkTheme()) Color(0xFFB6B6B6) else Color(0xFFE7E0EC)),


                ) {
                Row(modifier= Modifier
                    .padding(10.dp)
                    .padding(16.dp)) {
                    CreateImageProject()
                    Column(modifier = Modifier
                        .padding(7.dp)
                        ) {
                        Text(text = item,
                            fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                            fontSize = 20.sp,
                            color = Black)
                        Text(
                            text = "You have done a great job",
                            modifier = Modifier.padding(2.dp),
                            fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                            fontSize = 11.sp,
                            color =  Black
                        )
                    }
                }
            }
        }
    }
}
  