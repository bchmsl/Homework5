package com.bchmsl.homework5.tools

data class Field(
    val type: FieldType,
    val value: String
)

enum class FieldType{
    EMAIL,
    PASSWORD,
    USERNAME;
}