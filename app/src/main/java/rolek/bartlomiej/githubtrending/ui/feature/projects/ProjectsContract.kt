package rolek.bartlomiej.githubtrending.ui.feature.projects

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import rolek.bartlomiej.githubtrending.model.ProjectItem
import rolek.bartlomiej.githubtrending.ui.base.ViewEvent
import rolek.bartlomiej.githubtrending.ui.base.ViewSideEffect
import rolek.bartlomiej.githubtrending.ui.base.ViewState

class ProjectsContract {

    sealed class Event : ViewEvent {
        data object Retry : Event()
        data class ProjectClicked(val project: ProjectItem) : Event()
        data object LoadMore: Event()
    }

    data class State(
        val projectsPager: Flow<PagingData< ProjectItem>>,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data object DataWasLoaded : Effect()
        sealed class Navigation : Effect() {
            data class ToDetails(val project: ProjectItem) : Navigation()
        }
    }
}