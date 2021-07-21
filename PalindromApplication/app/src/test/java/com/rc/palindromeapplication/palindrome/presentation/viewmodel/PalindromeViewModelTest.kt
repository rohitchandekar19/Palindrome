package com.rc.palindromeapplication.palindrome.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rc.palindromeapplication.palindrome.exception.InputEmptyException
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.isA
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PalindromeViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var palindromeLivedataObserver: Observer<Boolean>

    @Mock
    private lateinit var errorLivedataObserver: Observer<Throwable>

    private lateinit var viewModel: PalindromeViewModel

    @Before
    fun setup() {
        viewModel = PalindromeViewModel()
    }


    @Test
    fun testValidatePalindrome_validPalindrome() {
        val viewModel = PalindromeViewModel()
        viewModel.validatePalindrome("11")
        viewModel.palindromeLiveData.observeForever(palindromeLivedataObserver)
        verify(palindromeLivedataObserver).onChanged(true)
    }

    @Test
    fun testValidatePalindrome_inValidPalindrome() {
        val viewModel = PalindromeViewModel()
        viewModel.validatePalindrome("12")
        viewModel.palindromeLiveData.observeForever(palindromeLivedataObserver)
        verify(palindromeLivedataObserver).onChanged(false)
    }

    @Test
    fun testValidatePalindrome_inputEmptyError() {
        val viewModel = PalindromeViewModel()
        viewModel.validatePalindrome("")
        viewModel.errorLiveData.observeForever(errorLivedataObserver)
        verify(errorLivedataObserver).onChanged(isA(InputEmptyException::class.java))
    }
}