package com.ynl.typeofwidgetvideoview

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView

@SuppressLint("Registered")
class MainActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private var arrayList = arrayListOf(
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        )

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.videoView)
        val mediacontroller = MediaController(this)
        mediacontroller.setAnchorView(videoView)


        videoView.setMediaController(mediacontroller)
        videoView.setVideoURI(Uri.parse(arrayList[index]))
        videoView.requestFocus()

        videoView.setOnPreparedListener { mp ->
            mp.setOnVideoSizeChangedListener { _, _, _ ->
                videoView.setMediaController(mediacontroller)
                mediacontroller.setAnchorView(videoView)
            }
        }

        videoView.setOnCompletionListener { mp ->
            Toast.makeText(applicationContext, "Video over", Toast.LENGTH_SHORT).show()
            if (index++ == arrayList.size) {
                index = 0
                mp.release()
                Toast.makeText(applicationContext, "Videos completed", Toast.LENGTH_SHORT).show()
            } else {
                videoView.setVideoURI(Uri.parse(arrayList[index]))
                videoView.start()
            }
        }

        videoView.setOnErrorListener { _, what, extra ->
            Log.d("API123", "What $what extra $extra")
            false
        }
    }
}

