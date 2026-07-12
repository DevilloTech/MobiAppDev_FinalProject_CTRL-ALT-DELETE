package com.example.technicalsumm2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.technicalsumm2.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Notification Channel ID
    private val channelId = "technicalsumm_channel"

    // Notification ID
    private var notificationId = 1

    // Request notification permission
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                sendNotification()
            } else {
                Toast.makeText(
                    this,
                    "Notification permission denied.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("theme_pref", MODE_PRIVATE)

        val darkMode = prefs.getBoolean("dark_mode", false)

        val desiredMode = if (darkMode) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        if (AppCompatDelegate.getDefaultNightMode() != desiredMode) {
            AppCompatDelegate.setDefaultNightMode(desiredMode)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        createNotificationChannel()

        binding.toolbar.overflowIcon?.setTint(
            ContextCompat.getColor(this, R.color.white)
        )
        supportActionBar?.title = "Home"

        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            view.setPadding(
                view.paddingLeft,
                statusBarInsets.top,
                view.paddingRight,
                view.paddingBottom
            )
            insets
        }

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportActionBar?.title = "Home"
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.nav_about_us -> {
                    supportActionBar?.title = "About Us"
                    replaceFragment(AboutUsFragment())
                    true
                }

                R.id.nav_profile -> {
                    supportActionBar?.title = "Profile"
                    replaceFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment).commit()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        val themeItem = menu?.findItem(R.id.action_theme)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            themeItem?.setTitle(R.string.action_light_theme)
        } else {
            themeItem?.setTitle(R.string.action_dark_theme)
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_settings -> {
                // TODO: Just a button
                true
            }

            R.id.action_help -> {
                // TODO: Just a button
                true
            }

            R.id.action_notify -> {
                checkPermissionAndNotify() // To notify
                true
            }

            R.id.action_theme -> {
                toggleTheme()
                true
            }

            R.id.action_exit -> {
                finishAffinity()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toggleTheme() {

        val prefs = getSharedPreferences("theme_pref", MODE_PRIVATE)
        val editor = prefs.edit()

        if (AppCompatDelegate.getDefaultNightMode() ==
            AppCompatDelegate.MODE_NIGHT_YES) {

            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )

            editor.putBoolean("dark_mode", false)

        } else {

            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )

            editor.putBoolean("dark_mode", true)
        }

        editor.apply()

        Toast.makeText(
            this,
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                "Switching to Dark"
            else
                "Switching to Light",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                channelId,
                "Technical Summ Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for Technical Summ 2"
            }

            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    private fun checkPermissionAndNotify() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            requestPermissionLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )

        } else {

            sendNotification()

        }
    }

    private fun sendNotification() {

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("CTRL + ALT + DELETE")
            .setContentText("This is a notification from our Group.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        getSystemService(NotificationManager::class.java)
            .notify(notificationId++, notification)
    }
}