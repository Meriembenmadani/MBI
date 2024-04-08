package com.example.projet2cp.screens
import NavigationViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet2cp.R
import com.example.projet2cp.data.Course
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyGray
import com.example.projet2cp.ui.theme.MyPurple

@Composable
fun BuyScreen(mbiNavController: NavHostController, viewModel: NavigationViewModel) {

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

        Column(modifier = Modifier
            .fillMaxHeight()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()

                .padding(
                    start = screenWidth * 0.05f,
                    end = screenWidth * 0.05f,
                    top = screenHeight * 0.05f,

                    ),
            ) {
                Text(
                    text =  if (viewModel.navigateToBuyScreen.value) " ${viewModel.selectedLanguage}  ${viewModel.selectedLevel}" else { "" },
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                    fontSize = 32.24.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            BuySection(screenHeight,screenWidth, mbiNavController , viewModel)
        }

    }
}


@Composable
fun CourseCard(course: Course, index: Int,navController:NavHostController,viewModel: NavigationViewModel) {
    val alignment = if (index % 2==0) Alignment.CenterStart else Alignment.CenterEnd
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp, bottom = 20.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSystemInDarkTheme()) Color(0xff827A7A) else Color(0xffF2F2F2)),

        contentAlignment = Alignment.Center

    ){
        Column (modifier = Modifier
            .align(alignment)
            .padding(5.dp), verticalArrangement = Arrangement.Center) {
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xffF2F2F2))

            ){

                Image(
                    painter = painterResource(course.img),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .size(135.dp, 94.dp)
                )}
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 4.dp, start = 4.dp, top = 4.dp)
                ) {
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Icon(
                            painter = painterResource(id=  R.drawable.date),
                            contentDescription = null,
                            modifier = Modifier.size(8.dp),
                            tint =if (isSystemInDarkTheme()) Color.White else {Color(0xFF6A6B6C)}

                        )
                        Text(
                            text = course.date,
                            modifier = Modifier
                                .padding(2.dp)
                                .align(Alignment.CenterVertically),
                            fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                            fontSize = 5.68.sp,
                            color = if (isSystemInDarkTheme()) Color.White else Color(0xFF6A6B6C)
                        )
                    }
                    Text(
                        text = "${course.lessons} lessons",
                        modifier = Modifier.padding(end = 4.dp, start = 4.dp),
                        fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                        fontSize = 5.68.sp,
                        color = if (isSystemInDarkTheme()) Color.White else Color(0xFF6A6B6C)
                    )

                }
            Text(
                text = course.name,
                modifier = Modifier.padding(4.dp),
                fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                fontSize = 7.68.sp,
                color = if (isSystemInDarkTheme()) MyPurple else MyBleu
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 4.dp, start = 4.dp)
            ) {
                Text(
                    text = "${course.price} DA",
                    modifier = Modifier.padding(end = 4.dp, start = 4.dp),
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                    fontSize = 5.68.sp,
                    color = if (isSystemInDarkTheme()) Color.White else Color(0xFF6A6B6C)
                )
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        painter = painterResource(id=  R.drawable.places),
                        contentDescription = null,
                        modifier = Modifier.size(8.dp),
                        tint =if (isSystemInDarkTheme()) Color.White else {Color(0xFF6A6B6C)}

                    )
                    Text(
                        text = "${course.places} Available Places",
                        modifier = Modifier
                            .padding(2.dp)
                            .align(Alignment.CenterVertically),
                        fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                        fontSize = 5.68.sp,
                        color = if (isSystemInDarkTheme()) Color.White else Color(0xFF6A6B6C)
                    )
                }


            }
            Divider(
                modifier = Modifier.padding(10.dp),
                color = if (isSystemInDarkTheme()) Color.White else Color.Gray,
                thickness = 1.dp
            )
          Column(modifier = Modifier.fillMaxWidth().height(30.dp),
              horizontalAlignment = Alignment.CenterHorizontally) {

              Button(

                  onClick = {
                      navController.navigate("PaymentScreen")
                      viewModel.purchasedCourses.add(course)

                  },
                  colors = ButtonDefaults.buttonColors(
                      containerColor = uiColor,
                      contentColor = Color.White
                  ),
                  shape = RoundedCornerShape(2.dp),
              ) {
                  Text(
                      text = "Buy",
                      fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                      fontSize = 10.sp,
                      color = Color.White
                  )

              }

         }
            }

        }
    }



fun getCoursesList():  List<Course> {
    return listOf(
        Course("Listening" , "20/02/2024", R.drawable.courses_english, 20, 12, 40000.0f),
        Course("Speaking" , "20/02/2024", R.drawable.speaking, 20, 30, 20000.0f),
        Course("Reading" , "20/02/2024", R.drawable.reading, 20, 12, 40000.0f),
        Course("Writing" , "20/02/2024", R.drawable.writing, 20, 12, 40000.0f),
        Course("Grammar" , "20/02/2024", R.drawable.gramma, 20, 12, 40000.0f)
    )
}


@Composable
fun BuySection(screenHeight: Dp, screenWidth: Dp,mbiNavController: NavHostController,viewModel: NavigationViewModel) {
    val bgColor =  if (isSystemInDarkTheme()) Black else Color.White
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    val listOfCourses = getCoursesList()

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
            LazyVerticalGrid(modifier = Modifier.fillMaxWidth(),columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.SpaceBetween){
                itemsIndexed(items =listOfCourses) {index:Int,course: Course ->
               CourseCard(course =course , index = index , navController = mbiNavController, viewModel = viewModel)
            }
        }
    }

}
}







