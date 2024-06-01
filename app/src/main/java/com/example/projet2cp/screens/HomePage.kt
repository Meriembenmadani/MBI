package com.example.projet2cp.screens

import com.example.projet2cp.NavigationViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.projet2cp.data.Course
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyGray
import com.example.projet2cp.ui.theme.MyPurple
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow

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

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()
            .padding(bottom =  screenHeight * 0.1f),
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
            SpecialOffers(screenWidth, screenHeight, offers, mbiNavController)

            NewActivities(activities, viewModel, mbiNavController, screenHeight, screenWidth)
        }
    }
}
@Composable
fun SpecialOffers(screenWidth: Dp, screenHeight: Dp, offers: List<Offer>,mbiNavController: NavHostController) {
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
                                mbiNavController.navigate("PaymentScreen") },
                                modifier = Modifier
                                    .padding(start = screenWidth * 0.0416f),
                                   //.size(63.dp, 30.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = uiColor),
                            ) {
                                Text("Buy Now")
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
fun NewActivities(activity: List<Activity>, viewModel: NavigationViewModel = viewModel(), navController: NavHostController , screenHeight: Dp, screenWidth: Dp) {
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
                            .clickable {
                                activity.isLiked.value = !activity.isLiked.value

                            }

                    )
                    Spacer(modifier =Modifier.width(6.dp))
                    Spacer(modifier =Modifier.width(6.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.comment),
                        contentDescription = "Comment Icon",
                        tint = uiColor,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                navController.navigate("commentSection/${activity.id}")
                            }
                    )
                }


                Text(
                    text = displayText,
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                    fontSize = 14.sp,
                    color = uxColor,
                )


                if (activity.showFullText.value) {
                    Text(
                        text = "...Less",
                        color = uiColor,
                        modifier = Modifier
                            .clickable { activity.showFullText.value = false }
                    )
                }

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

@Composable
fun CommentSection(screenHeight: Dp,screenWidth:Dp,navController: NavHostController,viewModel: NavigationViewModel,activityId:String) {
    val bgColor =  if (isSystemInDarkTheme()) Color(0xFF3A3A3A) else Color.White
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    var commentText by remember { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 100.dp)) {
     Column(modifier = Modifier
            .padding(
                top = screenHeight * 0.09f,
                start = screenWidth * 0.05f,
                end = screenWidth * 0.05f,
                bottom =screenHeight* 0.1f
            )
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
         LaunchedEffect(key1 = activityId) {
             viewModel.getComments(activityId)
         }
         val comments by viewModel.comments.collectAsState()


         Text(
             text = "Comments",
             fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
             fontSize = 15.52.sp,
             modifier = Modifier.padding(start = 0.dp, top = 10.dp, end = 0.dp, bottom = 30.dp),
             color = uiColor
         )
         Spacer(modifier = Modifier.height(6.dp))
         val activity = viewModel.activities.find { it.id == activityId }

         Comments(comments, viewModel,activityId)
     }
            Column(   modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ){
                    val avatar by viewModel.avatar.collectAsState()

                    Image(
                        painter =  painterResource(id = avatar ?: R.drawable.profile),
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))

                    @OptIn(ExperimentalMaterial3Api::class)
                    TextField(modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 1.dp,
                            color = if (isSystemInDarkTheme()) Color(0xFFC4C4C4) else Color(
                                0xFF475569
                            ),
                            shape = RoundedCornerShape(30.dp)
                        )
                        ,

                        shape = RectangleShape,
                        maxLines = 1,

                        singleLine = true,
                        value = commentText,
                        onValueChange = {
                            commentText = it
                        },

                        label = {
                            Text(
                                text = "Write a comment",
                                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                                color =if (isSystemInDarkTheme()) Color(0xFFC4C4C4) else Color(0xFF475569),
                                fontSize = 11.sp

                            )
                        },

                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent,
                            cursorColor = uiColor,
                            focusedTextColor =  if (isSystemInDarkTheme()) Color.White else MyGray,
                            unfocusedTextColor =  if (isSystemInDarkTheme()) Color.White else MyGray,



                            ),
                        trailingIcon = {
                            val iconImage = if (commentText.isNotEmpty()) {
                                if (isSystemInDarkTheme()) painterResource(id = R.drawable.submitdarck) else painterResource(id = R.drawable.submit)
                            } else{
                                painterResource(id = R.drawable.resource_default)
                            }


                            IconButton(onClick = {
                                val comment = Commentaire(
                                    userAvatar = avatar ?: R.drawable.profile,
                                    userName = viewModel.userName,
                                    comment = commentText
                                )
                                viewModel.saveComment(activityId, comment)
                                commentText = ""

                            }) {
                                Image(
                                    painter =iconImage,
                                    contentDescription ="",

                                    )
                            }
                        },


                        )
                }

            }

        }


    }



