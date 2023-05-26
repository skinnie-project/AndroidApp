package com.company.skinnie.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.company.skinnie.R
import com.company.skinnie.databinding.ActivitySplashScreenBinding
import com.company.skinnie.ui.auth.AuthActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    val SPLASH_SCREEN = 4000

    private lateinit var topAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        binding.ivLogo.animation = topAnimation

        Handler().postDelayed({
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())
    }
}