package rolek.bartlomiej.githubtrending.ui.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import rolek.bartlomiej.githubtrending.ui.feature.projectDetails.ProjectDetailsContract
import rolek.bartlomiej.githubtrending.ui.feature.projectDetails.ProjectDetailsScreen
import rolek.bartlomiej.githubtrending.ui.feature.projectDetails.ProjectDetailsViewModel

@Composable
fun ProjectDetailsDestination(projectUrl: String, navController: NavController) {
    val viewModel =
        hiltViewModel<ProjectDetailsViewModel, ProjectDetailsViewModel.DetailsViewModelFactory>(
            creationCallback = { factory -> factory.create(projectUrl = projectUrl) }
        )
    ProjectDetailsScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is ProjectDetailsContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        }
    )
}