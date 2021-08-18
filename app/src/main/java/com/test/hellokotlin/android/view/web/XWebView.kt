package com.test.hellokotlin.android.view.web

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.webkit.*
import androidx.annotation.RequiresApi

class XWebView : WebView {
    var mJavaScriptInterface: BaseJavaScriptInterface? = null
    private var mWebChromeClient: WebChromeClient? = null
    private var mWebViewClient: WebViewClient? = null

     val  defaultHeader:MutableMap<String,String> =HashMap()

    constructor(context: Context) : super(context) {
        initWebSetting()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initWebSetting()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initWebSetting()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        initWebSetting()
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int,
        privateBrowsing: Boolean
    ) : super(context, attrs, defStyleAttr, privateBrowsing) {
        initWebSetting()
    }

    private fun initWebSetting() {
        settings.apply {
            userAgentString = "Tag" + userAgentString
            setRenderPriority(WebSettings.RenderPriority.HIGH)
            cacheMode = WebSettings.LOAD_DEFAULT
            javaScriptEnabled = true
            defaultTextEncodingName = "UTF-8"
            useWideViewPort = true
            loadWithOverviewMode = true
            setAppCacheEnabled(true)

            allowFileAccess = true //允许访问文件
            setSupportZoom(true)

            val dir = context.applicationContext.getDir("database", Context.MODE_PRIVATE).path
            databaseEnabled = true
            databasePath = dir //设置数据库路径
            domStorageEnabled = true
            setGeolocationDatabasePath(dir)

            javaScriptCanOpenWindowsAutomatically = true
            setGeolocationEnabled(true)
            pluginState = WebSettings.PluginState.ON //设置webview支持插件
            setSupportMultipleWindows(true)

            builtInZoomControls = false//隐藏缩放按钮
            setSupportMultipleWindows(true) //开启多窗口支持

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW//在https的页面允许http访问
                CookieManager.getInstance().setAcceptThirdPartyCookies(this@XWebView, true)
            }
            //自动播放音乐
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mediaPlaybackRequiresUserGesture = false;
            }
        }
        requestFocus()
    }

    override fun setWebChromeClient(client: WebChromeClient?) {
        mWebChromeClient =client
        super.setWebChromeClient(client)
    }

    override fun getWebChromeClient(): WebChromeClient? =if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) super.getWebChromeClient() else mWebChromeClient

    override fun setWebViewClient(client: WebViewClient) {
        mWebViewClient =client
        super.setWebViewClient(client)
    }
    override fun getWebViewClient(): WebViewClient =if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) super.getWebViewClient() else mWebViewClient!!

    override fun loadUrl(url: String) {
        refreshHeader()
        super.loadUrl(url,defaultHeader)
    }

    override fun reload() {
        loadUrl(url!!)
    }

    override fun getUrl(): String? {
    return  if(TextUtils.isEmpty(super.getUrl())){
       ""
      }else super.getUrl()

    }
    fun  refreshHeader(){
        defaultHeader["tag"] ="tag"
    }

    @SuppressLint("JavascriptInterface")
    override fun addJavascriptInterface(`object`: Any, name: String) {
        mJavaScriptInterface =`object` as BaseJavaScriptInterface
        super.addJavascriptInterface(`object`, name)
    }

    fun  onActivityResult(requestCode:Int,resultCode:Int,data:Intent):Boolean{
        return  if(mJavaScriptInterface!=null&&mJavaScriptInterface is BaseJavaScriptInterface){
            (mJavaScriptInterface as  BaseJavaScriptInterface).onActivityResult(this,requestCode,resultCode,data)
        }else false

    }



}