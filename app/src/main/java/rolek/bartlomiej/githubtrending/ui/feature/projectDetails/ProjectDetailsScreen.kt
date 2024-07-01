package rolek.bartlomiej.githubtrending.ui.feature.projectDetails

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import rolek.bartlomiej.githubtrending.ui.common.Progress
import rolek.bartlomiej.githubtrending.ui.feature.projects.composables.ProjectListItem

@Composable
fun ProjectDetailsScreen(
    state: ProjectDetailsContract.State,
    effectFlow: Flow<ProjectDetailsContract.Effect>?,
    onEventSent: (event: ProjectDetailsContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: ProjectDetailsContract.Effect.Navigation) -> Unit
) {


    Scaffold { padding ->
        when {
            state.isLoading -> Progress()
//            state.isError -> RequestError(onRetryButtonClick = { onEventSent(ProjectsContract.Event.Retry) })
            else -> {
                Text(text = "Project Details")
            }
//                ProjectListItem(project = state.project) {
//                onEventSent(ProjectDetailsContract.Event.StarProject)
//            }
        }
    }

}