package com.theblackthorn.whatsappclone.Activities

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.theblackthorn.whatsappclone.Adapters.SectionPagerAdapter
import com.theblackthorn.whatsappclone.MainActivity
import com.theblackthorn.whatsappclone.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

            var sectionAdapter: SectionPagerAdapter? = null

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_dashboard)
            supportActionBar!!.title = "Dashboard"

            sectionAdapter = SectionPagerAdapter(supportFragmentManager)
            dashViewPagerId.adapter = sectionAdapter
            mainTabs.setupWithViewPager(dashViewPagerId)
            mainTabs.setTabTextColors(Color.WHITE, Color.MAGENTA)

        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            super.onCreateOptionsMenu(menu)
            menuInflater.inflate(R.menu.main_menu, menu)

            return true
        }

        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            super.onOptionsItemSelected(item)

            if (item != null) {
                if (item.itemId == R.id.logoutId) {
                    // log the user out
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                if (item.itemId == R.id.settingsId) {
                    // take user to settingsActivity
                    startActivity(Intent(this, SettingsActivity::class.java))
                }
            }

            return true
        }

}
