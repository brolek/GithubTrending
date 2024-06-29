package rolek.bartlomiej.githubtrending.ui.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import rolek.bartlomiej.githubtrending.ui.feature.projects.ProjectsContract
import rolek.bartlomiej.githubtrending.ui.feature.projects.ProjectsScreen
import rolek.bartlomiej.githubtrending.ui.feature.projects.ProjectsViewModel
import rolek.bartlomiej.githubtrending.ui.navigation.navigateToProjectDetails

@Composable
fun ProjectsDestination(navController: NavController) {
    val viewModel = hiltViewModel<ProjectsViewModel>()
    ProjectsScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is ProjectsContract.Effect.Navigation.ToDetails) {
                navController.navigateToProjectDetails(navigationEffect.projectId)
            }
        }
    )
}