package io.github.luteoos.mathcanvasdraw.network

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

class BaseData: Serializable {
    var uuid: String? = null

    val uid: UUID?
        get() = UUID.fromString(uuid)
}