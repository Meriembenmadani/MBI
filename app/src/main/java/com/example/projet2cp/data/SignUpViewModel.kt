package com.example.projet2cp.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class SignUpViewModel: ViewModel() {
   private val TAG = SignUpViewModel::class.simpleName
   var registrationUIState = mutableStateOf(RegistrationUIState())
   var allValidationPassed = mutableStateOf(false)
   var signUpInProgress = mutableStateOf(false)
   var signUpError = mutableStateOf<String?>(null)

   fun onEvent(event:SignUpUIEvent, navController: NavHostController){
      when(event){
          is SignUpUIEvent.UserNameChanged -> {
             registrationUIState.value = registrationUIState.value.copy(
                userName = event.userName
             )
             validateDataWithRules()
             printState()
          }
         is SignUpUIEvent.EmailChanged -> {
            registrationUIState.value = registrationUIState.value.copy(
               email = event.email
            )
            validateDataWithRules()
            printState()
         }
         is SignUpUIEvent.PasswordChanged -> {
            registrationUIState.value = registrationUIState.value.copy(
               password = event.password
            )
            validateDataWithRules()
            printState()
         }
         is SignUpUIEvent.SignUpButtonClicked ->{
            signUP(navController = navController)
         }


      }
   }

   private fun signUP(navController: NavHostController) {

      Log.d(TAG, "Inside_printState")
      printState()

      val auth = FirebaseAuth.getInstance()
      auth.fetchSignInMethodsForEmail(registrationUIState.value.email)
         .addOnCompleteListener { task ->
            if (task.isSuccessful) {
               val signInMethods = task.result?.signInMethods
               if (signInMethods == null || signInMethods.isEmpty()) {
                  // The email does not exist, you can create the user
                  createUserInFirebase(
                     email = registrationUIState.value.email,
                     password = registrationUIState.value.password,
                     username = registrationUIState.value.userName,
                     navController = navController
                  )
               } else {
                  // The email already exists, handle the error
                  Log.d(TAG, "Email already exists")
               }
            } else {
               // Failed to check if the email exists, handle the error
               Log.e(TAG, "Failed to check if email exists", task.exception)
            }
         }




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

   private fun createUserInFirebase(email:String, password:String,username:String,navController: NavHostController){
      signUpInProgress.value = true
      FirebaseAuth
         .getInstance()
         .createUserWithEmailAndPassword(email,password)
         .addOnCompleteListener {
            Log.d(TAG,"Inside_OnCompleteListener")
            Log.d(TAG,"is successful ${it.isSuccessful}")
            signUpInProgress.value = false
            if (it.isSuccessful){
               val firebaseUser = it.result?.user
               firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
                  if (task.isSuccessful) {
                     Log.d(TAG, "Verification email sent to ${firebaseUser.email}")
                     if (firebaseUser.isEmailVerified) {
                        // The email is verified, you can navigate to the application
                        val userId = it.result?.user?.uid // Get the user's ID
                        if (userId != null) {
                           val db = FirebaseDatabase.getInstance()
                           val user = hashMapOf(
                              "username" to username,
                              "email" to email,

                              )
                           db.getReference("users")
                              .child(userId) // Use the user's ID as the key
                              .setValue(user)
                              .addOnSuccessListener {
                                 Log.d(TAG, "User information successfully written!")
                              }
                              .addOnFailureListener { e ->
                                 Log.w(TAG, "Error writing user information", e)
                              }
                        }
                        navController.navigate("MBI")
                     } else {
                        // The email is not verified, do not navigate to the application
                        Log.d(TAG, "Email is not verified. Please verify your email before proceeding.")
                     }
                  } else {
                     Log.e(TAG, "Failed to send verification email.", task.exception)
                  }
               }

          }
            navController.navigate("MBI")
         }
         .addOnFailureListener {
            Log.d(TAG,"Inside_OnFailureListener")
            Log.d(TAG,"Exception = ${it.message}")
            Log.d(TAG,"Exception = ${it.localizedMessage}")
            signUpError.value = it.localizedMessage
         }


   }
}