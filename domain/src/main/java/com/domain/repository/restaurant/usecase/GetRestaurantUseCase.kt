package com.domain.repository.restaurant.usecase

import com.domain.repository.restaurant.model.Restaurant
import com.domain.repository.restaurant.repository.RestaurantRepository
import com.domain.repository.restaurant.usecase.GetRestaurantUseCase.Result.Success
import com.domain.repository.restaurant.usecase.GetRestaurantUseCase.Result.Failure
import com.domain.repository.restaurant.usecase.GetRestaurantUseCase.Result.Loading
import io.reactivex.Observable
import javax.inject.Inject

class GetRestaurantUseCase @Inject constructor(private val restaurantRepository: RestaurantRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val restaurants: Restaurant) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun execute(id: Int): Observable<Result> {
        return restaurantRepository.getRestaurant(id)
            .toObservable()
            .map { Success(it) as Result }
            .onErrorReturn { Failure(it) }
            .startWith(Loading)
    }
}