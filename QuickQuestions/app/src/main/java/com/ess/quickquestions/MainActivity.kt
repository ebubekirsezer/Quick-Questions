package com.ess.quickquestions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isAuth = intent.getBooleanExtra("isAuth",false)
        println(isAuth)

        val navHostFragment = sign_fragment as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        navGraph = graphInflater.inflate(R.navigation.main_navigation)
        navController = navHostFragment.navController

        val destination = if(isAuth) R.id.homeFragment else R.id.signFragment
        navGraph.startDestination = destination
        navController.graph = navGraph

    }
}