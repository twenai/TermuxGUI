# TermuxGUI

Aplikasi Android sederhana untuk TermuxGUI dengan antarmuka awal berbasis `MainActivity`.

## Requirements
- JDK 17
- Android SDK (API 34)
- Gradle 8+

## Build
```bash
gradle assembleDebug
```

Output APK debug ada di:
`app/build/outputs/apk/debug/app-debug.apk`

## Test
```bash
gradle testDebugUnitTest
```

Untuk instrumented test (butuh emulator/perangkat):
```bash
gradle connectedDebugAndroidTest
```

## Known limitations
- Tampilan saat ini masih minimal (satu layar status).
- Fitur utama TermuxGUI belum diimplementasikan di codebase ini.
