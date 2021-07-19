package com.test.hellokotlin.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.hellokotlin.MyApplcation
import com.test.hellokotlin.R
import com.test.hellokotlin.adapter.NewFruitAdapter
import com.test.hellokotlin.bean.Fruit
import com.test.hellokotlin.databinding.MaterialDesignActivityBinding
import com.test.hellokotlin.utils.showSnackbarN
import com.test.hellokotlin.utils.showSnackbarNew
import com.test.hellokotlin.utils.showToast
import kotlin.concurrent.thread

class MaterialDesignActivity : AppCompatActivity() {
    private lateinit var binding: MaterialDesignActivityBinding
    private val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple), Fruit(
            "Banana",
            R.drawable.banana
        ), Fruit("Orange", R.drawable.orange), Fruit(
            "Watermelon",
            R.drawable.watermelon
        ), Fruit("Pear", R.drawable.pear), Fruit(
            "Grape",
            R.drawable.grape
        ), Fruit("Pineapple", R.drawable.pineapple), Fruit(
            "Strawberry",
            R.drawable.strawberry
        ), Fruit("Cherry", R.drawable.cherry), Fruit(
            "Mango",
            R.drawable.mango
        )
    )

    private val fruitList = ArrayList<Fruit>()

    override fun onDestroy() {
        super.onDestroy()
        MyApplcation.destoryActivity("1")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MaterialDesignActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MyApplcation.addDestoryActivity(this,"1")
        initFruits()
        binding.apply {
            setSupportActionBar(toolbar)
            navView.setCheckedItem(R.id.navCall)
            navView.setNavigationItemSelectedListener {
                Toast.makeText(this@MaterialDesignActivity, it.title, Toast.LENGTH_SHORT).show()
                drawLayout.closeDrawers()
                true
            }

            fab.setOnClickListener { view ->
//                Snackbar.make(view, "Data fab", Snackbar.LENGTH_SHORT)
//                    .setAction("Undo") {
//                        "Data restored".showToast(this@MaterialDesignActivity)
//                    }
//                    .show()

                view.showSnackbarN("Data fab","Undo"){
                    "Data restored".showToast(this@MaterialDesignActivity)

                }

            }
        }
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        val layoutManager = GridLayoutManager(this, 2)
        val adapter = NewFruitAdapter(this, fruitList)
        binding.rvFruits.layoutManager = layoutManager
        binding.rvFruits.adapter = adapter
        binding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        binding.swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }

    private fun  refreshFruits(adapter: NewFruitAdapter){
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                binding.swipeRefresh.isRefreshing =false
            }
        }

    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> {
                Toast.makeText(this, "Backup", Toast.LENGTH_SHORT).show()
            }
            R.id.delete -> {
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                binding.drawLayout.openDrawer(GravityCompat.START)
            }
        }
        return true
    }

}