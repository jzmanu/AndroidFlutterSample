package com.manu.androidfluttersample.basic

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import com.manu.androidfluttersample.R
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryCodec
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

/**
 * @desc BasicMessageActivity
 * @author jzman
 */
class BasicMessageActivity : FlutterActivity() {
    private val tag = BasicMessageActivity::class.java.simpleName
    private val channel = "com.manu.image"
    private var baseChannel: BasicMessageChannel<ByteBuffer>? = null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.i(tag, "configureFlutterEngine:" + Thread.currentThread())
        baseChannel = BasicMessageChannel<ByteBuffer>(
            flutterEngine.dartExecutor,
            channel,
            BinaryCodec.INSTANCE
        )
        baseChannel!!.setMessageHandler { message, reply ->
            Log.i(tag, "configureFlutterEngine > message:$message")
            /// 将图片资源转换成字节
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.miao)
            val byteArrayOutputStream = ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            bitmap.recycle()
            val bytes = byteArrayOutputStream.toByteArray()
            byteArrayOutputStream.close()
            reply.reply(ByteBuffer.wrap(bytes))
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
            return MNewEngineIntentBuilder(BasicMessageActivity::class.java)
        }
    }


}