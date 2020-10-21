package com.ess.quickquestions

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
    private var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        remoteConfig = Firebase.remoteConfig

        currentUser = auth.currentUser

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    override fun onResume() {
        fetchUpdatesAndControl()
        super.onResume()
    }

    fun fetchUpdatesAndControl() {

        remoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val updated = task.result
            } else
                Toast.makeText(this, "Fetch Failed", Toast.LENGTH_SHORT).show()

            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            val versionName = packageInfo.versionName
            val version = remoteConfig.getString(FORCE_UPDATE_CURRENT_VERSION)
            val isRequired = remoteConfig.getBoolean(FORCE_UPDATE_REQUIRED)
            val appUrl = remoteConfig.getString(FORCE_UPDATE_URL)
            var versionUri = Uri.parse(appUrl)
            if (isRequired) {
                if (!version.equals(versionName)) {
                    var alert = AlertDialog.Builder(this)
                        .setTitle("Update Required")
                        .setMessage("To have better app perfomace you need to update")
                        .setPositiveButton(
                            "Update",
                            DialogInterface.OnClickListener { dialog, which ->
                                val intent = Intent(Intent.ACTION_VIEW, versionUri)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            })
                        .setNegativeButton(
                            "Later",
                            DialogInterface.OnClickListener { dialog, which ->
                                goHome()
                            })
                        .create()
                    alert.show()
                } else
                    goHome()
            } else
                goHome()
        }
    }

    fun goHome() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        if (currentUser != null) intent.putExtra("isAuth", true)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val FORCE_UPDATE_URL = "force_update_url"
        private const val FORCE_UPDATE_CURRENT_VERSION = "force_update_current_version"
        private const val FORCE_UPDATE_REQUIRED = "force_update_required"
    }
}