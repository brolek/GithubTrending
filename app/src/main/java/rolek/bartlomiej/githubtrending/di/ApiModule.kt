package rolek.bartlomiej.githubtrending.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import rolek.bartlomiej.githubtrending.BuildConfig
import rolek.bartlomiej.githubtrending.api.ApiService
import rolek.bartlomiej.githubtrending.api.ApiServiceImpl
import rolek.bartlomiej.githubtrending.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url(Constants.BASE_URL)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("Accept", "application/vnd.github.v3+json")
                header(HttpHeaders.Authorization, BuildConfig.GITHUB_TOKEN)

            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true; coerceInputValues = true })
            }

            engine {
                connectTimeout = 100_000
                socketTimeout = 100_000
            }

        }
    }

    @Singleton
    @Provides
    fun provideApiService(httpClient: HttpClient): ApiService = ApiServiceImpl(httpClient)

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Default
}