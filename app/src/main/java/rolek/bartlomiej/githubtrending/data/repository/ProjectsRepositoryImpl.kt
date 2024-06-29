package rolek.bartlomiej.githubtrending.data.repository

import io.ktor.client.call.body
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rolek.bartlomiej.githubtrending.data.api.ApiService
import rolek.bartlomiej.githubtrending.model.RepoListWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    ProjectsRepository {
    override suspend fun getTrendingRepositories(language: String) = kotlin.runCatching {
        withContext(dispatcher) {
            apiService.getTrendingRepositories(language).body<RepoListWrapper>().items
        }
    }


}