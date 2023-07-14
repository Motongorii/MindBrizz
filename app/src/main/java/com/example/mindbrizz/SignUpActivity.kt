

package com.example.mindbrizz


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity: AppCompatActivity() {
    private lateinit var etusername:EditText
    private lateinit var etemail:EditText
    private lateinit var etPassword:EditText
    private lateinit var bSignUp:Button
    private lateinit var tvSignIn:TextView
    private lateinit var progress:ProgressDialog
    private  lateinit var mAuth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        etusername = findViewById(R.id.etUsername)
        etemail = findViewById(R.id.etEmail)
        etPassword= findViewById(R.id.etPassword)
        tvSignIn = findViewById(R.id.tvSignIn)
        bSignUp = findViewById(R.id.bSignUp)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait ...")

        tvSignIn.setOnClickListener{
            startActivity(Intent(this,SignInActivity::class.java))
        }

        bSignUp.setOnClickListener {
            val email = etemail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val username = etusername.text.toString().trim()


            // Check if the user is submitting empty files

            if (email.isEmpty()){
                etemail.error = "Please fill this input"
                etemail.requestFocus()
            }else if(password.isEmpty()){
                etPassword.error = "Please fill the input"
                etPassword.requestFocus()
            }
            else if(username.isEmpty()){
                etusername.error = "Please fill the input"
                etusername.requestFocus()
            }
            else{
                // Proceed to register  the user
                progress.show()
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    progress.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this, "Registration Successful",
                            Toast.LENGTH_SHORT).show()
                        mAuth.signOut()
                        startActivity(Intent(this, SignInActivity ::class.java))
                        finish()

                    }else{
                        displaymessage("Error", it.exception!!.message.toString())
                    }
                }
            }
        }

    }
    private fun displaymessage(title:String, message:String){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok",null)
        alertDialog.create().show()
    }
}

