package com.manu.androidfluttersample

import android.content.Intent
import android.os.Bundle
import android.text.format.Formatter
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.manu.androidfluttersample.basic.AgentBasicActivity
import com.manu.androidfluttersample.fragment.AgentFragmentActivity
import com.manu.androidfluttersample.method.AgentActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import kotlinx.android.synthetic.main.activity_launch.*
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

/**
 * Android与Flutter通信机制
 */
class LaunchActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        btnSingleFlutterPage.setOnClickListener(this)
        btnFlutterFragment.setOnClickListener(this)
        btnBasicMessage.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnSingleFlutterPage -> toSingleFlutterPage()
            R.id.btnFlutterFragment -> toFlutterFragment()
            R.id.btnBasicMessage -> toBasicMessageChannelPage()
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
     * FlutterFragment
     */
    private fun toFlutterFragment(){
        startActivity(Intent(this@LaunchActivity,
            AgentFragmentActivity::class.java))
    }

    /**
     * BasicMessageChannelPage
     */
    private fun toBasicMessageChannelPage(){
        val intent = AgentBasicActivity
            .withNewEngine()
            ?.initialRoute("/BasicMessageChannelPage")
            ?.backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
            ?.build(this@LaunchActivity)
        startActivity(intent)
    }



}
