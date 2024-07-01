package rolek.bartlomiej.githubtrending.ui.feature.projectDetails

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import rolek.bartlomiej.githubtrending.data.repository.ProjectsRepository
import rolek.bartlomiej.githubtrending.ui.base.BaseViewModel

@HiltViewModel(assistedFactory = ProjectDetailsViewModel.DetailsViewModelFactory::class)
class ProjectDetailsViewModel @AssistedInject constructor(@Assisted val projectUrl: String, private val repository: ProjectsRepository) :
    BaseViewModel<ProjectDetailsContract.Event, ProjectDetailsContract.State, ProjectDetailsContract.Effect>() {

    @AssistedFactory
    interface DetailsViewModelFactory {
        fun create(projectUrl: String): ProjectDetailsViewModel
    }
    init {
        getProjects()
        println("ProjectDetailsViewModel $projectUrl")
    }

    override fun setInitialState(): ProjectDetailsContract.State {
        return ProjectDetailsContract.State(
//            projectUrl = projectUrl,
           project = null,
            isLoading = false,
            isError = false,
            isStarred = false
        )
    }

    override fun handleEvents(event: ProjectDetailsContract.Event) {
        when (event) {
            is ProjectDetailsContract.Event.Retry -> {
                getProjects()
            }

            is ProjectDetailsContract.Event.BackButtonClicked -> {
                setEffect { ProjectDetailsContract.Effect.Navigation.Back }
            }
            is ProjectDetailsContract.Event.StarProject -> {
                setState { copy(isStarred = !isStarred) }
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
//                    setEffect { ProjectDetailsContract.Effect.DataWasLoaded }
//                }
//                .onFailure {
//                    println(it.message)
//                    setState { copy(isError = true, isLoading = false) }
//                }
//        }
    }
}