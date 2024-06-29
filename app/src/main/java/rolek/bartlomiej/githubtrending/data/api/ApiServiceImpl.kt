package rolek.bartlomiej.githubtrending.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val httpClient: HttpClient) : ApiService {

    override suspend fun getTrendingRepositories(language: String): HttpResponse =
         httpClient.get("/search/repositories?q=language:${language}&sort=stars&order=desc&per_page=10")


//    = flow {
//        emit(ApiResult.Loading())
//        try {
//            val response =
//                httpClient.get("/search/repositories?q=language:${language}&sort=stars&order=desc&per_page=10")
//
//            emit(ApiResult.Success(response.body<RepoListWrapper>().items))
//        } catch (e: Exception) {
//            e.printStackTrace()
//            emit(ApiResult.Error(e.message ?: "Unknown error"))
//        }
//    }
}