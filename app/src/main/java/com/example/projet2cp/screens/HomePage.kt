package com.example.projet2cp.screens

import com.example.projet2cp.NavigationViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projet2cp.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyPurple
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun HomePage(mbiNavController: NavHostController, viewModel: NavigationViewModel = viewModel()) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val auth = FirebaseAuth.getInstance()
     val db = FirebaseDatabase.getInstance()
    var userName :String by remember { mutableStateOf("") }
    val userId = auth.currentUser?.uid
    if (userId != null) {
        LaunchedEffect(key1 = userId) {
            viewModel.getAvatar(userId)
        }
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchUserName()
    }

    val painter = viewModel.avatar?.let { rememberImagePainter(data = it) }
    val avatar by viewModel.avatar.collectAsState()

    val uiColor = if (isSystemInDarkTheme()) Color.White else Black
    val offers = listOf(
        Offer(
            title = "Short Japanese Stories for Kids",
            date = "20/02/2024",
            discount = 30,
            imageResource = R.drawable.japanessoffers,
            backgroundColor = Color(0xFF686BFF)
        ),
        Offer(
            title = "French for Beginners",
            date = "27/06/2024",
            discount = 50,
            imageResource = R.drawable.fr,
            backgroundColor = Color(0xFFEE97BC)
        )

    )
    val activities = listOf(
        Activity(
            id="manga",
            title = "Manga reading session",
            imageResource = R.drawable.manga,
            description = "Explore Japanese language and culture with our Manga Reading Sessions. Immerse yourself in captivating stories to learn vocabulary, grammar, and expressions. Our skilled instructors make language learning engaging and enjoyable. Join us for a professional and interactive experience merging language acquisition with the world of manga."
        ),
        Activity(
            id="movie",
            title = "Korean Movie Meet",
            imageResource = R.drawable.movie,
            description = "Dive into Korean language and culture with our Movie Meet-Up! Join us for enjoyable film screenings, interactive language practice sessions, and opportunities to connect with fellow learners. Experience a fun and engaging way to learn together and deepen your understanding of Korean language and culture!"
        ),
        Activity(
            id="conference",
            title = "English conference",
            imageResource = R.drawable.conference,
            description = "Discover English excellence at our conference featuring expert sessions, interactive workshops, and a dynamic community. Enhance your language proficiency and engage with fellow learners!"
        )
    )
    for (i in 0..2) {
        viewModel.createActivityReference(activities[i])
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = viewModel.userName,
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                    fontSize = 15.52.sp,
                    color = uiColor
                )
                Image(
                    painter =  painterResource(id = avatar ?: R.drawable.profile),
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )

            }
            SpecialOffers(screenWidth, screenHeight, offers)

            NewActivities(activities)
        }
    }
}
@Composable
fun SpecialOffers(screenWidth: Dp, screenHeight: Dp, offers: List<Offer>) {
    val cardWidth = screenWidth * 0.74f
    val cardHeight = screenHeight *0.20f
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    val uxColor = if (isSystemInDarkTheme()) Color.White else Black


    Column {
        Text(
            text = "Special Offers",
            fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
            fontSize = 15.52.sp,
            modifier = Modifier.padding(start = 0.dp, top = 10.dp, end = 34.dp, bottom = 10.dp),
            color = uxColor
        )

        LazyRow {
            items(offers.size) { index ->
                val offer = offers[index]
                Card(
                    modifier = Modifier
                        .size(cardWidth, cardHeight)
                        .padding(start = 0.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = offer.backgroundColor
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(){
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .width(80.dp)
                                .offset(x = 15.dp, y = 10.dp)
                                .rotate(36f)
                                .background(
                                    Color.Red,
                                    shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${offer.discount}% off",
                                color = Color.White,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(start =0.dp, top = 0.dp, end =0.dp, bottom = 0.dp)
                            )
                        }
                        Column {
                            Box(modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(
                                    start = screenWidth * 0.0277f,
                                    top = screenWidth * 0.0277f,
                                    end = screenWidth * 0.0277f,
                                    bottom = 0.dp
                                ), contentAlignment = Alignment.TopStart){
                                Text(
                                    text = offer.title,
                                    fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                                    fontSize = 13.42.sp,
                                    color = Color.White
                                )

                            }
                            Text(
                                text = offer.date,
                                fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                                fontSize = 12.sp,
                                color = Color.White,
                                modifier = Modifier.padding(start =screenWidth*0.0277f, top = 0.dp, end =screenWidth*0.0277f, bottom = 0.dp)
                            )

                            Button(onClick = {
                                /*TODO: Handle buy now click*/ },
                                modifier = Modifier
                                    .padding(start = screenWidth * 0.0416f)
                                    .size(63.dp, 30.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = uiColor),
                            ) {
                                Text("Buy Now",
                                    fontSize = 7.sp)
                            }
                        }
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(end = screenWidth * 0.0277f), contentAlignment = Alignment.BottomEnd) {
                            Image(
                                painter = painterResource(id = R.drawable.shape),
                                contentDescription = null,
                                modifier = Modifier.size(screenWidth*0.4f, screenHeight*0.09f)
                            )
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .padding(end = 25.dp), contentAlignment = Alignment.CenterEnd){
                                Image(
                                    painter = painterResource(id = offer.imageResource),
                                    contentDescription = null,
                                    modifier = Modifier.size(screenWidth*0.3f, screenHeight*0.1075f)
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
fun NewActivities(activity: List<Activity>, viewModel: NavigationViewModel = viewModel()) {
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    val uxColor = if (isSystemInDarkTheme()) Color.White else Black


    Column {
        Text(
            text = "New Activities",
            fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
            fontSize = 15.52.sp,
            modifier = Modifier.padding(start = 0.dp, top = 10.dp, end = 0.dp, bottom = 10.dp),
            color = uxColor
        )
LazyColumn{
            items(activity.size) { index ->
                val activity = activity[index]

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 0.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isSystemInDarkTheme()) Black else Color.White

        ),
            shape = RoundedCornerShape(8.dp)
        ) {

            Column {
                val displayText = if (activity.showFullText.value || activity.description.length <= 83) {
                    activity.description
                } else {
                    activity.description.take(83)
                }

                Text(
                    text = activity.title,
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                    fontSize = 16.sp,
                    color = uiColor,
                    modifier = Modifier.padding(16.dp)
                )

                Image(
                    painter = painterResource(id = activity.imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onDoubleTap = {
                                    activity.isLiked.value = !activity.isLiked.value
                                }
                            )
                        }
                )

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = "Like Icon",
                        tint = if (activity.isLiked.value) uiColor else Color.LightGray,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { activity.isLiked.value = !activity.isLiked.value

                            }

                    )
                    Spacer(modifier =Modifier.width(6.dp))
                    Text(text = "${activity.likes}", fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))), color = uxColor)
                    Spacer(modifier =Modifier.width(6.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.comment),
                        contentDescription = "Comment Icon",
                        tint = uiColor,
                        modifier = Modifier.size(24.dp)
                    )
                }


                Text(
                    text = displayText,
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                    fontSize = 14.sp,
                    color = uxColor,
                )


                if (!activity.showFullText.value && activity.description.length > 83) {
                    Text(
                        text = "...More",
                        color = uiColor,
                        modifier = Modifier
                            .clickable { activity.showFullText.value = true }
                    )
                }


            }
        }}}
    }
}

data class Activity(
    val id: String,
    val title: String,
    val imageResource: Int,
    val description: String,
    var likes: Int = 0,
    var showFullText: MutableState<Boolean> = mutableStateOf(false),
    var isLiked: MutableState<Boolean> = mutableStateOf(false)
)
data class Offer(
    val title: String,
    val date: String,
    val discount: Int,
    val imageResource: Int,
    val backgroundColor: Color
)