package com.manu.androidfluttersample.basic

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.manu.androidfluttersample.R
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryCodec
import io.flutter.plugin.common.MethodChannel
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

/**
 * @desc BasicMessageActivity
 * @author jzman
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AgentBasicActivity : FlutterActivity() {
    private val tag = AgentBasicActivity::class.java.simpleName
    private val channel = "com.manu.startBasicMessageChannelActivity"
    private var imageByteArray:ByteArray ?= null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.i(tag, "configureFlutterEngine")

        BasicMessageChannel<ByteBuffer>(
            flutterEngine.dartExecutor, "com.manu.image", BinaryCodec.INSTANCE
        ).setMessageHandler { message, reply ->
            Log.i(tag, "configureFlutterEngine > message:$message")
            val byteBuffer = message as ByteBuffer
            imageByteArray = ByteArray(byteBuffer.capacity())
            byteBuffer.get(imageByteArray)
//            reply.reply("reply from android")
        }

        MethodChannel(flutterEngine.dartExecutor, channel).setMethodCallHandler { call, result ->
            Log.i(tag, "configureFlutterEngine > method:${call.method}")
            if ("startBasicMessageChannelActivity" == call.method) {
                BasicMessageChannelActivity.startBasicMessageChannelActivity(this,imageByteArray)
            }
        }
    }

    /**
     * 自定义NewEngineIntentBuilder
     */
    class MNewEngineIntentBuilder(activityClass: Class<out FlutterActivity?>?) :
        NewEngineIntentBuilder(activityClass!!)

    companion object {

        /**
         * 重新创建NewEngineIntentBuilder才能保证生效
         */
        fun withNewEngine(): MNewEngineIntentBuilder? {
            return MNewEngineIntentBuilder(AgentBasicActivity::class.java)
        }
    }

    fun getImageByteData(): ByteArray{
        /// 将图片资源转换成字节
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.miao)
        val byteArrayOutputStream = ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        bitmap.recycle()
        val bytes = byteArrayOutputStream.toByteArray()
        byteArrayOutputStream.close()
        return  bytes
    }

}