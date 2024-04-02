package com.example.projet2cp.data

sealed class LoginUIEvent {
    data class EmailChanged(val email:String): LoginUIEvent()
    data class PasswordChanged(val password:String): LoginUIEvent()
    object ButtonClicked :  LoginUIEvent()
}