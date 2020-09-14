package com.manu.androidfluttersample

import android.app.Activity
import android.util.Log
import com.manu.androidfluttersample.MainActivity.Companion.startMainActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/**
 * @desc FlutterActivity
 * @author jzman
 */
val tag = AgentActivity::class.java.simpleName;

class AgentActivity : FlutterActivity() {
    private val channel = "com.manu.startMainActivity"
    private var platform: MethodChannel? = null;

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.d(tag,"configureFlutterEngine")
        platform = MethodChannel(flutterEngine.dartExecutor, channel)
        // 设置方法处理器
        platform!!.setMethodCallHandler(StartMethodCallHandler(this@AgentActivity))
    }

    companion object{
        /**
         * 重新创建NewEngineIntentBuilder才能保证生效
         */
        fun withNewEngine(): MNewEngineIntentBuilder? {
            return MNewEngineIntentBuilder(AgentActivity::class.java)
        }
    }

    /**
     * 自定义NewEngineIntentBuilder
     */
    class MNewEngineIntentBuilder(activityClass: Class<out FlutterActivity?>?) :
        NewEngineIntentBuilder(activityClass!!)

    /**
     * 实现MethodCallHandler
     */
    class StartMethodCallHandler(activity:Activity) : MethodChannel.MethodCallHandler{
        private val context:Activity = activity
        override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
            if ("startMainActivity" == call.method) {
                Log.i(tag,"arguments:"+call.arguments)
                startMainActivity(context)
                // 向Flutter回调执行结果
                result.success("success")
            } else {
                result.notImplemented()
            }
        }
    }
}