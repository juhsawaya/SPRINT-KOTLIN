package com.fiap.lara.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fiap.lara.data.MockLaraData
import com.fiap.lara.model.JourneyStage
import com.fiap.lara.model.LaraMessage
import com.fiap.lara.model.NutrizJourney
import com.fiap.lara.model.Sentiment
import com.fiap.lara.ui.screens.ConfirmationScreen
import com.fiap.lara.ui.screens.HomeScreen
import com.fiap.lara.ui.screens.JourneyDetailScreen
import com.fiap.lara.ui.screens.JourneyListScreen
import com.fiap.lara.ui.screens.RegisterNutrizScreen

@Composable
fun LaraNavHost() {
    val navController = rememberNavController()
    val journeys = remember { mutableStateListOf<NutrizJourney>().apply { addAll(MockLaraData.journeys) } }

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(
                journeys = journeys,
                onStart = { navController.navigate(Routes.Alerts.route) },
                onRegister = { navController.navigate(Routes.Report.route) }
            )
        }
        composable(Routes.Alerts.route) {
            JourneyListScreen(
                journeys = journeys,
                onBack = { navController.popBackStack() },
                onJourneyClick = { navController.navigate(Routes.Detail.create(it.id)) },
                onRegister = { navController.navigate(Routes.Report.route) }
            )
        }
        composable(
            route = Routes.Detail.route,
            arguments = listOf(navArgument("journeyId") { type = NavType.IntType })
        ) { entry ->
            val journeyId = entry.arguments?.getInt("journeyId") ?: 0
            JourneyDetailScreen(
                journey = journeys.firstOrNull { it.id == journeyId },
                onBack = { navController.popBackStack() },
                onHumanHandoff = { selected ->
                    val index = journeys.indexOfFirst { it.id == selected.id }
                    if (index >= 0) {
                        journeys[index] = selected.afterSimulatedAction()
                    }
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.Report.route) {
            RegisterNutrizScreen(
                onBack = { navController.popBackStack() },
                onSubmit = { form ->
                    val newJourney = MockLaraData.createJourney(
                        form = form,
                        nextId = (journeys.maxOfOrNull { it.id } ?: 0) + 1
                    )
                    journeys.add(index = 0, element = newJourney)
                    navController.navigate(Routes.Confirmation.create(newJourney.name))
                }
            )
        }
        composable(
            route = Routes.Confirmation.route,
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { entry ->
            ConfirmationScreen(
                name = Uri.decode(entry.arguments?.getString("name").orEmpty()),
                onGoToList = {
                    navController.navigate(Routes.Alerts.route) {
                        popUpTo(Routes.Home.route)
                    }
                }
            )
        }
    }
}

private fun NutrizJourney.afterSimulatedAction(): NutrizJourney {
    return if (readinessScore >= 70) {
        copy(
            currentStage = JourneyStage.Conversion,
            readinessScore = 95,
            sentiment = Sentiment.Secure,
            messages = messages + LaraMessage(
                day = postpartumDay,
                title = "Conversao",
                text = "Convite aceito. A LARA conectou a nutriz ao banco de leite mais proximo de casa."
            ),
            emotionalMemory = emotionalMemory + "Aceitou a simulacao de convite para o BLH."
        )
    } else {
        copy(
            needsHumanSupport = true,
            messages = messages + LaraMessage(
                day = postpartumDay,
                title = "Handoff humano",
                text = "Atendente Lactare acionado com resumo da memoria emocional da nutriz."
            ),
            emotionalMemory = emotionalMemory + "Handoff humano solicitado sem exigir que a mae repita sua historia."
        )
    }
}
