package rolek.bartlomiej.githubtrending.ui.feature.projects

import rolek.bartlomiej.githubtrending.model.ProjectItem
import rolek.bartlomiej.githubtrending.ui.base.ViewEvent
import rolek.bartlomiej.githubtrending.ui.base.ViewSideEffect
import rolek.bartlomiej.githubtrending.ui.base.ViewState

class ProjectsContract {

    sealed class Event : ViewEvent {
        object Retry : Event()
        data class ProjectClicked(val project: ProjectItem) : Event()
    }

    data class State(
        val projectsList: List<ProjectItem>,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()
        sealed class Navigation : Effect() {
            data class ToDetails(val projectId: String) : Navigation()
        }
    }
}