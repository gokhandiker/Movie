package com.melisa.loodos.ui.splash


import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.melisa.loodos.R
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long=3000 // 3 sec

    private lateinit var firebaseRemoteConfig: FirebaseRemoteConfig


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        firebaseRemoteConfig.setConfigSettings(
            FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(true)
                .build()
        )

        firebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);


        textView.setTextColor(Color.parseColor(firebaseRemoteConfig.getString("text_color")))
        textView.textSize = firebaseRemoteConfig.getValue("text_size").asDouble().toFloat()
        textView.text = firebaseRemoteConfig.getString("text_str")
    }

    override fun onStart() {
        super.onStart()


        /*
                This will initiate fetching of parameters. We have set the expiry time as 0
                which will ensure we get fresh parameters every time
                */

        /*
                This will initiate fetching of parameters. We have set the expiry time as 0
                which will ensure we get fresh parameters every time
                */firebaseRemoteConfig.fetch(0)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Activated", Toast.LENGTH_SHORT).show()
                    firebaseRemoteConfig.activateFetched()
                } else {
                    Toast.makeText(this, "Not Activated", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }


    override fun onPause() {
        super.onPause()
    }
}
