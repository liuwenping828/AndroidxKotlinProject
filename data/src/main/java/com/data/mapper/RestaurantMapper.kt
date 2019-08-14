package com.data.mapper

import com.data.response.RestaurantResponse
import com.domain.repository.restaurant.model.Restaurant
import javax.inject.Inject

/**
 * 将 Response 对象转换为 model 对象
 */
class RestaurantMapper @Inject constructor() {

    fun map(responseList: List<RestaurantResponse>): List<Restaurant> {
        return responseList.map { (map(it)) }
    }

    fun map(response: RestaurantResponse): Restaurant {
        return Restaurant(
            id = response.id,
            name = response.name,
            description = response.description,
            coverImgUrl = response.coverImgUrl,
            status = response.status,
            deliveryFee = response.deliveryFee
        )
    }

}