package rolek.bartlomiej.githubtrending.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val httpClient: HttpClient) : ApiService {

    override suspend fun getTrendingRepositories(page: Int): HttpResponse =
         httpClient.get("/search/repositories?q=stars:>0&sort=stars&order=desc&per_page=${PER_PAGE}") {
             parameter("page", page)
         }


    companion object {
        const val PER_PAGE = 20
    }
}