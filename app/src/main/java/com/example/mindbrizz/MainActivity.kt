package com.example.mindbrizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var txtocd:CardView
    private lateinit var txtstress:CardView
    private lateinit var txtpaddiction:CardView
    private lateinit var txtsuicide:CardView
    private lateinit var Txtanger:CardView
    private lateinit var toggle:ActionBarDrawerToggle
    private lateinit var chat:CardView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Txtanger = findViewById(R.id.txtanger)
        chat = findViewById(R.id.txtchat)
        txtocd = findViewById(R.id.ctxtocd)
        txtstress = findViewById(R.id.ctxtStress)
        txtpaddiction = findViewById(R.id.ctxtpornAddiction)
        txtsuicide = findViewById(R.id.ctxtsuicide)

        txtsuicide.setOnClickListener {
            startActivity(Intent(this,SuicidemngActivity::class.java))
            finish()
        }

        txtpaddiction.setOnClickListener {
            startActivity(Intent(this,PornAddictionmngActivity::class.java))
            finish()
        }

        txtstress.setOnClickListener {
            startActivity(Intent(this, StressmngActivity::class.java))
            finish()
        }

        txtocd.setOnClickListener {
            startActivity(Intent(this,OCDmngActivity::class.java))
            finish()
        }

        chat.setOnClickListener {
            startActivity(Intent(this,VerificationActivity::class.java))
            finish()
        }

        Txtanger.setOnClickListener {
            startActivity(Intent(this,AngermngActivity::class.java))
            finish()
        }

        val drawerLayout:DrawerLayout = findViewById(R.id.drawerlayout)
        val navView:NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_message-> Toast.makeText(this, "chat clicked", Toast.LENGTH_SHORT).show()
            }
         true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
