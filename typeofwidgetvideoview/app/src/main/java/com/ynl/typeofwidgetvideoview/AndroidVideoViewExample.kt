package com.ynl.typeofwidgetvideoview

import android.app.Activity
import android.app.ProgressDialog
import android.content.res.Configuration
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.VideoView

class AndroidVideoViewExample : Activity() {

    private var myVideoView: VideoView? = null
    private var position = 0
    private var progressDialog: ProgressDialog? = null
    private var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set the main layout of the activity
        setContentView(R.layout.activity_main)

        //set the media controller buttons
        if (mediaControls == null) {
            mediaControls = MediaController(this@AndroidVideoViewExample)
        }

        //initialize the VideoView
        myVideoView = findViewById(R.id.videoView)

        // create a progress bar while the video file is loading
        progressDialog = ProgressDialog(this@AndroidVideoViewExample)
        // set a title for the progress bar
        progressDialog!!.setTitle("JavaCodeGeeks Android Video View Example")
        // set a message for the progress bar
        progressDialog!!.setMessage("Loading...")
        //set the progress bar not cancelable on users' touch
        progressDialog!!.setCancelable(false)
        // show the progress bar
        progressDialog!!.show()

        try {
            //set the media controller in the VideoView
            myVideoView!!.setMediaController(mediaControls)

            //set the uri of the video to be played
            myVideoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.aa))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }

        myVideoView!!.requestFocus()
        //we also set an setOnPreparedListener in order to know when the video file is ready for playback
        myVideoView!!.setOnPreparedListener {
            // close the progress bar and play the video
            progressDialog!!.dismiss()
            //if we have a position on savedInstanceState, the video playback should start from here
            myVideoView!!.start()
            myVideoView!!.seekTo(20000)

//            if (position == 0) {
//                myVideoView!!.start()
//            } else {
//                //if we come from a resumed activity, video playback will be paused
//                myVideoView!!.pause()
//            }
        }

    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        //we use onSaveInstanceState in order to store the video playback position for orientation change
        savedInstanceState.putInt("Position", myVideoView!!.currentPosition)
        myVideoView!!.pause()
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        position = savedInstanceState.getInt("Position")
        myVideoView!!.seekTo(position)
    }
}