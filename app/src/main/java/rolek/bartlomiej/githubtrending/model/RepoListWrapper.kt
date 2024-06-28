package rolek.bartlomiej.githubtrending.model

@kotlinx.serialization.Serializable
data class RepoListWrapper(
    val incomplete_results: Boolean,
    val items: List<RepoItem>,
    val total_count: Int
)