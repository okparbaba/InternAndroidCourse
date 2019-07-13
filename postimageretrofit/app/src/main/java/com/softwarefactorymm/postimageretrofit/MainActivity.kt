package com.softwarefactorymm.postimageretrofit
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private var bitmap: Bitmap? = null
    private val uiHelper = UiHelper()
    private val reference = this
    companion object {
        private const val PICK_IMAGE_REQUEST_CODE = 546
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        findViewById<Button>(R.id.selectImageButton).setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST_CODE)
        }
        findViewById<Button>(R.id.uploadImageButton).setOnClickListener {
            if (bitmap != null) {
                val imageBytes = uiHelper.getImageUrl(bitmap!!)
                uploadImage(imageBytes)
            } else
                Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadImage(imageBytes: String) {
        if (TextUtils.isEmpty(etTitle.text)){
            etTitle.error = "Title required"
            etTitle.requestFocus()
            return
        }
        if (TextUtils.isEmpty(etTitle.text)){
            etDetail.error = "Detail required"
            etDetail.requestFocus()
            return
        }
        val materialDialog = uiHelper.showAlwaysCircularProgress(this, "Uploading Post")
        ServiceApi.Factory.getInstance(this)?.uploadImage(etTitle.text.toString(),etDetail.text.toString(),imageBytes)
            ?.enqueue(object : Callback<StatusMessageResponse> {
                override fun onFailure(call: Call<StatusMessageResponse>?, t: Throwable?) {
                    Toast.makeText(reference, "Post uploaded${t.toString()}", Toast.LENGTH_SHORT).show()
                    materialDialog.dismiss()
                    tvShow.text = t.toString()
                }

                override fun onResponse(call: Call<StatusMessageResponse>?, response: Response<StatusMessageResponse>?) {
                    materialDialog.dismiss()
                    tvShow.text = response?.toString()
                    Toast.makeText(reference, "Upload Error${response.toString()}", Toast.LENGTH_SHORT).show()
                    // Error Occurred during uploading
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST_CODE && RESULT_OK == resultCode) {
            data?.let {
                try {
                    bitmap = uiHelper.decodeUri(this, it.data)
                    imageView.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    if (bitmap != null) imageView.setImageBitmap(bitmap)
                }
            }
        }
    }
}