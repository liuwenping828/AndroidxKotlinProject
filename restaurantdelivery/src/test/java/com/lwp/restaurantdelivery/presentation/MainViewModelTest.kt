package com.lwp.restaurantdelivery.presentation

import com.domain.repository.restaurant.model.Restaurant
import com.domain.repository.restaurant.usecase.GetRestaurantListUseCase
import com.domain.repository.restaurant.usecase.GetRestaurantListUseCase.Result
import com.lwp.restaurantdelivery.presentation.main.MainRouter
import com.lwp.restaurantdelivery.presentation.main.MainViewModel
import com.lwp.restaurantdelivery.rx.RxJavaTestHooksResetRule
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

    @Mock
    lateinit var getRestaurantListUseCase: GetRestaurantListUseCase
    @Mock
    lateinit var mainRouter: MainRouter
    private lateinit var sut: MainViewModel

    @Before
    fun setUp() {
        sut = MainViewModel(mainRouter, getRestaurantListUseCase)
    }

    @Test
    fun `bound retrieves the restaurant list`() {
        given(getRestaurantListUseCase.execute(any(), any(), any(), any()))
            .willReturn(Observable.just(mock()))

        sut.bound()

        verify(getRestaurantListUseCase).execute(any(), any(), any(), any())
    }

    @Test
    fun `bound shows error when retrieving the restaurants fail`() {
        given(getRestaurantListUseCase.execute(any(), any(), any(), any()))
            .willReturn(Observable.just(Result.Failure(Throwable())))

        sut.bound()

        sut.showErrorGettingRestaurants.observe().test().assertValue(true)
    }

    @Test
    fun `bound adds restaurants to list when successful`() {
        val restaurants = arrayListOf<Restaurant>(mock(), mock())
        given(getRestaurantListUseCase.execute(any(), any(), any(), any()))
            .willReturn(Observable.just(Result.Success(restaurants)))

        sut.bound()

        MatcherAssert.assertThat(sut.restaurantList.size, CoreMatchers.equalTo(2))
    }

    @Test
    fun `unbound clears disposables`() {
        val restaurants = arrayListOf<Restaurant>(mock(), mock())
        given(getRestaurantListUseCase.execute(any(), any(), any(), any()))
            .willReturn(Observable.just(Result.Success(restaurants)))

        sut.bound()
        MatcherAssert.assertThat(sut.disposables.size(), CoreMatchers.equalTo(1))

        sut.unbound()
        MatcherAssert.assertThat(sut.disposables.size(), CoreMatchers.equalTo(0))
    }

    @Test
    fun `onRestaurantClicked shows restaurant detail screen`() {
        val restaurantId = 1
        val restaurant = mock<Restaurant> { on { id } doReturn restaurantId }

        sut.onRestaurantClicked(restaurant)

        verify(mainRouter).navigate(eq(MainRouter.Route.RESTAURANT_DETAIL), any())
    }

    @Test
    fun `onImageClicked shows image detail screen`() {
        val restaurant = mock<Restaurant>()

        sut.onRestaurantImageClicked(restaurant)

        verify(mainRouter).navigate(eq(MainRouter.Route.IMAGE_DETAIL), any())

    }


}