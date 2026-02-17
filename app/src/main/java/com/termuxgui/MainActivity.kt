package com.termuxgui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askNotificationPermissionIfNeeded()

        findViewById<MaterialButton>(R.id.btnPermission).setOnClickListener {
            openOverlaySettings()
        }

        findViewById<MaterialButton>(R.id.btnStart).setOnClickListener {
            if (canDrawOverlays()) {
                ContextCompat.startForegroundService(this, Intent(this, FloatingMenuService::class.java))
                Toast.makeText(this, "Floating menu started", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Aktifkan izin overlay dulu", Toast.LENGTH_SHORT).show()
                openOverlaySettings()
            }
        }

        findViewById<MaterialButton>(R.id.btnStop).setOnClickListener {
            stopService(Intent(this, FloatingMenuService::class.java))
            Toast.makeText(this, "Floating menu stopped", Toast.LENGTH_SHORT).show()
        }

        findViewById<MaterialButton>(R.id.btnOpenTermux).setOnClickListener {
            val launchIntent = packageManager.getLaunchIntentForPackage("com.termux")
            if (launchIntent != null) {
                startActivity(launchIntent)
            } else {
                Toast.makeText(this, "Aplikasi Termux belum terpasang", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun askNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun canDrawOverlays(): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(this)
    }

    private fun openOverlaySettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivity(intent)
        }
    }
}
