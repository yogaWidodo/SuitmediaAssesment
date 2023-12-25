package com.capstone.suitmediaassesment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.suitmediaassesment.databinding.ActivityMainBinding
import com.capstone.suitmediaassesment.utils.IsPalindrome

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            val palindrome = binding.etPalindrome.text.toString()
            if (palindrome.isEmpty()) {
                binding.etPalindrome.error = "Please enter some text"
                return@setOnClickListener
            }
            if (IsPalindrome.isPalindrome(palindrome)) {
                IsPalindrome.alertDialog(true, this)
            } else {
                IsPalindrome.alertDialog(false, this)
            }

        }

        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name.isEmpty()) {
                binding.etName.error = "Please enter the Name"
                return@setOnClickListener
            }
            val intent= Intent(this, SecondActivity::class.java)
            intent.putExtra("userName", name)
            startActivity(intent)
        }
    }
}