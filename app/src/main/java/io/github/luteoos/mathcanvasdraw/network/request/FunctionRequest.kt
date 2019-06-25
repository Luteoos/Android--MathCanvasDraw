package io.github.luteoos.mathcanvasdraw.network.request

import java.io.Serializable

class FunctionRequest : Serializable {
    var min: Double? = null
    var max: Double? = null
    var approximation : Int? = null
    var name : String? = null
    var functionString : String? = null
}