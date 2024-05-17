package com.example.protapptest.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

fun NavGraphBuilder.detailsNavGraph(
    navHostController: NavHostController,
    startDestination: String
) {
    navigation(
        route = Graph.DETAILS,
        startDestination = startDestination
    ) {


    }
}