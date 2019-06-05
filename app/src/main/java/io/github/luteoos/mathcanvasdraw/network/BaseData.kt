package io.github.luteoos.mathcanvasdraw.network

import java.io.Serializable
import java.util.*

interface BaseData: Serializable {
    var guid: String?
    var creationDate : String?
    val uid: UUID?
        get() = UUID.fromString(guid)

    val date: String?
        get() =  creationDate?.format("yyyy-MM-dd'T'HH:mm'Z'")
}