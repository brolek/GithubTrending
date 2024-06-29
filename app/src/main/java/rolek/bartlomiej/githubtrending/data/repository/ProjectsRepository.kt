package rolek.bartlomiej.githubtrending.data.repository

import rolek.bartlomiej.githubtrending.model.ProjectItem

interface ProjectsRepository {
    suspend fun getTrendingRepositories(page: Int): Result<List<ProjectItem>>
}