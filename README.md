# TermuxGUI Android Project

Project ini sekarang sudah diubah menjadi **project Android native (Kotlin)** untuk membuat **floating window menu** yang dapat mengirim command ke Termux.

## Fitur
- Floating menu (overlay) yang bisa digeser.
- Tombol fungsi cepat untuk command Termux:
  - Battery status
  - Device info
  - WiFi connection info
  - Toast via Termux API
  - Setup storage
- Integrasi ke Termux memakai `RunCommandService` (`com.termux.RUN_COMMAND`).
- Siap dibuild via GitHub Actions.

## Struktur
- `app/` : source Android app.
- `.github/workflows/android-build.yml` : workflow build APK debug.
- `docs/analisis-termux-v0.118.3.md` : dokumen analisis sebelumnya.

## Cara Build Lokal
1. Install Android SDK + Gradle + JDK 17.
2. Jalankan:
   ```bash
   gradle wrapper
   ./gradlew :app:assembleDebug
   ```

## Cara Pakai Aplikasi
1. Install APK.
2. Buka aplikasi `TermuxGUI`.
3. Beri izin overlay (draw over other apps).
4. Klik **Start Floating Menu**.
5. Pastikan aplikasi **Termux** dan **Termux:API** sudah terpasang agar command bisa berjalan.

## Catatan Penting
- Service menjalankan command melalui package `com.termux`, jadi environment Termux harus tersedia.
- Untuk distribusi release, Anda bisa lanjut tambah signing config di Gradle.


## GitHub Actions Output APK (Pilihan ABI)
Workflow akan menghasilkan beberapa APK agar user bisa menyesuaikan perangkat:
- `arm64-v8a` (Android 64-bit ARM)
- `armeabi-v7a` (Android 32-bit ARM)
- `x86`
- `x86_64`
- `universal` (semua ABI, file lebih besar)

Di tab **Actions**, lihat artifact `termuxgui-debug-apks` dan pilih file APK sesuai device.
