package com.domain.repository.restaurant.repository

import com.domain.repository.restaurant.model.Restaurant
import io.reactivex.Single

interface RestaurantRepository {

    fun getRestaurantList(lat: Double, lng: Double, offset: Int, limit: Int): Single<List<Restaurant>>

    fun getRestaurant(id: Int): Single<Restaurant>

}