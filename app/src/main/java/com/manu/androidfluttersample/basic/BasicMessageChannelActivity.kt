package com.manu.androidfluttersample.basic

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import com.manu.androidfluttersample.R
import io.flutter.embedding.android.FlutterActivity
import kotlinx.android.synthetic.main.activity_basic_message_channel.*

/**
 * @desc BasicMessageActivity
 * @author jzman
 */
class BasicMessageChannelActivity : FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_message_channel)

        val imageByteArray = intent.getByteArrayExtra("key_image")
        val bitmap = BitmapFactory.decodeByteArray(imageByteArray,0,imageByteArray.size)
        Log.i("basic","---"+intent.extras)
        imageView.setImageBitmap(bitmap)
    }

    companion object{
        fun startBasicMessageChannelActivity(context: Context, byteArray: ByteArray?){
            val intent = Intent(context,BasicMessageChannelActivity::class.java)
            intent.putExtra("key_image",byteArray)
            intent.putExtra("start","hhh")
            intent.putExtra("end","hhh")
            intent.putExtra("start2","hhh")
            intent.putExtra("start3","hhh")
            context.startActivity(intent)
        }
    }
}