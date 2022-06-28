package com.bchmsl.homework5.tools

import android.util.Patterns

class FieldsChecker {
    fun checkFields(field: Field): Boolean {
        return if (field.value.isNotEmpty()) {
            when (field.type) {
                FieldType.EMAIL -> checkEmail(field)
                FieldType.PASSWORD -> checkPassword(field)
                FieldType.USERNAME -> checkUsername(field)
            }
        } else {
            false
        }
    }

    private fun checkEmail(field: Field): Boolean = Patterns.EMAIL_ADDRESS.matcher(field.value).matches()

    private fun checkPassword(field: Field): Boolean = field.value.length >= 8

    private fun checkUsername(field: Field): Boolean = field.value.length >= 8
}
