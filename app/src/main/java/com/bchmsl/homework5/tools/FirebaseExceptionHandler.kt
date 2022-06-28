package com.bchmsl.homework5.tools

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import java.lang.Exception

class FirebaseExceptionHandler {

    fun handleException(e: Exception?): String{
        return when (e){
            is FirebaseAuthInvalidUserException -> "User not found"
            is FirebaseAuthInvalidCredentialsException -> "Password is invalid"
            else -> "An error occurred. Please, try again."
        }
    }
}