package com.domain.repository.restaurant.usecase

import com.domain.repository.restaurant.model.Restaurant
import com.domain.repository.restaurant.repository.RestaurantRepository
import com.domain.repository.restaurant.usecase.GetRestaurantListUseCase.Result.Success
import com.domain.repository.restaurant.usecase.GetRestaurantListUseCase.Result.Failure
import com.domain.repository.restaurant.usecase.GetRestaurantListUseCase.Result.Loading

import io.reactivex.Observable
import javax.inject.Inject

class GetRestaurantListUseCase @Inject constructor(private val restaurantRepository: RestaurantRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val restaurants: List<Restaurant>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun execute(lat: Double, lng: Double, offset: Int, limit: Int): Observable<Result> {
        return restaurantRepository.getRestaurantList(lat, lng, offset, limit)
            .toObservable()
            .map { Success(it) as Result }
            .onErrorReturn { Failure(it) }
            .startWith(Loading)
    }

}