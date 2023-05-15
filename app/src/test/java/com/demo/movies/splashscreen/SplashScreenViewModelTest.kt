package com.demo.movies.splashscreen

import com.demo.shared_test.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashScreenViewModelTest {

    private lateinit var testObject: SplashScreenViewModel

    @get:Rule
    var testCoroutineRUle = MainCoroutineRule()

    @Before
    fun setUp() {
        testObject = SplashScreenViewModel()
    }

    @Test
    fun `onSplashScreen test initial loading`() =
        runTest {
            testObject.load(3000L)
            assert(testObject.state.value == SplashScreenViewModel.SplashScreenUiState.Loading)

        }


}