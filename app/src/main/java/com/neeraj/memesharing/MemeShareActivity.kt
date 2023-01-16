package com.neeraj.memesharing

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_meme_share.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemeShareActivity : AppCompatActivity() {

    var mMemeUrl : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme_share)

        getMeme()

    }




    private fun getMeme() {

        progress_circular_act_meme.visibility = View.VISIBLE

        val apiInterface = ApiInterface.create().getMemes()

        apiInterface.enqueue(object : Callback<MemeResponse> {
            override fun onResponse(call: Call<MemeResponse>, response: Response<MemeResponse>) {

                if(response.isSuccessful) {

                 mMemeUrl = response.body()?.url
                    Glide.with(this@MemeShareActivity)
                        .load(mMemeUrl)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                progress_circular_act_meme.visibility = View.GONE
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                progress_circular_act_meme.visibility = View.GONE
                                return false
                            }
                        }).into(img_act_meme_share)

                }

            }

            override fun onFailure(call: Call<MemeResponse>, t: Throwable) {
                progress_circular_act_meme.visibility = View.GONE
            }


        })

    }


    fun nextMeme() {
        getMeme()
    }

    fun shareMeme() {

        val intent : Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mMemeUrl)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(intent, "Sharing Meme...")
        startActivity(shareIntent)



        /*val intent1 = Intent(Intent.ACTION_SEND)
        intent1.type = "text/plain"
        intent1.putExtra(Intent.EXTRA_TEXT, mMemeUrl)
        val chooser = Intent.createChooser(intent1, "fasfd")
        startActivity(chooser)*/


    }

}