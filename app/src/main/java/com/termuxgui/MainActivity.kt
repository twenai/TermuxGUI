package com.termuxgui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnPermission).setOnClickListener {
            openOverlaySettings()
        }

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            if (canDrawOverlays()) {
                startService(Intent(this, FloatingMenuService::class.java))
                Toast.makeText(this, "Floating menu started", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Aktifkan izin overlay dulu", Toast.LENGTH_SHORT).show()
                openOverlaySettings()
            }
        }

        findViewById<Button>(R.id.btnStop).setOnClickListener {
            stopService(Intent(this, FloatingMenuService::class.java))
            Toast.makeText(this, "Floating menu stopped", Toast.LENGTH_SHORT).show()
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
