package io.github.luteoos.mathcanvasdraw.network.response

import com.google.gson.annotations.SerializedName
import io.github.luteoos.mathcanvasdraw.network.BaseData
import retrofit2.http.Field

open class ChartResponse: BaseData{
    override var guid: String? = null
    override var creationDate: String? = null
    var logo: String? = null
    var functions: MutableList<FunctionResponse>? = null

}