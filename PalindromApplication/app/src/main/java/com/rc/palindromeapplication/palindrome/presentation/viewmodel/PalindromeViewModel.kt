package com.rc.palindromeapplication.palindrome.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rc.palindromeapplication.palindrome.exception.InputEmptyException
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PalindromeViewModel @Inject constructor() : ViewModel() {

    private val palindromeMutableLiveData = MutableLiveData<Boolean>()
    val palindromeLiveData get() = palindromeMutableLiveData

    private val errorMutableLiveData = MutableLiveData<Throwable>()
    val errorLiveData get() = errorMutableLiveData

    fun validatePalindrome(input: String) {
        if (input.isEmpty()) {
            errorLiveData.value = InputEmptyException()
            return
        }

        // Validating the input text as palindrome or not
        palindromeLiveData.value = isPalindrome(input)
    }

    private fun isPalindrome(input: String): Boolean {
        val lowercaseInput =
            input.lowercase(Locale.getDefault()).replace(VALID_CHAR_REGEX.toRegex(), "")
        return lowercaseInput == lowercaseInput.reversed()
    }

    companion object {
        private const val VALID_CHAR_REGEX = "[^a-zA-Z0-9]"
    }
}