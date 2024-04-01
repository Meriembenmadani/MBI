package com.example.projet2cp.data


data class RegistrationUIState (
    var userName : String = "",
    var email: String = "",
    var password: String = "",


    var userNameError :Boolean = false,
    var emailError :Boolean = false,
    var passwordError :Boolean = false





)