package com.company.skinnie.ui.scan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.company.skinnie.Preferences
import com.company.skinnie.databinding.ActivityPreviewImageBinding
import com.company.skinnie.reduceFileImage
import com.company.skinnie.rotateBitmap
import com.company.skinnie.uriToFile
import java.io.File

class PreviewImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewImageBinding

    private var imagePhoto: File? = null

    private val viewModel: PreviewImageViewModel by lazy {
        PreviewImageViewModel()
    }

    companion object {
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val predict = Preferences(this).getValues("predict").toString()

        if (predict.isNotEmpty()) {
            startActivity(Intent(this, ResultScanActivity::class.java))
            finish()
        }

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        //actionbar in activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnCamera.setOnClickListener { startCameraX() }
        binding.btnGalery.setOnClickListener { startGallery() }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.btnSend.setOnClickListener {
            if (imagePhoto != null) {
                binding.loading.visibility = View.VISIBLE
                uploadImage()
            } else {
                Toast.makeText(this, "Gambar tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadImage() {
        viewModel.uploadImage(imagePhoto!!, getFile.toString()).observe(this) {
            binding.loading.visibility = View.GONE
            if (it != null) {
                val preferences = Preferences(this)

                preferences.setValues("predict", it.predicted!!)
                preferences.setValues("ingredient1", it.keyIngredients1!!)
                preferences.setValues("ingredient2", it.keyIngredients2!!)
                preferences.setValues("ingredient3", it.keyIngredients3!!)
                preferences.setValues("ingredient4", it.keyIngredients4!!)

                Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ResultScanActivity::class.java))
                finish()
            } else {
                binding.loading.visibility = View.GONE
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
            val result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                isBackCamera
            )
            //val file = reduceFileImage(result, myFile)
            //getFile = file
            imagePhoto = reduceFileImage(result, myFile)
            binding.ivPreviewImage.setImageBitmap(result)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private var getFile: File? = null
    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this)
            getFile = myFile
            val results = BitmapFactory.decodeFile(myFile.path)
            binding.ivPreviewImage.setImageURI(selectedImg)
            //untuk mengurangi ukuran gambar
            imagePhoto = reduceFileImage(results, myFile)
        }
    }
}