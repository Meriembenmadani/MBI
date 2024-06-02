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
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projet2cp.R
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyPurple
import com.google.firebase.auth.FirebaseAuth
import coil.compose.rememberImagePainter
import com.example.projet2cp.ui.theme.MyGray


@Composable
fun ProfileScreen( mbiNavController: NavHostController,viewModel: NavigationViewModel = viewModel()) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    var showMenu by remember { mutableStateOf(false) }
    val auth: FirebaseAuth
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchUserName()
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()
            .padding(bottom = 20.dp),
        color = if (isSystemInDarkTheme()) Black else Color.White

    ) {
        Column (


        ){
        IconButton(
            onClick = {

                mbiNavController.navigate("MenuSection")
            },
            modifier = Modifier.padding(4.dp)){

            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = uiColor
            )
        }}

    }


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
            CreateImageProfile(Modifier,screenWidth, screenHeight,viewModel,mbiNavController)
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


@Composable
private fun CreateInfo(viewModel: NavigationViewModel = viewModel()) {
    val userEmail = viewModel.getUserEmail()
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    var newUsername by remember { mutableStateOf(viewModel.userName) }
    var showCard by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

           Row {
               Text(
                   text = viewModel.userName,
                   fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                   fontSize = 20.sp,
                   color = uiColor,

               )

           }

        }

        Text(
            text = userEmail,
            modifier = Modifier.padding(2.dp),
            fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
            fontSize = 13.6.sp,
            color = if (isSystemInDarkTheme()) Color.White else Black
        )
    }



@Composable
private fun CreateImageProfile(modifier: Modifier= Modifier, screenWidth: Dp, screenHeight: Dp, viewModel: NavigationViewModel = viewModel(), mbiNavController: NavHostController) {
    val avatarList = listOf(
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3,
        R.drawable.avatar4,
        R.drawable.avatar5,
        R.drawable.avatar6,
        R.drawable.avatar7,
        R.drawable.avatar8,
        R.drawable.avatar9
    )
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    var selectedAvatar by rememberSaveable { mutableStateOf(viewModel.selectedAvatar) }

    val avatar by viewModel.avatar.collectAsState()


    var showDialog by remember { mutableStateOf<Boolean>(false) }

    val auth = FirebaseAuth.getInstance()
    val userId = auth.currentUser?.uid
    if (userId != null) {
        LaunchedEffect(key1 = userId) {
            viewModel.getAvatar(userId)
        }
    }
    val painter = viewModel.avatar?.let { rememberImagePainter(data = it) }


    Box {
        Surface(
            modifier = Modifier
                .size(screenWidth * 0.35f),
            shape = CircleShape,
            border = BorderStroke(0.5.dp, uiColor),
            shadowElevation = 4.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        ) {

            Image(
                painter =  painterResource(id = avatar ?: R.drawable.profile),
                contentDescription = "profile image",
                modifier = modifier.size(130.dp),
                contentScale = ContentScale.Crop
            )
        }

        Image(
            painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.cameradark else R.drawable.camera),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .offset(x = (screenWidth * 0.30f - 12.dp), y = (screenWidth * 0.32f - 12.dp))
                .wrapContentSize()
                .clickable { showDialog = true },
        )

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(avatarList) { avatar ->
                        Image(
                            painter = painterResource(id = avatar),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clickable {
                                    if (userId != null) {
                                        viewModel.saveAvatar(userId, avatar)
                                    }
                                    showDialog = false
                                    mbiNavController.navigate("Profile")
                                }
                        )
                    }
                }
            }
        }
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
            .padding(0.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(12.dp)),
            border =  BorderStroke(width = 1.dp , color = uiColor),
            color = if (isSystemInDarkTheme()) Black else Color.White
        ) {
            Portfolio(data = listOf("English A0", "English A1" , "English A2", "English B1", "English B2", "English C1"))

        }

    }
}


