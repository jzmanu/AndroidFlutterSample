package com.manu.androidfluttersample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import io.flutter.embedding.android.FlutterActivityLaunchConfigs

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        val handler = Handler();
        handler.postDelayed({
            // 添加Flutter单个页面
            val intent = AgentActivity
                .withNewEngine()
                ?.backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.opaque)
                ?.build(this@LaunchActivity)

            // 添加Fragment
//            val intent = Intent(this@LaunchActivity,AgentActivity2::class.java)

            startActivity(intent)
            finish()
        },800)
    }


}