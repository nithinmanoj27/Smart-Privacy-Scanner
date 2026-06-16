# Android Application Setup Guide

This guide will walk you through the process of setting up an Android application in Android Studio and running it on an Android device.

## Prerequisites

- Android Studio installed on your computer.
- An Android device with USB debugging enabled.
- A USB cable to connect your Android device to the computer.

## Steps

### 1. Open Android Studio

Launch Android Studio after installation.

### 2. Set Up Your Project

Create a new project or open an existing project in Android Studio.

### 3. Connect Your Android Device

Connect your Android device to your computer using a USB cable.

- Enable Developer Options on your Android device:
  - Go to `Settings > About phone`.
  - Tap on `Build number` 7 times to enable Developer Options.
- Enable USB Debugging:
  - Go to `Settings > Developer Options` and turn on `USB Debugging`.

### 4. Configure Device in Android Studio

- Click on the "Run" menu in Android Studio.
- Select "Edit Configurations...".
- Ensure your connected device is selected under "Deployment Target Options".

### 5. Run Your Application

- Click on the green "Run" button (or use `Shift + F10`) in Android Studio.
- The app will be compiled and installed on your connected Android device.

### 6. Run App on Emulator (Optional)

- If desired, run the app on an emulator:
  - Go to `Tools > AVD Manager` in Android Studio.
  - Create or select a virtual device.
  - Launch the emulator by clicking on the "Play" button.

### 7. Test Your Application

- Once installed on your device or emulator, click the app's icon to launch and test your application.

### 8. Troubleshooting

- Check Android Studio logs for error messages if you encounter any issues.
- Update SDK tools or drivers if needed.

Note: Ensure your Android device meets the minimum requirements for running the Android application.

That's it! Your Android application should now be set up and running on your device or emulator.
