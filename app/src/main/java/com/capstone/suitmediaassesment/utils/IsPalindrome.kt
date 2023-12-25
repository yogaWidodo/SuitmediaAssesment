package com.capstone.suitmediaassesment.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

object IsPalindrome {
    fun isPalindrome(input: String): Boolean {
        val trimmedInput = input.replace("\\s".toRegex(), "").lowercase()
        val reversedInput = trimmedInput.reversed()
        return trimmedInput == reversedInput
    }

    fun alertDialog(boolean: Boolean, context: Context) {
        if (boolean) {
            AlertDialog.Builder(context)
                .setTitle("Palindrome")
                .setMessage("This is Palindrome")
                .setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        } else {
            AlertDialog.Builder(context)
                .setTitle("Palindrome")
                .setMessage("This is not Palindrome")
                .setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}