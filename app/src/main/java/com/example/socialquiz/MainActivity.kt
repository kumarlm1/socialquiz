package com.example.socialquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.socialquiz.common.SubCategoryScreen
import com.example.socialquiz.screen.home.HomeScreen
import com.example.socialquiz.screen.notes.NotesScreen
import com.example.socialquiz.screen.profile.ProfileScreen
import com.example.socialquiz.ui.theme.SocialQuizTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen() {
    val navController  = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
    ){ paddingvalues ->
        println("valuess "+paddingvalues.calculateBottomPadding())
        BottomnavGraph(paddingvalues,navController = navController)
    }
}


@Composable
fun BottomnavGraph(
    padding : PaddingValues,
    navController : NavHostController)
{
    NavHost( navController = navController,
    startDestination = BottomBarScreen.Home.route){
        composable(BottomBarScreen.Home.route) {
            HomeScreen(padding){
                navController.navigate("category")
            }
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen(padding)
        }
        composable(BottomBarScreen.Notes.route) {
            NotesScreen(padding)

        }
        composable("category"){
            SubCategoryScreen(padding) {
                navController.navigateUp()
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        modifier = Modifier
            .graphicsLayer {
                shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                clip = true

            }
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        MaterialTheme.colors.background,
                    )
                )
            ),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        println("rendered")

        AddItem(screen = BottomBarScreen.Home, currentDestination = currentDestination, navController = navController)
        AddItem(screen = BottomBarScreen.Profile, currentDestination = currentDestination, navController = navController)
        AddItem(screen = BottomBarScreen.Notes, currentDestination = currentDestination, navController = navController)


    }


}


@Composable
fun RowScope.AddItem(
    screen : BottomBarScreen,
    currentDestination : NavDestination?,
    navController: NavHostController
){
    println("route $currentDestination")
    BottomNavigationItem(
        modifier=Modifier.background(Color.Transparent),
        label= { Text(screen.title,color = MaterialTheme.colors.primary)  },
        selected = currentDestination?.route == screen.route,
        icon = {
            Icon(
                imageVector = if(currentDestination?.route == screen.route) screen.selectedIcon else screen.icon,
                contentDescription = screen.title,
            tint = MaterialTheme.colors.primary)
        },
        onClick = {
            println("route ${screen.route}")
        navController.navigate(screen.route){

            launchSingleTop = true
        }
        },
        unselectedContentColor = LocalContentColor.current.copy(ContentAlpha.disabled),

    )


}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SocialQuizTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MainScreen()
        }
    }
}