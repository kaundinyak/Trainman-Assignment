package com.kaundinyakasibhatla.trainman_assignment.ui.detail.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kaundinyakasibhatla.trainman_assignment.R
import com.kaundinyakasibhatla.trainman_assignment.databinding.ActivityGifDetailBinding
import kotlinx.android.synthetic.main.item_layout.view.*


class GifDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGifDetailBinding
    private var imageUrl:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gif_detail)
        imageUrl = intent.getStringExtra("imageUrl").toString()

        Glide.with(binding.gifImageView.context)
            .load(imageUrl)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }

            })
            .into(binding.gifImageView)



    }

}