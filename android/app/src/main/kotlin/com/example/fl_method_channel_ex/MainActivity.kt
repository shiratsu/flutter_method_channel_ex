package com.example.fl_method_channel_ex

import android.util.Log

import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.embedding.engine.FlutterEngine


class MainActivity: FlutterActivity() {

    companion object {
        private const val CHANNEL = "com.example.fl_method_channel_ex/sample"
        private const val METHOD_GET_LIST = "getList"
    }

    private lateinit var channel: MethodChannel

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
        channel.setMethodCallHandler { methodCall: MethodCall, result: MethodChannel.Result ->
            if (methodCall.method == METHOD_GET_LIST) {
                val name = methodCall.argument<String>("name").toString()
                val age = methodCall.argument<Int>("age")
                Log.d("Android", "name = ${name}, age = $age")

                val list = listOf("data0", "data1", "data2")
                result.success(list)
            }
            else
                result.notImplemented()
        }
    }
}
