package rolek.bartlomiej.githubtrending.ui.feature.projects

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import rolek.bartlomiej.githubtrending.data.repository.ProjectsRepository
import rolek.bartlomiej.githubtrending.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(private val repository: ProjectsRepository) :
    BaseViewModel<ProjectsContract.Event, ProjectsContract.State, ProjectsContract.Effect>() {

    override fun setInitialState(): ProjectsContract.State {
        return ProjectsContract.State(
            projectsPager = Pager (PagingConfig(pageSize = PAGE_SIZE)) {
            ProjectsPagingSource(repository)
        }.flow,
            isLoading = false,
            isError = false
        )
    }

    override fun handleEvents(event: ProjectsContract.Event) {
        when (event) {
            is ProjectsContract.Event.Retry -> {
                getProjects()
            }

            is ProjectsContract.Event.ProjectClicked -> {
                setEffect { ProjectsContract.Effect.Navigation.ToDetails(event.project) }
            }
            is ProjectsContract.Event.LoadMore -> {
                getProjects()
            }
        }
    }

    private fun getProjects() {
//        viewModelScope.launch {
//            setState { copy(isLoading = true, isError = false) }
//
//            repository.getTrendingRepositories(currentPage)
//                .onSuccess { projects ->
//                    setState { copy(isLoading = false) }
//                    currentPage++
//                    setEffect { ProjectsContract.Effect.DataWasLoaded }
//                }
//                .onFailure {
//                    println(it.message)
//                    setState { copy(isError = true, isLoading = false) }
//                }
//        }
    }
}