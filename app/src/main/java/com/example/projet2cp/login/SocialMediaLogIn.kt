package com.example.projet2cp.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projet2cp.R
import com.example.projet2cp.ui.theme.Black
import com.example.projet2cp.ui.theme.MyPurple

@Composable
fun SocialMediaLogIn(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    onClick: ()-> Unit,

    ) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background( if (isSystemInDarkTheme()) Black else Color(0xFFF1F5F9))
            .clickable { onClick }
            .height(40.dp)
            .border(
                width = 1.dp,
                color =  if (isSystemInDarkTheme()) MyPurple else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = icon),
            contentDescription ="" ,
            modifier= Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = text,
            fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
            fontSize = 14.sp,
            color = Color(0xFF475569)

        )
    }


}



