package com.example.projet2cp.screens

import com.example.projet2cp.NavigationViewModel
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import com.example.projet2cp.R
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.EnabledGray
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyPurple


@Composable
fun ResearchScreen(mbiNavController: NavHostController,viewModel: NavigationViewModel) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu


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
            ResearchSection(screenHeight,screenWidth,mbiNavController,viewModel)
        }
    }
}

@Composable
fun ResearchSection(screenHeight: Dp,screenWidth:Dp,navController: NavHostController,viewModel: NavigationViewModel) {
    val bgColor =  if (isSystemInDarkTheme()) Black else Color.White
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    val languageState = remember { mutableStateOf("Language") }
    val levelState = remember { mutableStateOf("Level") }
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    val isButtonEnabled = languageState.value != "Language" && levelState.value != "Level"
    val levelButton = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize(),
        color = bgColor,
        shape = (RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp))
    ) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(
                top = screenHeight * 0.09f,
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
            Choose(uiColor, bgColor, languageState, buttonClickedState)

            if (buttonClickedState.value){

                Box(
                    contentAlignment = Alignment.TopCenter
                ) {
                    Popup(
                        alignment = Alignment.TopStart,
                        offset = IntOffset(0, 50),
                        onDismissRequest = { buttonClickedState.value = false }
                    ) {
                        Languages(screenHeight, language = languageState, buttonClickedState = buttonClickedState)
                    }
                        }

                }

             else{
                Box {}
            }
            Choose(uiColor = uiColor, bgColor = bgColor, languageState = levelState, buttonClickedState =levelButton )
            if (levelButton.value){

                Box(
                    contentAlignment = Alignment.TopCenter
                ) {
                    Popup(
                        alignment = Alignment.TopStart,
                        offset = IntOffset(0, 50),
                        onDismissRequest = {levelButton.value = false }
                    ) {
                        Levels(screenHeight, language = levelState, buttonClickedState = levelButton)
                    }
                }

            }

            else{
                Box {}
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .run {
                    padding(

                        end = 13.dp,
                        start = 13.dp
                    )},
                onClick = {
                    viewModel.selectedLanguage = languageState.value
                    viewModel.selectedLevel = levelState.value
                    viewModel.navigateToBuyScreen.value = true
                    navController.navigate("buyScreen")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =  if (isButtonEnabled) uiColor else EnabledGray,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = isButtonEnabled
            ) {
                Text(
                    text = "Research",
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                    fontSize = 14.sp
                )

            }
        }

    }

}

@Composable
private fun Choose(
    uiColor: Color,
    bgColor: Color,
    languageState: MutableState<String>,
    buttonClickedState: MutableState<Boolean>
) {
    Card(
        modifier = Modifier.run {
            padding(
                top = 20.dp,
                end = 13.dp,
                start = 13.dp
            )
                .fillMaxWidth()
                .height(43.dp)
                .border(
                    width = 2.dp,
                    color = uiColor,
                    shape = RoundedCornerShape(12.dp)
                )
        },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = bgColor
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                text = languageState.value,
                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                color = if (isSystemInDarkTheme()) Color.White else Color(0xFF475569),
            )
            IconButton(onClick = { buttonClickedState.value = !buttonClickedState.value }) {
                Image(
                    painter = if (isSystemInDarkTheme()) painterResource(id = R.drawable.rd) else painterResource(
                        id = R.drawable.r
                    ),
                    contentDescription = "Icon Description"
                )
            }

        }

    }
}


@Composable
fun Languages(screenHeight:Dp,language:MutableState<String>,buttonClickedState:MutableState<Boolean>) {

    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
  Column (
      modifier = Modifier.padding(top=1.dp),

  ){


    Box (modifier= Modifier
        .height(300.dp)
        .width(240.dp)
        .padding(bottom = screenHeight * 0.07f, top = 5.dp, start = 5.dp, end = 5.dp)){
        Surface(modifier = Modifier
            .padding(end = 10.dp, start =10.dp )
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(12.dp)),
            border =  BorderStroke(width = 1.dp , color = uiColor),
            color = if (isSystemInDarkTheme()) Black else Color.White
        ) {
            Language(data = listOf(
                "English",
                "French" ,
                "Russian",
                "German",
                "Spanish",
                "Chinese",
                "Korean",
                "Italian",
                "Japanese"
            ), flag = listOf(
                R.drawable.english,
                R.drawable.french,
                R.drawable.russian,
                R.drawable.german,
                R.drawable.spanish,
                R.drawable.chinese,
                R.drawable.korean,
                R.drawable.italian,
                R.drawable.japanese
            ), language,buttonClickedState )

        }

    }
  }

}


@Composable
fun Language(data: List<String> , flag: List<Int>,language: MutableState<String>,buttonClickedState:MutableState<Boolean>)  {
    LazyColumn {
        var i = -1
        items(data) { item ->
            i++

            Card(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        end = 13.dp,
                        start = 13.dp
                    )
                    .width(200.dp)
                    .height(43.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSystemInDarkTheme()) Color(
                        0xFFB6B6B6
                    ) else Color(0xFFE7E0EC)
                ),


                ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        CreateImageFlag(flag.get(i))
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = item,
                            fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                            fontSize = 20.sp,
                            color = Black,
                            modifier = Modifier.clickable {
                                language.value = item
                                buttonClickedState.value = false
                            }

                        )


                    }
                }
            }
        }
    }
}


@Composable
private fun CreateImageFlag( imageId:Int) {
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu

    Surface(
        modifier = Modifier
            .size(40.dp)
            .padding(2.dp),
        shape = RoundedCornerShape(12.dp),

        color = Color.Transparent


    ) {
        Image(
            painter = painterResource(id = imageId), contentDescription = "profile image",
            modifier = Modifier.size(5.dp),
            contentScale = ContentScale.Crop
        )
    }
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

@Composable
fun Levels(screenHeight:Dp,language:MutableState<String>,buttonClickedState:MutableState<Boolean>) {

    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    Column (
        modifier = Modifier.padding(top=1.dp),

        ){


        Box (modifier= Modifier
            .height(300.dp)
            .width(240.dp)
            .padding(bottom = screenHeight * 0.07f, top = 5.dp, start = 5.dp, end = 5.dp)){
            Surface(modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
                shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                border =  BorderStroke(width = 1.dp , color = uiColor),
                color = if (isSystemInDarkTheme()) Black else Color.White
            ) {
                Levele(data = listOf(
                    "A0",
                    "A1" ,
                    "A2",
                    "B1",
                    "B2",
                    "C1",
                    "C2",

                ), language,buttonClickedState )

            }

        }
    }

}

@Composable
fun Levele(data: List<String> , language: MutableState<String>,buttonClickedState:MutableState<Boolean>)  {
    LazyColumn {

        items(data) { item ->


            Card(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        end = 13.dp,
                        start = 13.dp
                    )
                    .width(200.dp)
                    .height(43.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSystemInDarkTheme()) Color(
                        0xFFB6B6B6
                    ) else Color(0xFFE7E0EC)
                ),


                ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {




                        Text(
                            text = item,
                            fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                            fontSize = 20.sp,
                            color = Black,
                            modifier = Modifier.clickable {
                                language.value = item
                                buttonClickedState.value = false
                            }

                        )

                }
            }
        }
    }
}

