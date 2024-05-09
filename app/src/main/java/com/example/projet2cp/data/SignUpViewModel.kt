package com.example.projet2cp.data

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider



class SignUpViewModel: ViewModel() {
   private val activity: Activity = Activity()
   companion object {
      private const val RC_SIGN_IN = 9001
   }
   private val auth = FirebaseAuth.getInstance()

   private val TAG = SignUpViewModel::class.simpleName
   var registrationUIState = mutableStateOf(RegistrationUIState())
   var allValidationPassed = mutableStateOf(false)
   var signUpInProgress = mutableStateOf(false)

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


         else -> {}
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

                  createUserInFirebase(
                     email = registrationUIState.value.email,
                     password = registrationUIState.value.password,
                     username = registrationUIState.value.userName,
                     navController = navController
                  )
               } else {

                  Log.d(TAG, "Email already exists")
               }
            } else {

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
                  } else {
                     Log.e(TAG, "Failed to send verification email.", task.exception)
                  }
               }
               val userId = it.result?.user?.uid
               if (userId != null) {
                  val db = FirebaseDatabase.getInstance()
                  val user = hashMapOf(
                     "username" to username,
                     "email" to email,

                  )
                  db.getReference("users")
                     .child(userId)
                     .setValue(user)
                     .addOnSuccessListener {
                        Log.d(TAG, "User information successfully written!")
                     }
                     .addOnFailureListener { e ->
                        Log.w(TAG, "Error writing user information", e)
                     }
               }
               navController.navigate("MBI")
            }

         }
         .addOnFailureListener {
            Log.d(TAG,"Inside_OnFailureListener")
            Log.d(TAG,"Exception = ${it.message}")
            Log.d(TAG,"Exception = ${it.localizedMessage}")
         }


   }

   fun signInWithGoogle(launcher: ActivityResultLauncher<Intent>) {
      val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
         .requestIdToken("674714294123-15ichpu31103hv9rm5j42d2jmtgmncat.apps.googleusercontent.com")
         .requestEmail()
         .build()

      val googleSignInClient = GoogleSignIn.getClient(activity, gso)
      val signInIntent = googleSignInClient.signInIntent
      launcher.launch(signInIntent)
   }

   fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
      try {
         val account = completedTask.getResult(ApiException::class.java)
         firebaseAuthWithGoogle(account.idToken!!)
      } catch (e: ApiException) {
         // Handle sign-in error
      }
   }

   private fun firebaseAuthWithGoogle(idToken: String) {
      val credential = GoogleAuthProvider.getCredential(idToken, null)
      auth.signInWithCredential(credential)
         .addOnCompleteListener { task ->
            if (task.isSuccessful) {
               // Sign in success
            } else {
               // Sign in failure
            }
         }
   }
}