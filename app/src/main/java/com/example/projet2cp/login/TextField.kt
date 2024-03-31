package com.example.projet2cp.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.projet2cp.ui.theme.MyBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FunTextField(
    modifier: Modifier = Modifier,
    label: String,
    errorStatus:Boolean=false,
    onTextSelected:(String)->Unit
) {


    val textValue = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current



    androidx.compose.material3.OutlinedTextField(
        modifier = modifier
            .border(width = 2.dp ,color = MyBlue, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth(),

        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        maxLines = 1,
        singleLine = true,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },

        label = {
            Text(
                text = label,
                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                color = Color(0xFF475569),

            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            cursorColor = MyBlue

        ),

        isError = !errorStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FunPassWordField(
    modifier: Modifier = Modifier,
    label: String,
    errorStatus:Boolean=false,
    onTextSelected:(String)->Unit
) {
    val configuration = LocalConfiguration.current


    val password = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current
    val passwordVisible = remember {
        mutableStateOf(false)
    }


    androidx.compose.material3.TextField(
        modifier = modifier
            .border(width = 2.dp ,color = MyBlue, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        singleLine = true,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        shape = RoundedCornerShape(12.dp),
        label = {
            Text(
                text = label,
                fontFamily = FontFamily(listOf(Font(R.font.poppins_regular))),
                color = Color(0xFF475569),

            )
        },

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MyBlue,
            unfocusedBorderColor = MyBlue,
            errorBorderColor = MyBlue,
            cursorColor = MyBlue

        ),
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                painterResource(id = R.drawable.visibility)
            } else {
                painterResource(id = R.drawable.visibilityoff)
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
