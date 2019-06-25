package io.github.luteoos.mathcanvasdraw.network

import io.github.luteoos.mathcanvasdraw.network.request.FunctionRequest
import io.github.luteoos.mathcanvasdraw.network.request.LoginRequest
import io.github.luteoos.mathcanvasdraw.network.request.RegisterRequest
import io.github.luteoos.mathcanvasdraw.network.response.ChartResponse
import io.github.luteoos.mathcanvasdraw.network.response.FunctionEvaulateResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface API {

//    baseurl z /api/
    /**
     * /api/Users/
     */
    @POST("Users/login")
    fun login(@Body loginRequest: LoginRequest): Single<Response<Any?>>

    @POST("Users/register")
    fun register(@Body registerRequest: RegisterRequest) : Single<Response<Any?>>

    /**
     * /api/Chart/
     */

    @POST("Chart")
    fun postChart(@Body name: String) : Single<Response<String>>

    @GET("Chart/{id}")
    fun getChart(@Path("id") id: String): Single<Response<ChartResponse>>

    /**
     * /api/Function/
     */

    @POST("Function")
    fun postFunction(@Body function : FunctionRequest) : Single<Response<String>>

    @GET("Function/{id}/evaluate")
    fun getEvvaluateFunction(@Path("id") id: String) : Single<Response<FunctionEvaulateResponse>>

}