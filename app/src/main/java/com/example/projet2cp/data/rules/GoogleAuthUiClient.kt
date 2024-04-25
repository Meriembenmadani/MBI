package com.example.projet2cp.data.rules
import android.content.Context
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import android.content.IntentSender
import com.example.projet2cp.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException


class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient

) {
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender?{
        val result= try {
            oneTapClient.beginSignIn(
                buildSignInRequest()

            ).await()

        } catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
            null

        }
        return result?.pendingIntent?.intentSender
    }
    private fun buildSignInRequest():BeginSignInRequest{
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}