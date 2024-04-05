package com.example.projet2cp.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.projet2cp.R

@ExperimentalAnimationApi
@Composable
fun PaymentCard(
    nameText: TextFieldValue,
    cardNumber: TextFieldValue,
    expiryNumber: TextFieldValue,
    cvcNumber: TextFieldValue
) {
    var backVisible by remember { mutableStateOf(false) }
    var visaType by remember { mutableStateOf(CardType.None) }
    val length = if (cardNumber.text.length > 16) 16 else cardNumber.text.length
    val initial = remember { "*****************" }
        .replaceRange(0..length, cardNumber.text.take(16))


}


enum class CardType(
    val title: String,
    @DrawableRes val image: Int,
) {
    None("", R.drawable.ic_visa_logo),
    Visa("visa", R.drawable.ic_visa_logo),
}
