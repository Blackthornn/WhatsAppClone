package com.theblackthorn.whatsappclone.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.theblackthorn.whatsappclone.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        loginBtnID.setOnClickListener {


            var email = loginEmailEditText.text.toString().trim()
            var password = loginPasswordEditText.text.toString().trim()

            if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
            loginUser(email, password)
        }else{
                Toast.makeText(this, "Sorry login failed!", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun loginUser(email: String, password: String) {

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                task: Task<AuthResult> ->
                if (task.isSuccessful) {

                    var username = email.split("@")[0]

                    var dashboardIntent = Intent(this, DashboardActivity::class.java)
                    dashboardIntent.putExtra("name", username)
                    startActivity(dashboardIntent)
                    finish()

                }else {
                    Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show()
                }

            }
    }


}
