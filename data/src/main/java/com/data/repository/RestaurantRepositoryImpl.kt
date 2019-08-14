package com.data.repository

import com.data.RestaurantApi
import com.data.mapper.RestaurantMapper
import com.domain.repository.restaurant.model.Restaurant
import com.domain.repository.restaurant.repository.RestaurantRepository
import io.reactivex.Single

/**
 * RestaurantRepositoryImpl 实现 RestaurantRepository 接口
 * RestaurantRepository 负责从Api层 获取对应接口数据
 */
class RestaurantRepositoryImpl constructor(
    private val restaurantApi: RestaurantApi,
    private val restaurantMapper: RestaurantMapper
) : RestaurantRepository {

    override fun getRestaurantList(lat: Double, lng: Double, offset: Int, limit: Int): Single<List<Restaurant>> {
        return restaurantApi.getRestaurantList(lat, lng, offset, limit)
            .map {
                restaurantMapper.map(it)
            }
    }

    override fun getRestaurant(id: Int): Single<Restaurant> {
        return restaurantApi.getRestaurant(id)
            .map {
                restaurantMapper.map(it)
            }
    }
}