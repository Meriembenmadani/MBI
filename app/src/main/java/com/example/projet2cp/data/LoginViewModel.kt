package com.example.projet2cp.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet2cp.data.rules.validator
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
   private val TAG = LoginViewModel::class.simpleName
   var registrationUIState = mutableStateOf(RegistrationUIState())
   var allValidationPassed = mutableStateOf(false)
   var signUpInProgress = mutableStateOf(false)

   fun onEvent(event:UIEvent,navController: NavHostController){
      when(event){
          is UIEvent.UserNameChanged -> {
             registrationUIState.value = registrationUIState.value.copy(
                userName = event.userName
             )
             validateDataWithRules()
             printState()
          }
         is UIEvent.EmailChanged -> {
            registrationUIState.value = registrationUIState.value.copy(
               email = event.email
            )
            validateDataWithRules()
            printState()
         }
         is UIEvent.PasswordChanged -> {
            registrationUIState.value = registrationUIState.value.copy(
               password = event.password
            )
            validateDataWithRules()
            printState()
         }
         is UIEvent.SignUpButtonClicked ->{
            signUP(navController = navController)
         }
      }
   }

   private fun signUP(navController: NavHostController) {

      Log.d(TAG, "Inside_printState")
      printState()
      createUserInFirebase(
         email = registrationUIState.value.email,
         password = registrationUIState.value.password ,
         navController = navController
      )




   }

   private fun validateDataWithRules() {
      val userNameResult = validator.validateUserName(
         uName = registrationUIState.value.userName
      )
      val emailResult = validator.validateEmail(
         e = registrationUIState.value.email
      )
      val passwordResult = validator.validatePassword(
         pass = registrationUIState.value.password
      )
      Log.d(TAG, "Inside_validateDataWithRules")
      Log.d(TAG, "UserName Result = $userNameResult")
      Log.d(TAG, "Email Result = $emailResult")
      Log.d(TAG, "Password Result = $passwordResult")

      registrationUIState.value = registrationUIState.value.copy(
         userNameError = userNameResult.status,
         emailError = emailResult.status,
         passwordError = passwordResult.status
      )
      allValidationPassed.value = userNameResult.status && emailResult.status && passwordResult.status

   }

   private fun printState(){
      Log.d(TAG, "Inside_printState")
      Log.d(TAG, registrationUIState.value.toString())
   }

   private fun createUserInFirebase(email:String, password:String,navController: NavHostController){
      signUpInProgress.value = true
      FirebaseAuth
         .getInstance()
         .createUserWithEmailAndPassword(email,password)
         .addOnCompleteListener {
            Log.d(TAG,"Inside_OnCompleteListener")
            Log.d(TAG,"is successful ${it.isSuccessful}")
            signUpInProgress.value = false
            if (it.isSuccessful){
               navController.navigate("MBI")
            }

         }
         .addOnFailureListener {
            Log.d(TAG,"Inside_OnFailureListener")
            Log.d(TAG,"Exception = ${it.message}")
            Log.d(TAG,"Exception = ${it.localizedMessage}")
         }


   }
}