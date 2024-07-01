package rolek.bartlomiej.githubtrending.ui.feature.projectDetails

import rolek.bartlomiej.githubtrending.model.ProjectItem
import rolek.bartlomiej.githubtrending.ui.base.ViewEvent
import rolek.bartlomiej.githubtrending.ui.base.ViewSideEffect
import rolek.bartlomiej.githubtrending.ui.base.ViewState

class ProjectDetailsContract {

    sealed class Event : ViewEvent {
        data object Retry : Event()
        data object BackButtonClicked : Event()
        data object StarProject : Event()
    }

    data class State(
        val project: ProjectItem?,
        val isLoading: Boolean,
        val isError: Boolean,
        val isStarred: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object Back : Navigation()
        }
    }

}