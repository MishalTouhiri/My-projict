# مقهى الصباح — Cafe Menu App (Java / Android)

Android app for ordering from a small cafe menu.

- Welcome screen with the customer's name (validated inline)
- Menu in two sections (drinks, breakfast) with photos, descriptions and prices
- Quantity steppers per item, with a live item count and total at the bottom
- Order receipt screen with each line, the total and an order number
- Cafe info screen: address, hours, contact and the team
- Material 3 components, Arabic RTL layout, coffee colour palette

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
