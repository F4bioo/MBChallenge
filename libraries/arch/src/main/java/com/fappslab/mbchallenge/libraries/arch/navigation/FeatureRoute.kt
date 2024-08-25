package com.fappslab.mbchallenge.libraries.arch.navigation

import androidx.navigation.NavGraphBuilder

interface FeatureRoute {
    fun register(navGraphBuilder: NavGraphBuilder)
}
