package com.test.hellokotlin.activity.media

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.R
import com.test.hellokotlin.databinding.ActivityMediaPlayerBinding

class MediaPlayerActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMediaPlayerBinding
    private val mMediaPlayer = MediaPlayer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMediaPlayer()
        initView()
    }

    private fun initView() {
        binding.play.setOnClickListener(this)
        binding.pause.setOnClickListener(this)
        binding.stop.setOnClickListener(this)

    }

    private fun initMediaPlayer() {
        val assetManager = assets
        val fd = assetManager.openFd("music.mp3")
        mMediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)//设置播放音频文件的位置
        mMediaPlayer.prepare() //开始播放前调用，以完成准备工作
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.play -> {
                if(!mMediaPlayer.isPlaying){
                    mMediaPlayer.start() //开始播放
                }
            }
            R.id.pause -> {
                if(mMediaPlayer.isPlaying){
                    mMediaPlayer.pause() //暂停播放
                }
            }
            R.id.stop -> {
                mMediaPlayer.reset() //停止播放
                initMediaPlayer()
            }
        }

    }

    /**
     * 测试mediaPalyer的各种方法
     */
    private fun  testMediaFun(){
        mMediaPlayer.seekTo(10) //从指定位置开始播放音频
        mMediaPlayer.isPlaying //判断当前MediaPlayer是否正在播放
        mMediaPlayer.duration //获取载入的音频文件的时长

    }


    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer.stop()//停止播放视频。调用后的MediaPlayer对象无法再播放音频
        mMediaPlayer.release() //释放与MediaPlayer对象相关的资源
    }
}