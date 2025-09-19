# Android Setup Instructions

## Prerequisites

1. **Android Studio**: Download and install Android Studio
2. **Android SDK**: Ensure Android SDK 34 is installed
3. **Java/Kotlin**: Android Studio includes OpenJDK

## Building the Project

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd android
   ```

2. Open in Android Studio:
   - File → Open → Select the project directory
   - Wait for Gradle sync to complete

3. Set up an emulator or connect a physical device

4. Build and run:
   ```bash
   ./gradlew assembleDebug
   ./gradlew installDebug
   ```

## Running with Your Backend

1. Start your Node.js server on port 3000
2. Ensure the following endpoints are available:
   - `POST /api/auth/signin`
   - `POST /api/auth/signup`

3. If using a physical device, update the IP address in `NetworkModule.kt`:
   ```kotlin
   private const val BASE_URL = "http://YOUR_IP_ADDRESS:3000/"
   ```

## Troubleshooting

- **Gradle Issues**: Try `./gradlew clean` then rebuild
- **Network Issues**: Check if emulator can reach `10.0.2.2:3000`
- **Build Errors**: Ensure Android SDK 34 is installed

## Features Implemented

✅ Login screen with email/password fields
✅ Register screen with name, email, password fields  
✅ JWT token handling and display
✅ Error handling and loading states
✅ Navigation between screens
✅ Material3 design system
✅ Retrofit networking with OkHttp
✅ MVVM architecture with ViewModels
✅ Kotlin Coroutines for async operations