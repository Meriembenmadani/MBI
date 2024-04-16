package com.example.projet2cp.screens

import NavigationViewModel
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.projet2cp.R
import com.example.projet2cp.data.Course
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyPurple



@Composable
fun MyCoursesScreen(navController: NavHostController,viewModel: NavigationViewModel) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()
            .padding(
                bottom = screenHeight * 0.09f,
            ),
        color = uiColor
    ) {
        Column( modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = screenWidth * 0.05f,
                        end = screenWidth * 0.05f,
                        top = screenHeight * 0.05f,
                    ),
                //horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                Text(
                    text = "My Courses",
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                    fontSize = 32.24.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            CoursesSection(screenHeight = screenHeight, screenWidth = screenWidth,viewModel)


        }
    }
}




@Composable
fun CoursesSection(screenHeight: Dp, screenWidth: Dp,viewModel: NavigationViewModel) {
    val bgColor =  if (isSystemInDarkTheme()) Black else Color.White


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()

        ,
        color = bgColor,
        shape = (RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
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
            Box (modifier= Modifier
                .fillMaxSize()
                .fillMaxWidth()
                ){
                Surface(modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                    color = bgColor,
                    shape = RectangleShape,

                ) {
                    Course(data = viewModel.purchasedCourses,viewModel)

                }

            }

        }
    }
}

@Composable
fun Course(data:  List<Course>,viewModel: NavigationViewModel ) {
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    LazyColumn {
        items(data) { item ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(bottom = 13.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                colors = CardDefaults.cardColors(containerColor =   Color(0x59808080)),


                ) {
                Row(modifier= Modifier.padding(6.dp)) {

                    CreateImageCourse(imageId = item.img)
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                            Text(text = "${item.name} ${item.language} ${item.level} Classe",
                                fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                                fontSize = 12.5.sp,
                                color = uiColor
                            )
                        Column(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(),
                        verticalArrangement = Arrangement.Bottom
                        ) {
                        Row ( modifier = Modifier
                            .padding(start = 28.dp, end = 28.dp)

                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            Row (verticalAlignment = Alignment.CenterVertically){
                                Icon(
                                    painter = painterResource(id=  R.drawable.date),
                                    contentDescription = null,
                                    modifier = Modifier.size(11.dp),
                                    tint = Color.Black
                                )
                                Text(
                                    text = item.date,
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .align(Alignment.CenterVertically),
                                    fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                                    fontSize = 8.3.sp,
                                    color = Color.Black
                                )
                            }
                            Text(
                                text = "${item.lessons} lessons",
                                modifier = Modifier
                                    .padding(2.dp)
                                    .align(Alignment.CenterVertically),
                                fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                                fontSize = 8.3.sp,
                                color = Color.Black
                            )

                        }
                    }

                    }

                }

            }
        }
    }
}

@Composable
private fun CreateImageCourse( imageId:Int) {
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu

    Surface(

        shape = RoundedCornerShape(12.dp),

        color = Color.Transparent


    ) {

        Image(
            painter = painterResource(imageId),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(135.dp, 94.dp)
        )
    }
}