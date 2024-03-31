package com.example.projet2cp.data

sealed class UIEvent {

    data class UserNameChanged(val userName:String): UIEvent()
    data class EmailChanged(val email:String): UIEvent()
    data class PasswordChanged(val password:String): UIEvent()
}