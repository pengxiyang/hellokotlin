package com.test.hellokotlin.activity.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.hellokotlin.adapter.MsgAdapter
import com.test.hellokotlin.bean.Msg
import com.test.hellokotlin.databinding.ActivityChatBinding

/**
 *  created by pxy on 2021/4/26
 *
 */
class ChatActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var bind: ActivityChatBinding
    private val msgList = ArrayList<Msg>()
//    private var adapter: MsgAdapter? = null
    private lateinit var adapter:MsgAdapter //延迟初始化

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityChatBinding.inflate(layoutInflater)
        setContentView(bind.root)
        initMsg()
        val layoutManager =LinearLayoutManager(this)
        bind.rvChat.layoutManager =layoutManager
        if(!::adapter.isInitialized) {
            adapter = MsgAdapter(msgList)
        }
        bind.rvChat.adapter =adapter
        bind.send.setOnClickListener(this)

    }

    fun  initMsg(){
        val msg1 =Msg("Hello,boy.",Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 =Msg("Hello.Who is that?",Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 =Msg("This is Jack.Nice talking to you",Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }

    override fun onClick(v: View?) {
        when(v){
            bind.send->{
                val  content =bind.inputText.text.toString()
                val  msg =Msg(content,Msg.TYPE_SENT)
                msgList.add(msg)
                adapter?.notifyItemInserted(msgList.size-1) //当有新消息时，刷新recyclerview中的显示
                bind.rvChat.smoothScrollToPosition(msgList.size-1) //定位到最后一行
              //  bind.rvChat.scrollToPosition(msgList.size-1)

          //      bind.inputText.setText("")//清空输入框
                bind.inputText.text.clear()



            }
        }

    }

}