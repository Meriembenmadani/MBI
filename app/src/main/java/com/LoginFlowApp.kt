package com

import android.app.Application
import com.google.firebase.FirebaseApp

public class LoginFlowApp : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}