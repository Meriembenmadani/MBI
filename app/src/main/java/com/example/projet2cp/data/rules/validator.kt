package com.example.projet2cp.data.rules

object validator {
    fun validateUserName(uName:String) :ValidationResult{
        return ValidationResult(
            (!uName.isNullOrEmpty() && uName.length>=8)
        )

    }
    fun validateEmail(e:String) :ValidationResult{
        val isNotEmpty = !e.isNullOrEmpty()


        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

        val isValidFormat = emailPattern.matches(e)
        return ValidationResult(isNotEmpty && isValidFormat)
    }
    fun validatePassword(pass:String) :ValidationResult{
        return ValidationResult(
            (!pass.isNullOrEmpty()  && pass.length>=4 )
        )
    }
}
data class ValidationResult(
    val status:Boolean=false

)