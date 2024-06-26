package com.example.projet2cp.screens

import com.example.projet2cp.NavigationViewModel
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.projet2cp.R
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.EnabledGray
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyGray
import com.example.projet2cp.ui.theme.MyPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen( navController: NavHostController, viewModel: NavigationViewModel) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    val isCardFlipped = remember { mutableStateOf(false) }
    var expiryError by remember { mutableStateOf(false) }


    val textValue = remember { mutableStateOf("") }
    val cvc = remember { mutableStateOf("") }




    val cardNumber = remember { mutableStateOf("XXXXXXXXXXXXXXXX") }
    val cardHolder = remember { mutableStateOf("") }
    val expiry = remember { mutableStateOf("") }
    val rotation by animateFloatAsState(
        targetValue = if (isCardFlipped.value) 180f else 0f,
        animationSpec = tween(durationMillis = 100, easing = LinearEasing),
        label = ""
    )

    var isButtonEnabled by remember { mutableStateOf(false) }
    fun validateInputs() {
        isButtonEnabled = cardNumber.value.isNotEmpty()&&
                cardHolder.value.isNotEmpty() &&
                expiry.value.isNotEmpty() &&
                cvc.value.isNotEmpty()
    }
    LaunchedEffect(cardNumber.value, cardHolder.value, expiry.value, cvc.value) {
        validateInputs()
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize(),
        color = if (isSystemInDarkTheme()) Black else Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(
                    top = screenHeight * 0.05f,
                    start = screenWidth * 0.05f,
                    end = screenWidth * 0.05f,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(206.dp)
                    .graphicsLayer(rotationY = rotation)
                    .clickable { isCardFlipped.value = !isCardFlipped.value },
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (textValue.value.length >= 4) MyBleu else MyGray
                )
            ) {
                if (!isCardFlipped.value) {
                    CardFont(
                        cardNumber = cardNumber.value,
                        cardHolder = cardHolder.value,
                        expiry = expiry.value,
                        uiColor = uiColor
                    )
                }
                else{
                    CardBack(cvc = cvc.value)
                }
            }

            Spacer(modifier = Modifier.height(54.dp))
            Column(

            ){
                TextField(

                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = uiColor,
                            shape = RoundedCornerShape(6.dp)

                        ),
                    maxLines = 1,
                    singleLine = true,

                    value = cardHolder.value,
                    onValueChange = {
                        cardHolder.value = it

                    },

                    label = {
                        Text(
                            text = "Card Holder Name",
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
                Spacer(modifier = Modifier.height(20.dp))
                TextField(

                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = uiColor,
                            shape = RoundedCornerShape(6.dp)

                        ),
                    maxLines = 1,
                    singleLine = true,

                    value = textValue.value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 16) {
                            textValue.value = newValue
                            cardNumber.value = textValue.value
                        }
                    },

                    label = {
                        Text(
                            text = "Card Number",
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
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next

                    ),
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier

                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
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

                        value = expiry.value,
                        onValueChange = {
                                        expiry.value=it
                            expiryError = !isValidExpiryDate(it)
                        },

                        label = {
                            Text(
                                text = "Exp Date",
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
                        isError = expiryError,


                    )
                    TextField(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp)
                            .border(
                                width = 2.dp,
                                color = uiColor,
                                shape = RoundedCornerShape(6.dp)

                            ),
                        maxLines = 1,
                        singleLine = true,

                        value = cvc.value,
                        onValueChange = {
                            cvc.value = it
                            if (it.length in 1..2) isCardFlipped.value=true else {
                                isCardFlipped.value=false
                            }
                        },

                        label = {
                            Text(
                                text = "CVC",
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
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done

                        ),
                    )

                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    onClick = {
                        val userId = viewModel.auth.currentUser?.uid
                        if (userId != null) {
                            viewModel.savePurchasedCourse(userId, viewModel.addCourse)
                        }
                        navController.navigate("SuccessfulPaymentScreen")


                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isButtonEnabled) uiColor else EnabledGray ,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp),
                    enabled = isButtonEnabled

                ) {
                    Text(
                        text = "Confirm Payment",
                        fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                        fontSize = 14.sp
                    )

                }




            }

        }
    }
}

@Composable
fun CardFont(cardNumber: String, cardHolder: String, expiry: String, uiColor: Color) {
    Row(
        modifier = Modifier
            .padding(19.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            painter = painterResource(id = R.drawable.card_symbol),
            contentDescription = "card symbol",
        )
        Image(
            painter = painterResource(id = R.drawable.ic_visa_logo),
            contentDescription = "visa logo",
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = formatCardNumber(cardNumber),
            modifier = Modifier.padding(2.dp),
            fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
            fontSize = 24.sp,
            color = Color.White
        )
    }
    Row(
        modifier = Modifier
            .padding(19.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = "CARD HOLDER ",
                fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                fontSize = 12.sp,
                color = Color.White
            )

            Text(
                text = cardHolder,
                fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                fontSize = 16.sp,
                color = Color.White
            )

        }
        Column {
            Text(
                text = "Expiry ",
                fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                fontSize = 12.sp,
                color = Color.White
            )

            Text(
                text = expiry,
                fontFamily = FontFamily(listOf(Font(R.font.poppins_semi_bold))),
                fontSize = 16.sp,
                color = Color.White
            )

        }
    }
}

@Composable
fun CardBack(cvc: String){
    Spacer(modifier = Modifier.fillMaxHeight(0.4f))
    Column(


    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(scaleX = -1f)
            .height(40.dp),
            shape = RectangleShape,
            colors = CardDefaults.cardColors(
                containerColor = if (isSystemInDarkTheme()) Color.White else Color.Black
            )) {

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

            horizontalAlignment = Alignment.Start

        ) {
            Card(modifier = Modifier
                .width(50.dp)
                .height(30.dp),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )) {
                Column(
                    modifier = Modifier.run {
                        fillMaxWidth()

                    },

                    horizontalAlignment = Alignment.CenterHorizontally

                ){
                Text(
                    text = cvc,
                    modifier = Modifier.graphicsLayer(scaleX = -1f),
                    fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
                    fontSize = 24.sp,
                    color = Color.Black
                )
                }

            }
        }

    }


}

fun formatCardNumber(cardNumber: String): String {
    val formattedNumber = cardNumber.chunked(4).joinToString(" ")
    return if (formattedNumber.length > 16) formattedNumber.substring(0, 16) else formattedNumber
}
fun isValidExpiryDate(expiryDate: String): Boolean {
    val expiryDatePattern = "^(0[1-9]|1[0-2])\\/([0-9]{2})$".toRegex()
    return expiryDatePattern.matches(expiryDate)
}