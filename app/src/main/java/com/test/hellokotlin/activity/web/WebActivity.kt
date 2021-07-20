package com.test.hellokotlin.activity.web

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.databinding.ActivityWebBinding

class WebActivity  :AppCompatActivity(){
    private lateinit var binding:ActivityWebBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            wv.settings.javaScriptEnabled =true
            wv.webViewClient= WebViewClient()
            wv.loadUrl("https://www.baidu.com")

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home-> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}