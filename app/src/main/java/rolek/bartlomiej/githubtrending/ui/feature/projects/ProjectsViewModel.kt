package rolek.bartlomiej.githubtrending.ui.feature.projects

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rolek.bartlomiej.githubtrending.data.repository.ProjectsRepository
import rolek.bartlomiej.githubtrending.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(private val repository: ProjectsRepository) :
    BaseViewModel<ProjectsContract.Event, ProjectsContract.State, ProjectsContract.Effect>() {

    init {
        getProjects()
    }

    override fun setInitialState(): ProjectsContract.State {
        return ProjectsContract.State(
            projectsList = emptyList(),
            isLoading = true,
            isError = false
        )
    }

    override fun handleEvents(event: ProjectsContract.Event) {
        when (event) {
            is ProjectsContract.Event.Retry -> {
                getProjects()
            }

            is ProjectsContract.Event.ProjectClicked -> {
                setEffect { ProjectsContract.Effect.Navigation.ToDetails(event.project.node_id) }
            }
        }
    }

    private fun getProjects() {
        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }

            repository.getTrendingRepositories("kotlin")
                .onSuccess { projects ->
                    setState { copy(projectsList = projects, isLoading = false) }
                    setEffect { ProjectsContract.Effect.DataWasLoaded }
                }
                .onFailure {
                    setState { copy(isError = true, isLoading = false) }
                }
        }
    }


}