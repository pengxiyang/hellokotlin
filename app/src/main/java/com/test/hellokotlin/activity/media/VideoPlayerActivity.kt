package com.test.hellokotlin.activity.media

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.R
import com.test.hellokotlin.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityVideoPlayerBinding.inflate(layoutInflater)
    }
    private val videoView get() = binding.videoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initVideo()
    }

    private fun initVideo() {
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView.setVideoURI(uri)//设置播放视频文件的uri
        binding.play.setOnClickListener(this)
        binding.pause.setOnClickListener(this)
        binding.stop.setOnClickListener(this)


    }

    private fun tesrVideoFun() {
        videoView.setVideoPath("'") //设置要播放视频文件的地址
        videoView.seekTo(10)//从指定位置开始播放
        videoView.isPlaying //判断视频是否正在播放
        videoView.duration //获取载入视频文件的时长

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.play -> {
                Toast.makeText(this,"123",Toast.LENGTH_SHORT).show()
                if(!videoView.isPlaying){
                    videoView.start()//开始或继续播放视频
                }
            }
            R.id.pause -> {
                if(videoView.isPlaying){
                    videoView.pause()//暂停播放视频
                }
            }
            R.id.stop -> {
            if(videoView.isPlaying){
                videoView.resume() //重新播放
            }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend() //释放videoView所占用的资源
    }

}