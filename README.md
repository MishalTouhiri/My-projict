# ☕ Morning Café — مقهى الصباح

Android app for ordering from a small café menu, in Arabic and English.

![Java](https://img.shields.io/badge/Java-Android-3DDC84?logo=android&logoColor=white)
![Material 3](https://img.shields.io/badge/Material-3-6750A4?logo=materialdesign&logoColor=white)
![Min SDK](https://img.shields.io/badge/minSdk-34-informational)
![Tests](https://img.shields.io/badge/tests-Robolectric-brightgreen)

| Welcome | Menu | Receipt | Café info |
|:---:|:---:|:---:|:---:|
| <img src="docs/screenshots/en-1-welcome.webp" width="200" alt="Welcome screen"> | <img src="docs/screenshots/en-2-menu.webp" width="200" alt="Menu screen"> | <img src="docs/screenshots/en-3-receipt.webp" width="200" alt="Receipt screen"> | <img src="docs/screenshots/en-4-info.webp" width="200" alt="Café info screen"> |
| <img src="docs/screenshots/ar-1-welcome.webp" width="200" alt="شاشة الترحيب"> | <img src="docs/screenshots/ar-2-menu.webp" width="200" alt="شاشة المنيو"> | <img src="docs/screenshots/ar-3-receipt.webp" width="200" alt="شاشة الفاتورة"> | <img src="docs/screenshots/ar-4-info.webp" width="200" alt="شاشة معلومات المقهى"> |

<sub>Screenshots are generated from the real app by `CafeFlowTest` (see below).</sub>

## Features

- Welcome screen with the customer's name (validated inline)
- Menu in two sections (drinks, breakfast) with photos, descriptions and prices
- Quantity steppers per item, with a live item count and total at the bottom
- Order receipt screen with each line, the total and an order number
- Café info screen: address, hours, contact and the team
- Material 3 components and a coffee colour palette
- Arabic (right to left) by default, English on English devices

## Tech stack

| | |
|---|---|
| Language | Java |
| Platform | Android (compile/target SDK 34, min SDK 34) |
| UI | AppCompat, Material Components 1.11 (Material 3), ConstraintLayout |
| Tests | JUnit 4, Robolectric 4.13 |
| Build | Gradle (Kotlin DSL) |

## Run

Open the project in Android Studio and run the `app` configuration on a device or
emulator with Android 14 (API 34) or newer, or from a terminal:

```bash
./gradlew installDebug
```

## Tests and screenshots

`CafeFlowTest` (Robolectric) walks the whole flow: name check, quantities and
total, receipt and the info screen. It can also save a PNG of every screen:

```bash
./gradlew testDebugUnitTest -Pcafe.screenshots=build/screens
```

---

<div dir="rtl">

**مقهى الصباح** — تطبيق أندرويد لطلب القهوة والفطور من منيو مقهى صغير: إدخال الاسم، اختيار الكميات مع إجمالي مباشر، فاتورة الطلب، وصفحة معلومات المقهى. يدعم العربية والإنجليزية.

</div>
