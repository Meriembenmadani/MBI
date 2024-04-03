package com.example.projet2cp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.projet2cp.R
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyGray
import com.example.projet2cp.ui.theme.MyPurple

@Preview
@Composable
fun ResearchScreen() {
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
        color = uiColor

    ) {

        Column(modifier = Modifier
            .fillMaxHeight()
           ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            TopSectionR(screenHeight,screenWidth)
            ResearchSection(screenHeight,screenWidth)
        }
    }
}

@Composable
fun ResearchSection(screenHeight: Dp,screenWidth:Dp) {
    val bgColor =  if (isSystemInDarkTheme()) Black else Color.White
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize(),
        color = bgColor,
        shape = (RoundedCornerShape(50.dp))
    ) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(
                top = screenHeight * 0.05f,
                start = screenWidth * 0.05f,
                end = screenWidth * 0.05f,
            )
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = "Choose a language and level",
                fontFamily = FontFamily(listOf(Font(R.font.poppins_extrabold))),
                fontSize = 18.sp,
                color = uiColor
            )
            TextFieldR(
                modifier = Modifier.fillMaxWidth(),
                "Language",
                onButtonClick = {buttonClickedState.value= ! buttonClickedState.value}
            )

            if (buttonClickedState.value){
                Box(
                    contentAlignment = Alignment.TopCenter
                ) {
                    Dialog(
                        onDismissRequest = {
                            buttonClickedState.value=false
                        }) {
                       Languages(screenHeight)
                    }
                }

            } else{
                Box {}
            }
        }

    }

}

@Preview
@Composable
fun Languages(screenHeight:Dp ) {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
  Column (){


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
            Language(data = listOf("English A0", "English A1" , "English A2"))

        }

    }
  }

}

@Preview
@Composable
fun Language(data: List<String>) {
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

@Composable
private fun CreateImageFlag(modifier: Modifier= Modifier) {
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
@OptIn(ExperimentalMaterial3Api::class)
fun TextFieldR(
       modifier: Modifier = Modifier,
        label: String,
       onButtonClick: () -> Unit

    ) {

        val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
        val password = remember { mutableStateOf("") }
        val localFocusManager = LocalFocusManager.current



           androidx.compose.material3.TextField(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 2.dp,
                    color = uiColor,
                    shape = RoundedCornerShape(12.dp)
                ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions {
                localFocusManager.clearFocus()
            },
            shape = RectangleShape,
            maxLines = 1,

            singleLine = true,
            value = password.value,
            onValueChange = {
                password.value = it

            },

            label = {
                Text(
                    text = label,
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                    color = if (isSystemInDarkTheme()) Color.White else Color(0xFF475569),

                    )
            },

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,
                cursorColor = uiColor,
                errorTextColor = Color.Red,
                focusedTextColor = if (isSystemInDarkTheme()) Color.White else MyGray,
                unfocusedTextColor = if (isSystemInDarkTheme()) Color.White else MyGray,


                ),
            trailingIcon = {
                IconButton(onClick = { onButtonClick }) {
                    Image(
                        painter = if (isSystemInDarkTheme()) painterResource(id = R.drawable.rd) else painterResource(id = R.drawable.r),
                        contentDescription = "Icon Description"
                    )
                }
            }

        )
    }


@Composable
fun TopSectionR(screenHeight: Dp, screenWidth:Dp) {
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    Column(modifier = Modifier
        .fillMaxWidth()

        .padding(
            start = screenWidth * 0.05f,
            end = screenWidth * 0.05f,
            top = screenHeight * 0.05f,

            ),
        )
    {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = screenHeight * 0.0f,
                    bottom = screenHeight * 0.025f
                )
                .size(screenWidth * 0.16f, screenHeight * 0.2f),
            painter = if (isSystemInDarkTheme()) painterResource(id = R.drawable.studentdark) else painterResource(id = R.drawable.student),
            contentDescription = "",
            alignment = Alignment.BottomEnd
        )
        Text(
            text = "Hi Student",
            fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
            fontSize = 32.24.sp,
            color = Color.White
        )
    }
}
