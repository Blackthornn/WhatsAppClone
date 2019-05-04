package com.theblackthorn.whatsappclone.Activities

import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.theblackthorn.whatsappclone.R
import kotlinx.android.synthetic.main.activity_status.*

class StatusActivity : AppCompatActivity() {

    var mDatabase: DatabaseReference? = null
    var mCurrentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        supportActionBar!!.title = "Status"

        if (intent.extras != null) {
            var oldStatus = intent.extras.get("status")
            statusUpdateEditText.setText(oldStatus.toString())
        }
        if (intent.extras.equals(null)) {
            statusUpdateEditText.setText("Enter your New Status")
        }

        statusUpdateBtn.setOnClickListener {

            mCurrentUser = FirebaseAuth.getInstance().currentUser
            var userId = mCurrentUser!!.uid

            mDatabase = FirebaseDatabase.getInstance().reference
                .child("Users")
                .child(userId)

            var status = statusUpdateEditText.text.toString().trim()

            mDatabase!!.child("status")
                .setValue(status).addOnCompleteListener {
                    task: Task<Void> ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Status successfully updated!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, SettingsActivity::class.java))

                    } else {

                        Toast.makeText(this, "Unable to update status at this time", Toast.LENGTH_LONG).show()

                    }
                }


        }
    }
}
