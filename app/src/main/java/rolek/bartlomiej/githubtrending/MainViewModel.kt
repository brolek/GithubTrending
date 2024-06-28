package rolek.bartlomiej.githubtrending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import rolek.bartlomiej.githubtrending.api.ApiService
import rolek.bartlomiej.githubtrending.api.util.ApiResult
import rolek.bartlomiej.githubtrending.model.RepoItem
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val TAG = MainViewModel::class.java.name
    private val _repos = MutableStateFlow<ApiResult<List<RepoItem>>>(ApiResult.Loading())
    val repos = _repos.asStateFlow()

    init {
        fetchRepos()
    }

    private fun fetchRepos() {
        print("fetchRepos")
        viewModelScope.launch {
            apiService.getTrendingRepositories("kotlin")
                .flowOn(dispatcher)
                .catch { _repos.value = ApiResult.Error(it.message ?: "Error") }
                .collect {
                    _repos.value = it
                }
        }
    }
}