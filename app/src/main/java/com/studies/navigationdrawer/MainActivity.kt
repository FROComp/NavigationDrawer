package com.studies.navigationdrawer

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawer,
            R.string.open,
            R.string.close
        )



        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setCheckedItem(R.id.home)
        replaceFragment(HomeFragment(), title = "Home Fragment")

        navView.setNavigationItemSelectedListener {
            it.isChecked = true

            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment(), it.title.toString())
                }

                R.id.message -> {
                    replaceFragment(MessageFragment(), it.title.toString())
                }

                R.id.login -> {
                    replaceFragment(LoginFragment(), it.title.toString())
                }

                R.id.settings -> {
                    replaceFragment(SettingsFragment(), it.title.toString())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_1, fragment).commit()
        setTitle(title)
        drawer.closeDrawers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)

    }
}
