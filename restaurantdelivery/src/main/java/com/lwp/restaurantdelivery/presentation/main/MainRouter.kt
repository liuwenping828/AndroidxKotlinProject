package com.lwp.restaurantdelivery.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.lwp.restaurantdelivery.presentation.imagedetail.ImageDetailActivity
import com.lwp.restaurantdelivery.presentation.restaurantdetail.RestaurantDetailActivity
import java.lang.ref.WeakReference

/**
 * MainActivity 中路由导航
 */
class MainRouter(private val activityRef: WeakReference<Activity>) {

    enum class Route {
        IMAGE_DETAIL,
        RESTAURANT_DETAIL
    }

    fun navigation(route: Route, bundle: Bundle = Bundle()) {
        when (route) {
            Route.IMAGE_DETAIL -> {
                showNextScreen(ImageDetailActivity::class.java, bundle)
            }
            Route.RESTAURANT_DETAIL -> {
                showNextScreen(RestaurantDetailActivity::class.java, bundle)
            }
        }
    }

    private fun showNextScreen(clazz: Class<*>, bundle: Bundle) {
        activityRef.get()?.startActivity(Intent(activityRef.get(), clazz).putExtras(bundle))
    }


}