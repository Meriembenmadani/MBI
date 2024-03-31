package com.example.projet2cp.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
   private val TAG = LoginViewModel::class.simpleName
   var registrationUIState = mutableStateOf(RegistrationUIState())
   fun onEvent(event:UIEvent){
      when(event){
          is UIEvent.UserNameChanged -> {
             registrationUIState.value = registrationUIState.value.copy(
                userName = event.userName
             )
             printState()
          }
         is UIEvent.EmailChanged -> {
            registrationUIState.value = registrationUIState.value.copy(
               userName = event.email
            )
            printState()
         }
         is UIEvent.PasswordChanged -> {
            registrationUIState.value = registrationUIState.value.copy(
               userName = event.password
            )
            printState()
         }
      }
   }
   private fun printState(){
      Log.d(TAG, "Inside_printState")
      Log.d(TAG, registrationUIState.value.toString())
   }
}