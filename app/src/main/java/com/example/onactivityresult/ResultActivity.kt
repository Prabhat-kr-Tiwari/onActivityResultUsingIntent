package com.example.onactivityresult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.onactivityresult.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_RESULT_TEXT = "result_text"
    }
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_result)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_result)
        binding.btnSetResult.setOnClickListener {
            // When the user clicks on "Set Result" button, set the result and finish the activity
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_RESULT_TEXT, "This is the result from ResultActivity")
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            // When the user clicks on "Cancel" button, just finish the activity without setting a result
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }
}