package com.capstone.suitmediaassesment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.suitmediaassesment.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }


    }

    private fun initUI() {
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        if (firstName!=null && lastName!=null){
            val selectedName = getString(R.string.full_name, firstName, lastName)
            binding.selectedName.text = selectedName
        }else{
            binding.selectedName.text = getString(R.string.full_name, "Selected", "User Name")
        }

        val user = intent.getStringExtra("userName")
        binding.userName.text = user
    }
}