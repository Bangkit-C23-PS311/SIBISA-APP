package com.coding.sibisa.data.pref

sealed class Compact<out R> private constructor(){
    data class Succes<out T>(val data: T): Compact<T>()
    data class Error(val error: String): Compact<Nothing>()
    object Loading: Compact<Nothing>()
}



