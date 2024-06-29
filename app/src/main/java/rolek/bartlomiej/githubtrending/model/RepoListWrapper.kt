package rolek.bartlomiej.githubtrending.model

@kotlinx.serialization.Serializable
data class RepoListWrapper(
    val incomplete_results: Boolean,
    val items: List<ProjectItem>,
    val total_count: Int
)