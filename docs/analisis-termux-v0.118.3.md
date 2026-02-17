# Analisis Rilis Termux v0.118.3

Sumber rilis: https://github.com/termux/termux-app/releases/tag/v0.118.3

## Ringkasan Cepat
- **Tag/Nama rilis:** `v0.118.3`.
- **Tanggal publikasi:** `2025-05-22T22:47:26Z`.
- **Ruang lingkup perubahan:** 1 perubahan utama terkait nama variabel build + 3 perbaikan penting (kompatibilitas Android 16 QPR1, stabilitas izin storage, dan kompatibilitas serialisasi antaraplikasi/plugin).

## Perubahan (Changed)
1. **Perubahan nama variabel build**
   - `TERMUX_APP__BUILD_DATA_DIR` dihapus.
   - `TERMUX__ROOTFS` diganti menjadi `TERMUX__ROOTFS_DIR`.
   - Dampak praktis: skrip build/otomasi internal yang masih memakai nama lama harus diperbarui agar tidak gagal membaca variabel.

## Perbaikan (Fixed)
1. **Perbaikan crash Android 16 QPR1**
   - Dependency `org.lsposed.hiddenapibypass:hiddenapibypass` dinaikkan ke `6.1`.
   - Dampak pengguna: mencegah crash pada perangkat Android 16 QPR1.

2. **Perbaikan masalah izin storage pasca update**
   - `MANAGE_EXTERNAL_STORAGE` kini dideklarasikan di aplikasi Termux.
   - Latar belakang: penambahan izin di Termux:API `0.51.0` dapat menyebabkan izin storage aplikasi Termux tercabut setelah update.
   - Dampak pengguna: menurunkan risiko Termux kehilangan akses penyimpanan setelah pembaruan.

3. **Perbaikan kompatibilitas serialisasi Java (`Serializable`)**
   - Menambahkan `serialVersionUID` eksplisit untuk kelas seperti `ReportInfo` dan `TextIOInfo`.
   - Latar belakang: mencegah exception saat baca objek serializable lintas aplikasi/plugin (contoh alur notifikasi error plugin).
   - Dampak pengguna: alur integrasi Termux + Termux:API lebih stabil, terutama untuk notifikasi/error handling.

## Asset Build pada Halaman Rilis
- `arm64-v8a`, `armeabi-v7a`, `x86`, `x86_64`, `universal`, dan file `sha256sums` tersedia.
- Dari sisi ukuran, APK `universal` paling besar dibanding APK per-arsitektur.

## Penilaian Risiko Update
- **Untuk pengguna umum:** update ini termasuk **maintenance release penting** karena menyentuh crash, izin storage, dan stabilitas plugin.
- **Untuk pengelola build/otomasi:** ada **breaking change kecil** pada nama variabel build environment; perlu audit skrip CI/CD atau dokumentasi internal.

## Rekomendasi
1. Jika Anda pengguna Android terbaru (terutama jalur Android 16), **disarankan update**.
2. Setelah update, verifikasi:
   - akses storage Termux masih normal,
   - command dari Termux:API berjalan,
   - notifikasi error plugin dapat dibuka tanpa crash.
3. Untuk maintainer:
   - cari penggunaan variabel lama `TERMUX_APP__BUILD_DATA_DIR` dan `TERMUX__ROOTFS`,
   - migrasikan ke skema variabel baru (`TERMUX__ROOTFS_DIR` dan skema build terbaru yang disebutkan di changelog).
