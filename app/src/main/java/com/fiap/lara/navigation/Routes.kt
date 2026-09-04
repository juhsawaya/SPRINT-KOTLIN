package com.fiap.lara.navigation

import android.net.Uri

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Alerts : Routes("journeys")
    data object Detail : Routes("journey/{journeyId}") {
        fun create(journeyId: Int) = "journey/$journeyId"
    }
    data object Report : Routes("register")
    data object Confirmation : Routes("confirmation/{name}") {
        fun create(name: String) = "confirmation/${Uri.encode(name)}"
    }
}
