package com.manu.androidfluttersample.fragment

import android.util.Log
import com.manu.androidfluttersample.event.EventChannelActivity
import com.manu.androidfluttersample.method.MethodChannelActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/**
 * @desc FlutterFragment
 * @author jzman
 */
class MFlutterFragment : FlutterFragment() {
    private val channel = "com.manu.startEventChannelActivity"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.d(tag,"configureFlutterEngine")
        MethodChannel(flutterEngine.dartExecutor,channel)
            .setMethodCallHandler{methodCall: MethodCall, result: MethodChannel.Result ->
                if ("startEventChannelActivity" == methodCall.method) {
                    Log.i(tag,"arguments:"+methodCall.arguments)
                    EventChannelActivity.startEventChannelActivity(context)
                    result.success("success")
                } else {
                    result.notImplemented()
                }
            }
    }

    companion object{
        fun withNewEngine(): NewEngineFragmentBuilder? {
            return MNewEngineIntentBuilder(
                MFlutterFragment::class.java
            )
        }
    }

    class MNewEngineIntentBuilder(activityClass: Class<out FlutterFragment?>?) :
        NewEngineFragmentBuilder(activityClass!!)

}