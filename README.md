# TermuxGUI Android Project

Project ini adalah **Android native app (Kotlin)** dengan **floating window menu** untuk mengirim command shell ke Termux.

## Fitur Utama
- Floating menu modern yang bisa digeser.
- Splash screen modern.
- Kontrol cepat dari Main Menu modern:
  - Buka izin overlay
  - Start/Stop floating menu
  - Buka aplikasi Termux
- Integrasi ke Termux via `com.termux.RUN_COMMAND` (`RunCommandService`).
- Build APK per arsitektur ABI + universal di GitHub Actions.

## Izin (Permissions)
Manifest sudah menambahkan izin/integrasi yang dibutuhkan untuk skenario TermuxGUI:
- `SYSTEM_ALERT_WINDOW` (floating menu)
- `com.termux.permission.RUN_COMMAND` (kirim command ke Termux)
- `INTERNET`
- `ACCESS_NETWORK_STATE`
- `WAKE_LOCK`
- `FOREGROUND_SERVICE`
- `POST_NOTIFICATIONS`
- `queries` untuk `com.termux` dan `com.termux.api`

> Catatan: beberapa izin tetap harus disetujui user di perangkat (misalnya overlay/notification) dan Termux/Termux:API harus terpasang.

## Build Lokal
1. Install Android SDK + JDK 17 + Gradle.
2. Jalankan:
   ```bash
   gradle wrapper
   ./gradlew :app:assembleDebug
   ```

## GitHub Actions Output APK (Tidak Digabung)
Workflow Actions akan menghasilkan artifact terpisah agar user langsung pilih sesuai perangkat:
- `termuxgui-debug-arm64-v8a` → Android 64-bit ARM
- `termuxgui-debug-armeabi-v7a` → Android 32-bit ARM
- `termuxgui-debug-x86` → Intel/Emulator 32-bit
- `termuxgui-debug-x86_64` → Intel/Emulator 64-bit
- `termuxgui-debug-universal` → universal (lebih besar)

Di tab **Actions**, lihat **Summary** untuk panduan pilihan ABI, lalu download artifact yang sesuai.
