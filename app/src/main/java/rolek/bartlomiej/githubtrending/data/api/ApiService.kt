package rolek.bartlomiej.githubtrending.data.api

import io.ktor.client.statement.HttpResponse

interface ApiService {
    suspend fun getTrendingRepositories(language: String): HttpResponse
}