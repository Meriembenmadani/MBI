package com.example.projet2cp.data.rules

object validator {
    fun validateUserName(uName:String) :ValidationResult{
        return ValidationResult(
            (!uName.isNullOrEmpty() && uName.length>=8)
        )

    }
    fun validateEmail(e:String) :ValidationResult{
        return ValidationResult(
            (!e.isNullOrEmpty()  )
        )
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