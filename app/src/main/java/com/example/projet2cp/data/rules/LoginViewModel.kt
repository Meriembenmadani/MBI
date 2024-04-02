package com.example.projet2cp.data.rules

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.projet2cp.data.LoginUIEvent

class LoginViewModel :ViewModel(){
    private val TAG = LoginViewModel::class.simpleName
    var loginUIState = mutableStateOf(LoginUIState())
    var allValidationPassed = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)

    fun onEvent(event:LoginUIEvent){
        when(event){
          is LoginUIEvent.EmailChanged ->{
              loginUIState.value = loginUIState.value.copy(
                  email = event.email
              )

          }

            else -> {}
        }

    }

}