package com.manu.androidfluttersample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.manu.androidfluttersample.MFlutterFragment.Companion.withNewEngine
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import kotlinx.android.synthetic.main.activity_launch.*

/**
 * Android与Flutter通信机制
 */
class LaunchActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        btnSingleFlutterPage.setOnClickListener(this)
        btnFlutterFragment.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnSingleFlutterPage -> toSingleFlutterPage()
            R.id.btnFlutterFragment -> toFlutterFragment()
        }
    }

    /**
     * 单个Flutter页面
     */
    private fun toSingleFlutterPage(){
        val intent = AgentActivity
            .withNewEngine()
            ?.backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
            ?.build(this@LaunchActivity)
        startActivity(intent)
    }

    /**
     * 单个Flutter页面
     */
    private fun toFlutterFragment(){
        startActivity(Intent(this@LaunchActivity,AgentFragmentActivity::class.java))
    }
}