@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->

            Card(
                modifier = Modifier
                    .padding(end = 13.dp, bottom = 6.dp, top = 6.dp, start = 13.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                colors = CardDefaults.cardColors(containerColor =  if (isSystemInDarkTheme()) Color(0xFFB6B6B6) else Color(0xFFE7E0EC)),


                ) {
                Row(modifier= Modifier
                    .padding(6.dp, bottom = 0.dp) ){
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuSection(screenHeight: Dp,screenWidth:Dp,navController: NavHostController,viewModel: NavigationViewModel) {
    val bgColor =  if (isSystemInDarkTheme()) Color(0xFF3A3A3A) else Color.White
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    var commentText by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf(false) }
    var sujet by remember { mutableStateOf("") }
    var section by remember { mutableStateOf("") }
    var erreur  by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .fillMaxSize()
        .padding(bottom = 10.dp),
        color = if (isSystemInDarkTheme()) Black else Color.White) {


    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = bgColor
        ),
    ) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 100.dp, top = 30.dp),) {
        Column (modifier = Modifier.align(Alignment.TopStart)){
            IconButton(
                onClick = {

                    navController.navigate("Profile")

                }) {
                Icon(
                    painter = painterResource(id = com.example.projet2cp.R.drawable.logout),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = uiColor
                )
            }
        }
 Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier
            .padding(
                top = 12.dp,
                start = screenWidth * 0.05f,
                end = screenWidth * 0.05f,
                bottom =screenHeight* 0.1f
            )
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            var edit by remember { mutableStateOf(false) }
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .shadow(4.dp)
                    .clickable { edit = !edit },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSystemInDarkTheme()) Color(0xFF2A2A2A) else Color(0xFFA7A7A7),
                )
            ) {

                Text(
                    text = "Edit Profile",
                    modifier = Modifier.padding(2.dp),
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                    fontSize = 20.sp,
                    color = if (isSystemInDarkTheme()) Color.White else Black
                )

            }
            if (edit){
                var newUsername by remember { mutableStateOf(viewModel.userName) }
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
                    value = newUsername,
                    onValueChange = {
                        newUsername = it
                    },

                    label = {
                        Text(
                            text = "Update Username",
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
                        val iconImage = if (newUsername.isNotEmpty()) {
                            if (isSystemInDarkTheme()) painterResource(id = R.drawable.submitdarck) else painterResource(id = R.drawable.submit)
                        } else{
                            painterResource(id = R.drawable.resource_default)
                        }


                        IconButton(onClick = {

                            viewModel.updateUserName(newUsername)
                            edit =!edit

                        }) {
                            Image(
                                painter =iconImage,
                                contentDescription ="",

                                )
                        }
                    },


                    )
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (edit){
                var newEmamil by remember { mutableStateOf(viewModel.getUserEmail()) }
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
                    value = newEmamil,
                    onValueChange = {
                        newEmamil = it
                    },

                    label = {
                        Text(
                            text = "Update Email",
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
                        val iconImage = if (newEmamil.isNotEmpty()) {
                            if (isSystemInDarkTheme()) painterResource(id = R.drawable.submitdarck) else painterResource(id = R.drawable.submit)
                        } else{
                            painterResource(id = R.drawable.resource_default)
                        }


                        IconButton(onClick = {

                            viewModel.updateUserEmail(newEmamil)
                            edit =!edit

                        }) {
                            Image(
                                painter =iconImage,
                                contentDescription ="",

                                )
                        }
                    },


                    )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .shadow(4.dp)
                    .clickable { contact = !contact },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSystemInDarkTheme()) Color(0xFF2A2A2A) else Color(0xFFA7A7A7),
                )
            ) {

                Text(
                    text = "Contact Us",
                    modifier = Modifier.padding(2.dp),
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                    fontSize = 20.sp,
                    color = if (isSystemInDarkTheme()) Color.White else Black
                )

            }
            if (contact) {
                Dialog(
                    onDismissRequest = {
                        contact=!contact
                    }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()

                            .padding(top = 50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = bgColor,
                        )
                    ) {
                        Column {
                            Text(
                                text = "Contact Us",
                                modifier = Modifier.padding(2.dp),
                                fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                                fontSize = 20.sp,
                                color = uiColor
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextField(

                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(end = 6.dp)
                                    .border(
                                        width = 2.dp,
                                        color = uiColor,
                                        shape = RoundedCornerShape(6.dp)
                                    ),
                                maxLines = 1,
                                singleLine = true,

                                value = sujet,
                                onValueChange = {
                                    sujet=it

                                },

                                label = {
                                    Text(
                                        text = "Object",
                                        fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                                        color = Color(0xFF475569),
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent,
                                    errorBorderColor = Color.Transparent,
                                    cursorColor = uiColor,
                                    errorTextColor = Color.Red,
                                    focusedTextColor = MyGray,
                                    unfocusedTextColor = MyGray,
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),



                                )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextField(

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 6.dp)
                                    .border(
                                        width = 2.dp,
                                        color = uiColor,
                                        shape = RoundedCornerShape(6.dp)
                                    ),
                                maxLines = 1,
                                singleLine = true,

                                value = section,
                                onValueChange = {
                                    section=it

                                },

                                label = {
                                    Text(
                                        text = "Section",
                                        fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                                        color = Color(0xFF475569),
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent,
                                    errorBorderColor = Color.Transparent,
                                    cursorColor = uiColor,
                                    errorTextColor = Color.Red,
                                    focusedTextColor = MyGray,
                                    unfocusedTextColor = MyGray,
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),



                                )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextField(

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 6.dp)
                                    .border(
                                        width = 2.dp,
                                        color = uiColor,
                                        shape = RoundedCornerShape(6.dp)
                                    ),
                                maxLines = 1,
                                singleLine = true,

                                value = message,
                                onValueChange = {
                                    message=it

                                },

                                label = {
                                    Text(
                                        text = "Message",
                                        fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                                        color = Color(0xFF475569),
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent,
                                    errorBorderColor = Color.Transparent,
                                    cursorColor = uiColor,
                                    errorTextColor = Color.Red,
                                    focusedTextColor = MyGray,
                                    unfocusedTextColor = MyGray,
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),



                                )


                        }
                    }
                }
            }
        }
    }}}
}
