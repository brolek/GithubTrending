package rolek.bartlomiej.githubtrending.model

@kotlinx.serialization.Serializable
data class License(
    val key: String,
    val name: String,
    val node_id: String,
    val spdx_id: String,
    val url: String
)