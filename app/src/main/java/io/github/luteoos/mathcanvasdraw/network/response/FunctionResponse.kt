package io.github.luteoos.mathcanvasdraw.network.response

import com.google.gson.annotations.SerializedName
import io.github.luteoos.mathcanvasdraw.network.BaseData

class FunctionResponse: BaseData {
    override var guid: String? = null
    override var creationDate: String? = null
    var min: Double? = null
    var max: Double? = null
    var approximation: Double? = null
    var name: String? = null
    @SerializedName("parameters")
    var functionBody: String? = null

}
