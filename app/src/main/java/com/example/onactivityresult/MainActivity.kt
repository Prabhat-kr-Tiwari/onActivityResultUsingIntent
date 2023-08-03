package com.example.onactivityresult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.onactivityresult.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_RESULT_ACTIVITY = 1
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
      /*  binding.btnStartActivityForResult.setOnClickListener {
            val intent= Intent(this,ResultActivity::class.java)
            startActivityForResult(intent,REQUEST_CODE_RESULT_ACTIVITY)
        }*/

        // Receiver
        val getResult =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()) {
                if(it.resultCode == Activity.RESULT_OK){
                    //val value = it.data?.getStringExtra("input")
                    // The ResultActivity returned successfully
                    val resultText = it.data?.getStringExtra(ResultActivity.EXTRA_RESULT_TEXT)
                    val tvResult: TextView = findViewById(R.id.tvResult)
                    tvResult.text = resultText
                }else{
                    Toast.makeText(this, "Result is cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        // Caller
        binding.btnStartActivityForResult.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            getResult.launch(intent)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==REQUEST_CODE_RESULT_ACTIVITY){
            if(resultCode==Activity.RESULT_OK) {
                // The ResultActivity returned successfully
                val resultText = data?.getStringExtra(ResultActivity.EXTRA_RESULT_TEXT)
                val tvResult: TextView = findViewById(R.id.tvResult)
                tvResult.text = resultText
            }else{
                Toast.makeText(this, "Result is cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}