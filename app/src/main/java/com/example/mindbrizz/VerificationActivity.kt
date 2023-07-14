package com.example.mindbrizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mindbrizz.databinding.ActivityVerificationBinding
import com.google.firebase.auth.FirebaseAuth

class VerificationActivity : AppCompatActivity() {
    var binding:ActivityVerificationBinding? = null
    var auth:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVerificationBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)

        auth = FirebaseAuth.getInstance()
        if (auth!!.currentUser != null){
            val intent = Intent(this@VerificationActivity, ChatScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
        supportActionBar?.hide()
        binding!!.editNumber.requestFocus()
        binding!!.continueBtn.setOnClickListener{
            val intent = Intent(this@VerificationActivity, OTPActivity::class.java)
            intent.putExtra("phoneNumber", binding!!.editNumber.text.toString())
            startActivity(intent)
        }

    }
}