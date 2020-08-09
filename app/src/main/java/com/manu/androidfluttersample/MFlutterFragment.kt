package com.manu.androidfluttersample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class MFlutterFragment : FlutterFragment() {
    private val channel = "com.manu.startMainActivity"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.d(tag,"configureFlutterEngine")
        MethodChannel(flutterEngine.dartExecutor,channel)
            .setMethodCallHandler{methodCall: MethodCall, result: MethodChannel.Result ->
                if ("startMainActivity" == methodCall.method) {
                    MainActivity.startMainActivity(context)
                    result.success("success")
                } else {
                    result.notImplemented()
                }
            }
    }

    companion object{
        fun withNewEngine(): NewEngineFragmentBuilder? {
            return MNewEngineIntentBuilder(MFlutterFragment::class.java)
        }
    }

    class MNewEngineIntentBuilder(activityClass: Class<out FlutterFragment?>?) :
        NewEngineFragmentBuilder(activityClass!!)

}