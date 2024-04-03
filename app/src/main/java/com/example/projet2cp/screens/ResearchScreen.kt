package com.example.projet2cp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .padding(top = screenHeight * 0.05f,
                start = screenWidth * 0.05f,
                end = screenWidth * 0.05f,)
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
            TextFieldR(modifier = Modifier.fillMaxWidth(),"Language")
        }

    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TextFieldR(
       modifier: Modifier = Modifier,
        label: String,

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
                IconButton(onClick = { /*TODO*/ }) {
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
