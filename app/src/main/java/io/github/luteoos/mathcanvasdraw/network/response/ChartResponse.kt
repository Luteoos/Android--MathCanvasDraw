package io.github.luteoos.mathcanvasdraw.network.response

import io.github.luteoos.mathcanvasdraw.network.BaseData
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ChartResponse: BaseData, RealmObject(){
    @PrimaryKey
    override var guid: String? = null
    override var creationDate: String? = null
    var name: String? = null
    var logo: String? = null
    var functions: RealmList<FunctionResponse>? = null

}