package com.ess.quickquestions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.ess.quickquestions.utils.FirebaseNotificationService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isAuth = intent.getBooleanExtra("isAuth", false)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(R.color.textColorPrimary))

        val navHostFragment = sign_fragment as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        navGraph = graphInflater.inflate(R.navigation.main_navigation)
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBar(navController, appBarConfiguration)

        parseIntentExtras(intent.extras)

        /*  FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
              if (!task.isSuccessful) {
                  println("Fetching FCM registration token failed " + task.exception)
                  return@OnCompleteListener
              }

              // Get new FCM registration token
              val token = task.result

              // Log and toast
              val msg = "FCM registration Token:"+ token
              Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
          })*/


        val destination = if (isAuth) R.id.homeFragment else R.id.signFragment
        navGraph.startDestination = destination
        navController.graph = navGraph

    }

    private fun parseIntentExtras(extras: Bundle?) {
        extras?.let {
            val content = it.getString(FirebaseNotificationService.PARAM_CUSTOM_MESSAGE)
            content?.let { message ->
            }
        }
    }

    private fun setupActionBar(
        navController: NavController,
        appBarConfiguration: AppBarConfiguration
    ) {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.sign_fragment).navigateUp(appBarConfiguration)
    }
}