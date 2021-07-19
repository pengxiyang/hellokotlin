package com.test.hellokotlin.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.test.hellokotlin.databinding.ActivityFruitMaterialBinding

class MaterialDesign2Activity :AppCompatActivity(){
    private lateinit var  binding:ActivityFruitMaterialBinding

    companion object{
        const val  FRUIT_NAME ="fruit_name"
        const val  FRUIT_IMAGE_ID ="fruit_image_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFruitMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun  initView(){
        val fruitName = intent.getStringExtra(FRUIT_NAME)?:""
        val  fruitImageId =intent.getIntExtra(FRUIT_IMAGE_ID,0)
        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            collapsingToolbar.title =fruitName
            Glide.with(this@MaterialDesign2Activity).load(fruitImageId).into(fruitImageIv)
            fruitContentText.text=generateFruitContent(fruitName)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun  generateFruitContent(fruitName:String) =fruitName.repeat(500)

}