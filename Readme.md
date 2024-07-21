# Jetchanger - Currency Converter App

This is a simple Android application built with Kotlin and Jetpack Compose that allows users to convert currencies using the Open Exchange Rates API. The app uses Retrofit for network requests, Gson for JSON parsing, and Coroutines for asynchronous operations.

## Features

- Convert between various currencies
- Real-time exchange rates using Open Exchange Rates API
- User-friendly interface built with Jetpack Compose
- Uses Coroutines for efficient and reactive UI updates
- Custom deserializers in Gson to handle complex API responses

## Libraries Used

- [Retrofit](https://square.github.io/retrofit/): A type-safe HTTP client for Android and Java.
- [Gson](https://github.com/google/gson): A Java library that can be used to convert Java Objects into their JSON representation.
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html): For asynchronous and more efficient code execution on Android.
- [Jetpack Compose](https://developer.android.com/jetpack/compose): Modern toolkit for building native UI.

## API Used

- [Open Exchange Rates API](https://openexchangerates.org/): Provides real-time exchange rates for over 160 currencies.

## Custom Deserializers in Gson

The Open Exchange Rates API returns a JSON object with dynamic keys (currency codes) and values (exchange rates). To handle this, custom deserializers are used in Gson. This allows us to convert the JSON response into a more manageable data structure in our Kotlin code.

## How to Use

1. Clone the repository.
2. Add your Open Exchange Rates API key to the `local.properties` file.(for now it's pasted from test)
3. Build and run the project on an Android emulator or physical device.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
