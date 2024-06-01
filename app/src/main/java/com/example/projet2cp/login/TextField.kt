package com.example.projet2cp.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.projet2cp.R
import com.example.projet2cp.ui.theme.EnabledGray
import com.example.projet2cp.ui.theme.MyBleu
import com.example.projet2cp.ui.theme.MyGray
import com.example.projet2cp.ui.theme.MyPurple


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FunTextField(
    modifier: Modifier = Modifier,
    label: String,
    errorStatus:Boolean,
    onTextSelected:(String)->Unit,

) : String{
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu


    val textValue = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current



    androidx.compose.material3.OutlinedTextField(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth(),

        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        maxLines = 1,
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },

        label = {
            Text(
                text = label,
                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                color = if (isSystemInDarkTheme()) Color.White else Color(0xFF475569),

            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = uiColor,
            unfocusedBorderColor = uiColor,
            errorBorderColor = uiColor,
            cursorColor = uiColor,
            errorTextColor = Color.Red,
            focusedTextColor =  if (isSystemInDarkTheme()) Color.White else MyGray,
            unfocusedTextColor =  if (isSystemInDarkTheme()) Color.White else MyGray,

        ),

        isError = !errorStatus
    )
    return textValue.value
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FunPassWordField(
    modifier: Modifier = Modifier,
    label: String,
    errorStatus:Boolean,
    onTextSelected:(String)->Unit
) {
    val configuration = LocalConfiguration.current

    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    val password = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current
    val passwordVisible = remember {
        mutableStateOf(false)
    }


    androidx.compose.material3.TextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 2.dp,
                color = uiColor,
                shape = RoundedCornerShape(12.dp)
            )
         ,
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
            onTextSelected(it)
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
            focusedTextColor =  if (isSystemInDarkTheme()) Color.White else MyGray,
            unfocusedTextColor =  if (isSystemInDarkTheme()) Color.White else MyGray,



            ),
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                if (isSystemInDarkTheme()) painterResource(id = R.drawable.visibilitydark) else painterResource(id = R.drawable.visibility)
            } else {
                if (isSystemInDarkTheme()) painterResource(id = R.drawable.visibility_off_dark) else   painterResource(id = R.drawable.visibilityoff)
            }

            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Image(
                    painter = iconImage,
                    contentDescription = description,

                )
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}

@Composable
fun ButtonComponent(value:String, onButtonClicked :()->Unit, isEnabled : Boolean =false){
    val uiColor = if (isSystemInDarkTheme()) MyPurple else MyBleu
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        onClick = {
                  onButtonClicked.invoke()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor =  if (isEnabled) uiColor else EnabledGray,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        enabled = isEnabled
    ) {
        Text(
            text = value,
            fontFamily = FontFamily(listOf(Font(R.font.poppins_medium))),
            fontSize = 14.sp
        )
    }
}
