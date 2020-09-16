package com.manu.androidfluttersample.method

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.manu.androidfluttersample.R
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @desc MainActivity
 * @author jzman
 */
class MethodChannelActivity : FlutterActivity() {
    private val tag = MethodChannelActivity::class.java.simpleName;
    private val channel = "com.manu.startMainActivity"
    private var methodChannel: MethodChannel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetDart.setOnClickListener {
            getDartMethod()
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.i(tag,"configureFlutterEngine")
        methodChannel = MethodChannel(flutterEngine.dartExecutor,channel)
    }

    private fun getDartMethod(){
        methodChannel?.invokeMethod("getName",null, object :MethodChannel.Result{
            override fun success(result: Any?) {
                Log.i(tag,"success: "+result.toString())
                Toast.makeText(this@MethodChannelActivity,result.toString(),Toast.LENGTH_LONG).show()
            }

            override fun error(errorCode: String,errorMessage: String?,errorDetails: Any?) {
                Log.i(tag,"error")
            }

            override fun notImplemented() {
                Log.i(tag,"notImplemented")
            }
        })
    }

    companion object{
        fun startMainActivity(context: Context) {
            val intent = Intent(context, MethodChannelActivity::class.java)
            context.startActivity(intent)
        }
    }
}