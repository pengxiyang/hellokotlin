package com.test.hellokotlin.android.view.web

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import androidx.fragment.app.Fragment
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.lang.ref.WeakReference
import java.net.URLEncoder

class BaseJavaScriptInterface(activity: Activity?, view: XWebView) {
    var activityWeakReference: WeakReference<Activity>? = null
    private var fragmentWeakReference: WeakReference<Fragment>? = null
    private var resumeCallback: String? = null;
    private var webView: XWebView

    init {
        activityWeakReference = WeakReference<Activity>(activity)
        webView = view
    }

    companion object {
        private var tempWebMap: Map<String, String> = HashMap()

        fun encodeJsonStr(values: Map<String, String>): String {
            val value = JSONObject(values)
            var encodeStr = value.toString()
            try {
                encodeStr = URLEncoder.encode(encodeStr, "utf-8")
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }

            return  encodeStr
        }
    }

    fun setFragment(fragment: Fragment) {
        fragmentWeakReference = WeakReference<Fragment>(fragment)
    }

    private fun onResume() {
        if (!TextUtils.isEmpty(resumeCallback)) {
            val callback = resumeCallback
            webView.post {
                webView.loadUrl("javascript:$callback()")
            }
        }


    }


    fun onActivityResult(view: XWebView, requestCode: Int, resultCode: Int, data: Intent): Boolean {
        if (activityWeakReference == null || activityWeakReference!!.get() == null) {
            return false
        }
        return false
    }
}