# Tinjauan Codebase TermuxGUI

Berikut empat tugas yang diusulkan berdasarkan temuan dari codebase saat ini.

## 1) Tugas perbaikan salah ketik
**Temuan:** Teks UI di `activity_main.xml` menampilkan kalimat bahasa Inggris yang kaku, `TwenXCI Rebuild Success`, yang terkesan placeholder dan berpotensi typo konteks terhadap nama proyek/release.

**Tugas yang diajukan:**
- Ubah string tampilan utama menjadi kalimat final yang konsisten dengan branding aplikasi (mis. "TermuxGUI berhasil dimuat").
- Pindahkan string hardcoded ke `strings.xml` agar mudah dilokalisasi dan direview.

## 2) Tugas perbaikan bug
**Temuan:** `MainActivity` memiliki `intent-filter` `MAIN/LAUNCHER`, namun belum mendeklarasikan `android:exported="true"` di `AndroidManifest.xml`. Pada Android 12+ ini dapat menyebabkan instalasi/build gagal.

**Tugas yang diajukan:**
- Tambahkan `android:exported="true"` pada deklarasi `MainActivity`.
- Verifikasi build debug berhasil dengan Android Gradle Plugin yang digunakan.

## 3) Tugas perbaikan komentar kode / ketidaksesuaian dokumentasi
**Temuan:** Proyek belum memiliki dokumentasi dasar (README) yang menjelaskan tujuan aplikasi, dependensi utama, dan langkah build/jalankan. Ini membuat konteks implementasi dan ekspektasi perilaku sulit diverifikasi.

**Tugas yang diajukan:**
- Tambahkan `README.md` berisi deskripsi aplikasi, requirement Android/Java/Gradle, dan perintah build.
- Sertakan bagian "Known limitations" untuk mengurangi mismatch ekspektasi pengguna vs implementasi saat ini.

## 4) Tugas peningkatan pengujian
**Temuan:** Belum ada unit test maupun instrumented test.

**Tugas yang diajukan:**
- Tambahkan minimal:
  - 1 unit test (`app/src/test`) untuk validasi konfigurasi/utility sederhana.
  - 1 instrumented test (`app/src/androidTest`) yang memverifikasi `MainActivity` dapat diluncurkan.
- Integrasikan eksekusi test ke pipeline CI agar regresi terdeteksi otomatis.
