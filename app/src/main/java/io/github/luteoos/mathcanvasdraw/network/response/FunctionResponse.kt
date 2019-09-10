package io.github.luteoos.mathcanvasdraw.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.github.luteoos.mathcanvasdraw.network.BaseData
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey

open class FunctionResponse: BaseData, RealmObject(){
    @PrimaryKey
    override var guid: String? = null
    override var creationDate: String? = null
    var min: Double? = null
    var max: Double? = null
    var approximation: Int? = null
    var name: String? = null
    @SerializedName("parameters")
    var functionBody: String? = null
    @Ignore
    var color: String = "#FFFFFF"
    @Expose(serialize = false, deserialize = false)
    var customColor: String? = null
}
