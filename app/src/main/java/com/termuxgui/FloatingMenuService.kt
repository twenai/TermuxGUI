package com.termuxgui

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast

class FloatingMenuService : Service() {

    private lateinit var windowManager: WindowManager
    private var floatingView: View? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        floatingView = inflater.inflate(R.layout.view_floating_menu, null)

        val layoutType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 50
            y = 200
        }

        windowManager.addView(floatingView, params)
        setupDrag(floatingView!!, params)
        setupActions(floatingView!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (floatingView != null) {
            windowManager.removeView(floatingView)
            floatingView = null
        }
    }

    private fun setupDrag(view: View, params: WindowManager.LayoutParams) {
        val dragHeader = view.findViewById<View>(R.id.headerDrag)

        var initialX = 0
        var initialY = 0
        var initialTouchX = 0f
        var initialTouchY = 0f

        dragHeader.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialX = params.x
                    initialY = params.y
                    initialTouchX = event.rawX
                    initialTouchY = event.rawY
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    params.x = initialX + (event.rawX - initialTouchX).toInt()
                    params.y = initialY + (event.rawY - initialTouchY).toInt()
                    windowManager.updateViewLayout(view, params)
                    true
                }

                else -> false
            }
        }
    }

    private fun setupActions(view: View) {
        view.findViewById<Button>(R.id.btnClose).setOnClickListener { stopSelf() }
        view.findViewById<Button>(R.id.btnBattery).setOnClickListener {
            runTermuxCommand("termux-battery-status")
        }
        view.findViewById<Button>(R.id.btnDevice).setOnClickListener {
            runTermuxCommand("termux-deviceinfo")
        }
        view.findViewById<Button>(R.id.btnWifi).setOnClickListener {
            runTermuxCommand("termux-wifi-connectioninfo")
        }
        view.findViewById<Button>(R.id.btnToast).setOnClickListener {
            runTermuxCommand("termux-toast \"Halo dari TermuxGUI Android\"")
        }
        view.findViewById<Button>(R.id.btnStorage).setOnClickListener {
            runTermuxCommand("termux-setup-storage")
        }
    }

    private fun runTermuxCommand(command: String) {
        try {
            val intent = Intent().apply {
                setClassName("com.termux", "com.termux.app.RunCommandService")
                action = "com.termux.RUN_COMMAND"
                putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/usr/bin/bash")
                putExtra("com.termux.RUN_COMMAND_ARGUMENTS", arrayOf("-lc", command))
                putExtra("com.termux.RUN_COMMAND_BACKGROUND", true)
                putExtra("com.termux.RUN_COMMAND_WORKDIR", "/data/data/com.termux/files/home")
            }
            startService(intent)
            Toast.makeText(this, "Run: $command", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Gagal kirim command ke Termux. Pastikan Termux + Termux:API terpasang.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
