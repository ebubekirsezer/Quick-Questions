package com.ess.quickquestions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.android.synthetic.main.activity_main.*

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        remoteConfig = Firebase.remoteConfig

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        fetchUpdatesAndControl()
        val currentUser = auth.currentUser
        val intent = Intent(applicationContext, MainActivity::class.java)

        if (currentUser != null) intent.putExtra("isAuth", true)

        startActivity(intent)
        finish()
    }

    fun fetchUpdatesAndControl() {

        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        val versionName = packageInfo.versionName
        val version = remoteConfig.getString(FORCE_UPDATE_CURRENT_VERSION)
        val isRequired = remoteConfig.getBoolean(FORCE_UPDATE_REQUIRED)
        val appUrl = remoteConfig.getString(FORCE_UPDATE_URL)
        println(version)
        println(isRequired)
        println("Url: " + appUrl)
    }

    companion object {
        private const val FORCE_UPDATE_URL = "force_update_store_url"
        private const val FORCE_UPDATE_CURRENT_VERSION = "force_update_current_version"
        private const val FORCE_UPDATE_REQUIRED = "force_update_required"
    }
}