@Composable
fun Comments(data:  List<Commentaire>, viewModel: NavigationViewModel,activityId: String) {
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    var edit by remember { mutableStateOf(false) }
    var delet by remember { mutableStateOf(false) }
    LazyColumn {
        items(data) { item ->
            var show by remember { mutableStateOf(false) }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                colors = CardDefaults.cardColors(containerColor =   Color.Transparent),


                ) {
                Row(modifier= Modifier.padding(6.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween) {

                        Image(
                            painter = painterResource(id = item.userAvatar),
                            contentDescription = "User Avatar",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = item.userName,
                            fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                            fontSize = 10.24.sp,
                            color = if (isSystemInDarkTheme()) Color.White else Black
                        )

                    }
                    //Spacer(modifier = Modifier.width(60.dp))
                    Column(modifier = Modifier.fillMaxHeight()) {


                    if(viewModel.userName == item.userName){
                    Icon(
                        painter = painterResource(id = com.example.projet2cp.R.drawable.pp),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                           show= !show
                        },
                        tint = if (isSystemInDarkTheme()) MyPurple else MyBleu

                    )}
                        Spacer(modifier =Modifier.height(2.dp))
                        if (show) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),

                                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                                colors = CardDefaults.cardColors(containerColor = if (isSystemInDarkTheme()) Color(0xFF3A3A3A) else Color.White  ),


                                ){
                                Column (){
                                    Row (modifier = Modifier
                                        .clickable { delet = !delet }
                                        .padding(1.dp)){
                                        Icon(
                                            painter = painterResource(id = com.example.projet2cp.R.drawable.trash),
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp),
                                            tint =  if (isSystemInDarkTheme()) MyPurple else MyBleu
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Text(text = "Delete",
                                            fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                                            fontSize = 16.sp,
                                            color = if (isSystemInDarkTheme()) MyPurple else MyBleu
                                        )

                                    }
                                    Row (modifier = Modifier
                                        .clickable { edit = !edit }
                                        .padding(1.dp)){
                                        Icon(
                                            painter = painterResource(id = com.example.projet2cp.R.drawable.pen),
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp),
                                            tint =  if (isSystemInDarkTheme()) MyPurple else MyBleu
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Text(text = "Edit",
                                            fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                                            fontSize = 16.sp,
                                            color = if (isSystemInDarkTheme()) MyPurple else MyBleu
                                        )

                                    }
                                    var commentchange by remember { mutableStateOf(item.comment) }
                                    if (edit) {
                                        @OptIn(ExperimentalMaterial3Api::class)
                                        TextField(

                                            value =commentchange,
                                            onValueChange = { commentchange = it },
                                            label = { Text("Edit your comment") },
                                            colors = TextFieldDefaults.textFieldColors(
                                                containerColor = if (isSystemInDarkTheme()) Black else Color.White,
                                                focusedIndicatorColor = uiColor,
                                                unfocusedIndicatorColor = uiColor,
                                                cursorColor = uiColor,

                                            ),
                                            trailingIcon = {
                                                val iconImage = if (commentchange.isNotEmpty()) {
                                                    if (isSystemInDarkTheme()) painterResource(id = R.drawable.submitdarck) else painterResource(id = R.drawable.submit)
                                                } else{
                                                    painterResource(id = R.drawable.resource_default)
                                                }


                                                IconButton(onClick = {
                                                    item.comment= commentchange
                                                    viewModel.updateComment(activityId,item.commentId,item)
                                                    edit=!edit
                                                    show=!show
                                                }) {
                                                    Image(
                                                        painter =iconImage,
                                                        contentDescription ="",

                                                        )
                                                }
                                            },
                                            modifier = Modifier.padding(6.dp)
                                        )


                                    }
                                    if(delet){
                                        viewModel.deleteComment(activityId,item.commentId)
                                        delet=!delet
                                        show=!show
                                    }
                                }
                            }
                        }
                    }

                }

                Text(text = item.comment,
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                    fontSize = 12.sp,
                    color = if (isSystemInDarkTheme()) Color.White else Black
                )


            }
        }
    }
}



data class Activity(
    val id: String,
    val title: String,
    val imageResource: Int,
    val description: String,
    var showFullText: MutableState<Boolean> = mutableStateOf(false),
    var isLiked: MutableState<Boolean> = mutableStateOf(false),
    var comments: MutableStateFlow<List<Commentaire>> = MutableStateFlow(listOf())
)
data class Offer(
    val title: String,
    val date: String,
    val discount: Int,
    val imageResource: Int,
    val backgroundColor: Color
)
data class Commentaire(
    var commentId: String = "",
    var userAvatar: Int =0,
    var userName: String="",
    var comment: String="",

    )