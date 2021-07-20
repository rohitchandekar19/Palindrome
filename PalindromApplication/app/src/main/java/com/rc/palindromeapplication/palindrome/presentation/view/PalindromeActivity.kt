package com.rc.palindromeapplication.palindrome.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rc.palindromeapplication.R
import com.rc.palindromeapplication.databinding.ActivityPalindromeBinding
import com.rc.palindromeapplication.extension.hideKeyboard
import com.rc.palindromeapplication.extension.showKeyboard
import com.rc.palindromeapplication.palindrome.exception.InputEmptyException
import com.rc.palindromeapplication.palindrome.presentation.viewmodel.PalindromeViewModel

class PalindromeActivity : AppCompatActivity() {

    private val palindromeViewModel: PalindromeViewModel by viewModels()
    private lateinit var binding: ActivityPalindromeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPalindromeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListener()
        observeError()
        observeSuccess()
        binding.inputEditText.showKeyboard()
    }

    private fun setOnClickListener() {
        binding.checkPalindrome.setOnClickListener {
            palindromeViewModel.validatePalindrome(binding.inputEditText.text.toString())
            it.hideKeyboard()
        }
    }

    private fun observeSuccess() {
        palindromeViewModel.palindromeLiveData.observe(this, {
            val message = if (it) {
                getString(R.string.valid_palindrome)
            } else {
                getString(R.string.not_valid_palindrome)
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun observeError() {
        palindromeViewModel.errorLiveData.observe(this, {
            if (it is InputEmptyException) {
                Toast.makeText(this, getString(R.string.empty_error), Toast.LENGTH_SHORT).show()
            }
        })
    }
}