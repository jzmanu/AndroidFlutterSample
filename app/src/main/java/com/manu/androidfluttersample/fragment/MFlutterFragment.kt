package com.manu.androidfluttersample.fragment

import android.util.Log
import com.manu.androidfluttersample.method.MethodChannelActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/**
 * @desc FlutterFragment
 * @author jzman
 */
class MFlutterFragment : FlutterFragment() {

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.d(tag,"configureFlutterEngine")
        EventChannel(flutterEngine.dartExecutor,"com.manu.event").setStreamHandler(object:
            EventChannel.StreamHandler{
            override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                Log.i(tag,"configureFlutterEngine > onListen")
                // EventSink发送事件通知
                events?.success("event message")
            }

            override fun onCancel(arguments: Any?) {
                Log.i(tag,"configureFlutterEngine > onCancel")
            }
        })
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