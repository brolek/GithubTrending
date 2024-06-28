package rolek.bartlomiej.githubtrending.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rolek.bartlomiej.githubtrending.api.util.ApiResult
import rolek.bartlomiej.githubtrending.model.RepoItem
import rolek.bartlomiej.githubtrending.model.RepoListWrapper
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val httpClient: HttpClient) : ApiService {

    override fun getTrendingRepositories(language: String): Flow<ApiResult<List<RepoItem>>> = flow {
        emit(ApiResult.Loading())
        try {
            val response =
                httpClient.get("/search/repositories?q=language:${language}&sort=stars&order=desc&per_page=10")

            emit(ApiResult.Success(response.body<RepoListWrapper>().items))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message ?: "Unknown error"))
        }
    }
}