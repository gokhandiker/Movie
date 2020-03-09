package com.melisa.loodos.ui.splash


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.melisa.loodos.BuildConfig
import com.melisa.loodos.R
import com.melisa.loodos.ui.choose.ChooseActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long=3000 // 3 sec
    private val APP_TEXT_COLOR_KEY = "text_color"
    private val APP_TEXT_SIZE_KEY = "text_size"
    private val APP_TEXT_STRING_KEY= "text_str"

    private lateinit var firebaseRemoteConfig: FirebaseRemoteConfig


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()


        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setDeveloperModeEnabled(BuildConfig.DEBUG)
            .build()


        firebaseRemoteConfig?.setConfigSettings(configSettings)

        firebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults)
        //setting the default values for the UI
        firebaseRemoteConfig?.setDefaults(R.xml.remote_config_defaults)

        //getting the Remote values from Remote Config
        getRemoteConfigValues()


    }

    private fun getRemoteConfigValues() {
        txt_loodos?.setTextColor(Color.parseColor(firebaseRemoteConfig.getString(APP_TEXT_COLOR_KEY)))
        txt_loodos?.textSize = firebaseRemoteConfig.getValue(APP_TEXT_SIZE_KEY).asDouble().toFloat()
        txt_loodos?.text = firebaseRemoteConfig.getString(APP_TEXT_STRING_KEY)

        var cacheExpiration: Long = 10

        firebaseRemoteConfig?.fetch(cacheExpiration)?.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Fetch Succeeded", Toast.LENGTH_SHORT).show()
                firebaseRemoteConfig?.activateFetched()
            } else {
                Toast.makeText(applicationContext, "Fetch Failed", Toast.LENGTH_SHORT).show()
            }
            //changing the textview and backgorund color
            setRemoteConfigValues()
        }

    }

    private fun setRemoteConfigValues() {
        val remoteValueTextColor = firebaseRemoteConfig?.getString(APP_TEXT_COLOR_KEY)
        val remoteValueTextSize = firebaseRemoteConfig?.getString(APP_TEXT_SIZE_KEY)
        val remoteValueTextString = firebaseRemoteConfig?.getString(APP_TEXT_STRING_KEY)

        if (!remoteValueTextString.isNullOrEmpty()) {
            txt_loodos?.text = remoteValueTextString
            txt_loodos?.textSize = remoteValueTextSize.toFloat()
            txt_loodos?.setTextColor(Color.parseColor(remoteValueTextColor))
        } else
            txt_loodos?.text = "Failed to load values"

        Handler().postDelayed({
            val mainIntent = Intent(this, ChooseActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_TIME_OUT)
    }

}
