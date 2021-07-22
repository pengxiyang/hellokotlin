package com.test.hellokotlin.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.roughike.bottombar.OnTabSelectListener
import com.test.hellokotlin.R
import com.test.hellokotlin.databinding.ActivityNewMainBinding
import com.test.hellokotlin.android.fragment.HomeFragment
import com.test.hellokotlin.android.fragment.MainFragment
import com.test.hellokotlin.android.fragment.MineFragment

class NewMainActivity : AppCompatActivity(), OnTabSelectListener {
    lateinit var binding: ActivityNewMainBinding
    private var list = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
    }

    private fun initData() {
        list.clear()
        binding.bottombar.setOnTabSelectListener(this)
        var fragments = supportFragmentManager.fragments
        if (fragments == null || fragments.isEmpty()) {
            list.add(HomeFragment())
            list.add(MainFragment())
            list.add(MineFragment())

        } else {
            for (i in fragments.indices) {
                if (fragments[i] is HomeFragment) {
                    list.add(fragments[i])
                    break
                }
                if (fragments[i] is MainFragment) {
                    list.add(fragments[i])
                    break

                }
                if (fragments[i] is MineFragment) {
                    list.add(fragments[i])
                    break
                }

            }
        }
        val adapter: FragmentPagerAdapter = object : FragmentPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            override fun getCount(): Int = list.size
            override fun getItem(position: Int): Fragment = list.get(position)
        }
        binding.noscrollVp.adapter = adapter

        binding.noscrollVp.offscreenPageLimit = list.size

    }


    override fun onTabSelected(tabId: Int) {
       when(tabId){
           R.id.tab_home ->{
               if(binding.noscrollVp.currentItem!=0){
                   binding.noscrollVp.setCurrentItem(0,false)
               }
           }
           R.id.tab_main->{
               if(binding.noscrollVp.currentItem!=1){
                   binding.noscrollVp.setCurrentItem(1,false)
               }
           }
           R.id.tab_mine ->{
               if(binding.noscrollVp.currentItem!=2){
                   binding.noscrollVp.setCurrentItem(2,false)
               }

           }

       }

    }

}