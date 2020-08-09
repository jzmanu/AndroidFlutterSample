package com.manu.androidfluttersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.manu.androidfluttersample.AgentActivity.Companion.withNewEngine
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine

class AgentActivity2 : FragmentActivity() {
    private val flutterFragmentTag = "flutter_fragment_tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent2)
        val fragmentManager = supportFragmentManager
        var flutterFragment = fragmentManager.findFragmentByTag(flutterFragmentTag)
        if (flutterFragment == null){
//            flutterFragment = FlutterFragment.createDefault()
            flutterFragment = MFlutterFragment
                .withNewEngine()
                ?.build()
            if (flutterFragment != null) {
                fragmentManager.beginTransaction()
                    .add(R.id.ff_container,flutterFragment,flutterFragmentTag)
                    .commit()
            }
        }
    }
}