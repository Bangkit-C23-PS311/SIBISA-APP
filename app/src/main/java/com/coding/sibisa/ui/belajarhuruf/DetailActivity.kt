package com.coding.sibisa.ui.belajarhuruf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.bumptech.glide.Glide
import com.coding.sibisa.data.response.DataItemItem
import com.coding.sibisa.databinding.ActivityDetailBinding
import com.coding.sibisa.ui.CameraActivity


class DetailActivity : AppCompatActivity() {
    companion object{
        const val KONCIAN = "INIKONCIANBROOOK"
    }

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val result = intent.getParcelableExtra<DataItemItem>(KONCIAN)

        binding.apply {
            descDetail.text = result?.description
            titleDetail.text = result?.title
        }
        Glide.with(this)
            .load(result?.imageUrl)
            .fitCenter()
            .into(binding.ivDetail)

        binding.btnDetail.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java).apply {
                putExtra(CameraActivity.KONCIAN, result as Parcelable)
            }
            startActivity(intent)
        }

    }
}