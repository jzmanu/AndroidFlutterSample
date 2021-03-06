package com.manu.androidfluttersample.fragment

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.manu.androidfluttersample.R

/**
 * @desc 添加FlutterFragment到Activity
 * @author jzman
 */
class AgentFragmentActivity : FragmentActivity() {
    private val flutterFragmentTag = "flutter_fragment_tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent2)
        val fragmentManager = supportFragmentManager
        var flutterFragment = fragmentManager.findFragmentByTag(flutterFragmentTag)
        if (flutterFragment == null){
            flutterFragment = MFlutterFragment
                .withNewEngine()
                ?.initialRoute("/EventChannelPage")
                ?.build()
            if (flutterFragment != null) {
                fragmentManager.beginTransaction()
                    .add(R.id.ff_container,flutterFragment,flutterFragmentTag)
                    .commit()
            }
        }
    }
}