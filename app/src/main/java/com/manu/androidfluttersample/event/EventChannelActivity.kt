package com.manu.androidfluttersample.event

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.manu.androidfluttersample.R
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel

/**
 * @desc BasicMessageActivity
 * @author jzman
 */
class EventChannelActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_event)
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel.MethodCallHandler { call, result ->
            if ("startEventChannelActivity" == call.method){
                startEventChannelActivity(this)
            }
        }

        EventChannel(flutterEngine.dartExecutor,"com.manu.event").setStreamHandler(object:EventChannel.StreamHandler{
            override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
            }

            override fun onCancel(arguments: Any?) {
            }
        })
    }

    companion object{
        fun startEventChannelActivity(context: Context){
            context.startActivity(Intent(context,EventChannelActivity::class.java))
        }
    }
}