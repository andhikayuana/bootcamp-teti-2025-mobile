package id.yuana.bootcamp.demo.data.source.response

import kotlinx.serialization.Serializable

@Serializable
data class GetRandomResponse(
    val code: Int,
    val msg: String,
    val data: String
)


//{
//    "code": 200,
//    "msg": "Success",
//    "data": "Hewan apa yang taat lalu lintas? Unta. Unta-makan keselamatan."
//}