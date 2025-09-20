# Android Kotlin JWT Authentication App

A modern Android application built with Jetpack Compose that consumes a JWT authentication API at `localhost:3000/api/auth/signin`.

## Features

- **Modern UI**: Built entirely with Jetpack Compose
- **JWT Authentication**: Consumes REST API endpoints for login and registration
- **Network Layer**: Uses Retrofit with OkHttp for API communication
- **MVVM Architecture**: Clean architecture with ViewModels and Repository pattern
- **Navigation**: Seamless navigation between login, register, and success screens
- **Version Catalog**: Modern Gradle dependency management with `libs.versions.toml`

## Project Structure

```
app/
├── src/main/java/com/guacamolie/auth/
│   ├── MainActivity.kt                    # Main activity hosting the app
│   ├── data/
│   │   ├── api/
│   │   │   └── AuthApiService.kt         # Retrofit API interface
│   │   ├── model/
│   │   │   └── AuthModels.kt             # Data models for API requests/responses
│   │   └── repository/
│   │       └── AuthRepository.kt         # Repository handling API calls
│   ├── di/
│   │   └── NetworkModule.kt              # Dependency injection setup
│   ├── navigation/
│   │   └── AuthNavigation.kt             # Navigation setup between screens
│   ├── ui/
│   │   ├── screen/
│   │   │   ├── LoginScreen.kt            # Login screen UI
│   │   │   ├── RegisterScreen.kt         # Registration screen UI
│   │   │   └── SuccessScreen.kt          # Success screen showing JWT token
│   │   ├── theme/
│   │   │   ├── Color.kt                  # App color definitions
│   │   │   ├── Theme.kt                  # Material3 theme setup
│   │   │   └── Type.kt                   # Typography definitions
│   │   └── viewmodel/
│   │       └── AuthViewModel.kt          # ViewModel managing auth state
│   └── res/
│       ├── values/
│       │   ├── strings.xml               # String resources
│       │   └── themes.xml                # XML theme definitions
│       └── AndroidManifest.xml           # App manifest with Internet permission
├── build.gradle.kts                      # App-level Gradle build script
└── proguard-rules.pro                    # ProGuard rules
```

## API Endpoints

The app expects a Node.js backend running on `localhost:3000` with the following endpoints:

### POST `/api/auth/signin`
Login endpoint that accepts email and password and returns a JWT token.

**Request:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": "user123",
    "email": "user@example.com",
    "name": "John Doe"
  }
}
```

### POST `/api/auth/signup`
Registration endpoint for creating new accounts.

**Request:**
```json
{
  "email": "user@example.com",
  "password": "password123",
  "name": "John Doe"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": "user123",
    "email": "user@example.com",
    "name": "John Doe"
  }
}
```

## Key Dependencies

- **Jetpack Compose BOM**: 2024.08.00
- **Retrofit**: 2.11.0 for REST API communication
- **OkHttp**: 4.12.0 with logging interceptor
- **Gson**: 2.10.1 for JSON serialization
- **Coroutines**: 1.8.1 for asynchronous programming
- **Navigation Compose**: 2.7.7 for screen navigation
- **Material3**: Latest Material Design components
- **BCrypt**: 0.10.2 for secure password hashing

## Network Configuration

The app is configured to connect to `localhost:3000` through the Android emulator using the IP `10.0.2.2`. This allows the emulated Android device to reach the host machine's localhost.

If running on a physical device, update the `BASE_URL` in `NetworkModule.kt` to your machine's IP address:

```kotlin
private const val BASE_URL = "http://YOUR_IP_ADDRESS:3000/"
```

## Architecture Highlights

### MVVM Pattern
- **Model**: Data classes in `data/model/`
- **View**: Composable functions in `ui/screen/`
- **ViewModel**: `AuthViewModel` managing UI state and business logic

### Repository Pattern
- `AuthRepository` abstracts API calls from the ViewModel
- Handles error cases and network exceptions
- Returns Kotlin `Result` objects for clean error handling

### Modern Android Development
- **Jetpack Compose**: Declarative UI toolkit
- **Kotlin DSL**: Build scripts written in Kotlin
- **Version Catalog**: Centralized dependency management
- **Material3**: Latest Material Design system

## Usage

1. Start your Node.js backend server on port 3000
2. Build and run the Android app
3. Use the login screen to authenticate with existing credentials
4. Or use the register screen to create a new account
5. Upon successful authentication, view the JWT token on the success screen

## Error Handling

The app includes comprehensive error handling:
- Network connectivity issues
- Invalid credentials
- Server errors
- Malformed responses

All errors are displayed to the user with appropriate messaging.

## Security Notes

- Uses HTTPS in production (update BASE_URL)
- JWT tokens are handled securely in memory
- No sensitive data is persisted locally
- Network traffic is logged only in debug builds
- **Password Security**: Passwords are hashed using bcrypt with a cost factor of 12 before sending to the backend API
- **Frontend Consistency**: Uses the same bcrypt hashing approach as the Next.js frontend for consistency
