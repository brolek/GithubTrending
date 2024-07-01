package rolek.bartlomiej.githubtrending.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import rolek.bartlomiej.githubtrending.model.ProjectItem
import rolek.bartlomiej.githubtrending.ui.navigation.NavigationKey.Arg.PROJECT_URL
import rolek.bartlomiej.githubtrending.ui.navigation.destinations.ProjectDetailsDestination
import rolek.bartlomiej.githubtrending.ui.navigation.destinations.ProjectsDestination
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationKey.Route.PROJECTS
    ) {
        composable(
            route = NavigationKey.Route.PROJECTS
        ) {
            ProjectsDestination(navController)
        }

        composable(
            route = NavigationKey.Route.PROJECT_DETAILS,
            arguments = listOf(
                navArgument(name = PROJECT_URL) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val projectUrl =
                requireNotNull(backStackEntry.arguments?.getString(PROJECT_URL)) { "Project url is required as an argument" }
            ProjectDetailsDestination(
                projectUrl = projectUrl,
                navController = navController
            )
        }
    }
}

object NavigationKey {

    object Arg {
        const val PROJECT_URL = "project_url"
    }

    object Route {
        const val PROJECTS = "projects"
        const val PROJECT_DETAILS = "$PROJECTS/{${PROJECT_URL}}"
    }

}

fun NavController.navigateToProjectDetails(project: ProjectItem) {
    val encodedUrl = URLEncoder.encode(project.url, StandardCharsets.UTF_8.toString())
    navigate(route = "${NavigationKey.Route.PROJECTS}/$encodedUrl")
}