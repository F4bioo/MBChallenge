package com.fappslab.mbchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.fappslab.mbchallenge.libraries.arch.di.FEATURE_ROUTES_QUALIFIER
import com.fappslab.mbchallenge.libraries.arch.navigation.extension.NavHostControllerProvider
import com.fappslab.mbchallenge.libraries.design.theme.PlutoTheme
import com.fappslab.mbchallenge.presentation.compose.MainNavGraph
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            PlutoTheme(
                statusBarColor = Color.Transparent,
                navigationBarColor = Color.Transparent,
            ) {
                Surface {
                    NavHostControllerProvider { navController ->
                        MainNavGraph(
                            navController = navController,
                            featureRoutes = koinInject(named(FEATURE_ROUTES_QUALIFIER))
                        )
                    }
                }
            }
        }
    }
}
