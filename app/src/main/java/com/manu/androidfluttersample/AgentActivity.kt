package com.manu.androidfluttersample

import android.os.Bundle
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class AgentActivity : FlutterActivity() {
    private val tag = AgentActivity::class.java.simpleName;
    private val channel = "com.manu.startMainActivity"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.d(tag,"configureFlutterEngine")

        MethodChannel(flutterEngine.dartExecutor, channel)
            .setMethodCallHandler { methodCall: MethodCall, result: MethodChannel.Result ->
                if ("startMainActivity" == methodCall.method) {
                    MainActivity.startMainActivity(this)
                    result.success("success")
                } else {
                    result.notImplemented()
                }
            }
    }

    companion object{
        fun withNewEngine(): MNewEngineIntentBuilder? {
            return MNewEngineIntentBuilder(AgentActivity::class.java)
        }
    }

    class MNewEngineIntentBuilder(activityClass: Class<out FlutterActivity?>?) :
        NewEngineIntentBuilder(activityClass!!)
}