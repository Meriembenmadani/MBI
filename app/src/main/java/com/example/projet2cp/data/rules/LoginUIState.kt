package com.example.projet2cp.data.rules

data class LoginUIState (
    var email: String = "",
    var password: String = "",

    var emailError :Boolean = false,
    var passwordError :Boolean = false


)