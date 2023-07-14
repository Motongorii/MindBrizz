package com.example.mindbrizz


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

class SignInActivity : AppCompatActivity() {
    private lateinit var etemail:EditText
    private lateinit var tvForgotPassword:TextView
    private lateinit var etPassword:EditText
    private lateinit var bSignIn:Button
    private lateinit var tvSignUp:TextView
    private lateinit var progress:ProgressDialog
    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        bSignIn = findViewById(R.id.bSignIn)
        tvForgotPassword= findViewById(R.id.tvForgotPassword)
        etemail = findViewById(R.id.etEmail)
        etPassword= findViewById(R.id.etPassword)
        tvSignUp = findViewById(R.id.tvSignUp)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")


        tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        tvForgotPassword.setOnClickListener {
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
        }

        bSignIn.setOnClickListener {
            val email = etemail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty()){
                etemail.setError("Please fill this input")
                etemail.requestFocus()
            }else if(password.isEmpty()){
                etPassword.setError("Please fill the input")
                etPassword.requestFocus()
            }else{
                // Proceed to register  the user
                progress.show()
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    progress.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this, "Welcome to MindBrizz",
                            Toast.LENGTH_SHORT).show()
                        mAuth.signOut()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()

                    }else{
                        displaymessage("Error", it.exception!!.message.toString())
                    }
                }
            }


        }


    }


    fun displaymessage(title:String, message:String){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok",null)
        alertDialog.create().show()
    }
}