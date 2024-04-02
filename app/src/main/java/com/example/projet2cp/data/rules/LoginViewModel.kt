package com.example.projet2cp.data.rules

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.projet2cp.data.LoginUIEvent
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel :ViewModel(){
    private val TAG = LoginViewModel::class.simpleName
    var loginUIState = mutableStateOf(LoginUIState())
    var allValidationPassed = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)

    fun onEvent(event:LoginUIEvent){
        when(event){

            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )

            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.password
                )

            }

            is LoginUIEvent.ButtonClicked ->{
                login()
            }


            else -> {}
        }
        validateLoginUIDataWithRules()

    }

    private fun validateLoginUIDataWithRules() {
        TODO("Not yet implemented")
    }

    private fun login() {
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword()

    }
    private fun validateDataWithRules() {

        val emailResult = validator.validateEmail(
            e = loginUIState.value.email
        )
        val passwordResult = validator.validatePassword(
            pass = loginUIState.value.password
        )
        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "Email Result = $emailResult")
        Log.d(TAG, "Password Result = $passwordResult")

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
        allValidationPassed.value =  emailResult.status && passwordResult.status

    }
}