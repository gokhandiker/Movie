package com.melisa.loodos.ui.splash


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.melisa.loodos.R
import com.melisa.loodos.ui.choose.ChooseActivity
import com.melisa.loodos.ui.main.MainActivity
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


        txt_loodos.setTextColor(Color.parseColor(firebaseRemoteConfig.getString("text_color")))
        txt_loodos.textSize = firebaseRemoteConfig.getValue("text_size").asDouble().toFloat()
        txt_loodos.text = firebaseRemoteConfig.getString("text_str")
    }

    override fun onStart() {
        super.onStart()

        firebaseRemoteConfig.fetch(0)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Activated", Toast.LENGTH_SHORT).show()

                    Handler().postDelayed({
                        /* Create an Intent that will start the Menu-Activity. */
                        val mainIntent = Intent(this, ChooseActivity::class.java)
                        startActivity(mainIntent)
                        finish()
                    }, 3000)

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
