package rolek.bartlomiej.githubtrending.api

import kotlinx.coroutines.flow.Flow
import rolek.bartlomiej.githubtrending.api.util.ApiResult
import rolek.bartlomiej.githubtrending.model.RepoItem
import rolek.bartlomiej.githubtrending.model.RepoListWrapper

interface ApiService {
    fun getTrendingRepositories(language: String): Flow<ApiResult<List<RepoItem>>>
}