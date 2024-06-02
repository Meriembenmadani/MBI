package com.example.projet2cp.data

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.internal.Contexts.getApplication

class LoginViewModel :ViewModel(){
    private val TAG = LoginViewModel::class.simpleName
    var loginUIState = mutableStateOf(LoginUIState())
    var allValidationPassed = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)

    fun onEvent(event:LoginUIEvent,navController: NavHostController){
        when(event){

            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
                validateDataWithRules()

            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
                validateDataWithRules()

            }

            is LoginUIEvent.ButtonClicked ->{
                login(navController)
            }



        }
        validateLoginUIDataWithRules()

    }
    fun sendPasswordResetEmail(email: String) {
        val auth = FirebaseAuth.getInstance()

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Email", "Email sent.")
                } else {
                    // Handle any error
                    // s
                    Log.e("Email", "Error sending email", task.exception)
                }
            }
    }

    private fun validateLoginUIDataWithRules() {

    }

    private fun login(navController: NavHostController) {
        loginInProgress.value=true
        val email= loginUIState.value.email
        val password = loginUIState.value.password
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                loginInProgress.value=false
                Log.d(TAG,"Inside_login_success")
                Log.d(TAG,"is Successful = ${it.isSuccessful}")
                if(it.isSuccessful){
                    navController.navigate("MBI")
                }
            }
            .addOnFailureListener {
                Log.d(TAG,"Inside_OnFailureListener")
                Log.d(TAG,"Exception = ${it.message}")
                Log.d(TAG,"Exception = ${it.localizedMessage}")
            }

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
    fun doesEmailExist(email: String): Boolean {
        val auth = FirebaseAuth.getInstance()
        var emailExists = false

        auth.createUserWithEmailAndPassword(email, "temporaryPassword")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // The email does not exist
                    // Delete the temporary account
                    task.result?.user?.delete()
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        // The email exists
                        emailExists = true
                    }
                }
            }
            .addOnFailureListener {
                // Handle any error
                Log.e("Email", "Error checking email", it)
            }

        return emailExists
    }




}