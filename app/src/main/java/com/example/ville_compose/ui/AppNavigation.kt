package com.example.ville_compose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ville_compose.viewmodel.CityListViewModel

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object CityList : Screen("city_list", "Villes", Icons.Default.List)
    object AddCity : Screen("add_city", "Ajouter", Icons.Default.Add)
}

val bottomNavItems = listOf(
    Screen.CityList,
    Screen.AddCity,
)

@Composable
fun AppNavigation(viewModel: CityListViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = bottomNavItems.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = screen.label) },
                            label = { Text(screen.label) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.CityList.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.CityList.route) {
                CityListScreen(
                    viewModel = viewModel,
                    onCityClick = { city ->
                        navController.navigate("city_detail/${city.name}")
                    }
                )
            }
            composable(Screen.AddCity.route) {
                AddCityScreen(
                    viewModel = viewModel,
                    onNavigateBack = {
                        navController.navigate(Screen.CityList.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                        }
                    }
                )
            }
            composable(
                route = "city_detail/{cityName}",
                arguments = listOf(navArgument("cityName") { type = NavType.StringType })
            ) { backStackEntry ->
                val cityName = backStackEntry.arguments?.getString("cityName")
                val city = viewModel.cities.value.find { it.name == cityName }
                if (city != null) {
                    CityDetailScreen(
                        city = city, 
                        onEditClick = {
                            navController.navigate("edit_city/${city.name}")
                        },
                        onNavigateBack = { navController.navigateUp() }
                    )
                }
            }
            composable(
                route = "edit_city/{cityName}",
                arguments = listOf(navArgument("cityName") { type = NavType.StringType })
            ) { backStackEntry ->
                val cityName = backStackEntry.arguments?.getString("cityName")
                val city = viewModel.cities.value.find { it.name == cityName }
                if (city != null) {
                    EditCityScreen(
                        city = city,
                        onUpdateCity = { updatedCity ->
                            viewModel.updateCity(updatedCity)
                            navController.popBackStack()
                        },
                        onDeleteCity = { cityId ->
                            viewModel.deleteCity(cityId)
                            navController.popBackStack(Screen.CityList.route, inclusive = false)
                        },
                        onNavigateBack = { navController.navigateUp() }
                    )
                }
            }
        }
    }
}
