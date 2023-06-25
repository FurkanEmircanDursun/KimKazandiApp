package com.example.kimkazandi

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.kimkZazandi.roomDraw.DrawDao

import com.example.kimkazandi.roomDraw.DrawDatabase
import com.example.kimkazandi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DrawDatabase
    private lateinit var drawDao: DrawDao

    private lateinit var results: Results

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_general,
                R.id.nav_begins,
                R.id.nav_free,
                R.id.nav_car,
                R.id.nav_phoneAndTablet,
                R.id.nav_vacation,
                R.id.nav_myFollow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        db = Room.databaseBuilder(applicationContext, DrawDatabase::class.java, "draws")
            .allowMainThreadQueries()
            .build()

        drawDao = db.drawDao()

        val urlList = listOf(
            "https://www.kimkazandi.com/cekilisler",
            "https://www.kimkazandi.com/cekilisler/yeni-baslayanlar",
            "https://www.kimkazandi.com/cekilisler/bedava-katilim",
            "https://www.kimkazandi.com/cekilisler/araba-kazan",
            "https://www.kimkazandi.com/cekilisler/telefon-tablet-kazan",
            "https://www.kimkazandi.com/cekilisler/tatil-kazan"
        )

        results = Results()
        val run = Runnable {
            val lastLoginTime = getLastLoginTime(this@MainActivity)
            val currentTime = System.currentTimeMillis()

            // 3 saatten fazla süre geçtiyse
            if (currentTime - lastLoginTime > 3 * 60 * 60 * 1000) {
                Log.d("zaman","zaman 3 saatten fazla geçmiş")
                drawDao.deleteAll()

                for (page in urlList) {
                    val a = results.getAllData(page)
                    for (item in a) {
                        drawDao.insert(item)
                    }
                }

                saveLastLoginTime(this@MainActivity)
            }
        }

        Thread(run).start()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun saveLastLoginTime(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val currentTime = System.currentTimeMillis()
        editor.putLong("lastLoginTime", currentTime)
        editor.apply()
    }

    private fun getLastLoginTime(context: Context): Long {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getLong("lastLoginTime", 0)
    }
}