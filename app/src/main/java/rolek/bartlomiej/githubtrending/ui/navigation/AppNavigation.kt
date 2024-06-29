package rolek.bartlomiej.githubtrending.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rolek.bartlomiej.githubtrending.ui.navigation.NavigationKey.Arg.PROJECT_ID
import rolek.bartlomiej.githubtrending.ui.navigation.destinations.ProjectsDestination

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

//        composable(
//            route = NavigationKey.Route.PROJECT_DETAILS,
//            arguments = listOf(navArgument(name = PROJECT_ID) {
//                type = NavType.StringType
//            })
//        ) { backStackEntry ->
//            val userId = requireNotNull(backStackEntry.arguments?.getString(PROJECT_ID)) { "User id is required as an argument" }
//            ReposScreenDestination(
//                UserId = userId,
//                navController = navController
//            )
//        }
    }
}

object NavigationKey {

    object Arg {
        const val PROJECT_ID = "user_id"
    }

    object Route {
        const val PROJECTS = "projects"
        const val PROJECT_DETAILS = "$PROJECTS/{$PROJECT_ID}"
    }

}

fun NavController.navigateToProjectDetails(userId: String) {
    navigate(route = "${NavigationKey.Route.PROJECTS}/$userId")
}