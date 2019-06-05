package io.github.luteoos.mathcanvasdraw.network

import io.github.luteoos.mathcanvasdraw.network.request.loginRequest
import io.github.luteoos.mathcanvasdraw.network.request.registerRequest
import io.github.luteoos.mathcanvasdraw.network.response.ChartResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {

//    baseurl z /api/
    /**
     * /api/Users/
     */
    @POST("Users/login")
    fun login(@Body loginRequest: loginRequest): Single<Response<Any?>>

    @POST("Users/register")
    fun register(@Body registerRequest: registerRequest) : Single<Response<Any?>>

    /**
     * /api/Chart/
     */

    @GET("Chart")
    fun testGETCHART(): Single<Response<ChartResponse>>

}