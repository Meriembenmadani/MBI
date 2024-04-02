package com.example.projet2cp.data

sealed class SignUpUIEvent {

    data class UserNameChanged(val userName:String): SignUpUIEvent()
    data class EmailChanged(val email:String): SignUpUIEvent()
    data class PasswordChanged(val password:String): SignUpUIEvent()
    object SignUpButtonClicked : SignUpUIEvent()
}