package id.yuana.bootcamp.demo.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Product(
    val id: String,
    val name: String,
    val data: JsonObject? = null
)
