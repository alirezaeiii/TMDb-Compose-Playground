# TMDb-Compose
### 🚧 Work in progress 👷‍♀️⛏👷🔧️👷🔧 🚧

<a href="https://play.google.com/store/apps/details?id=com.sample.tmdb"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" height=70px /></a>

## Screenshots
<p float="left">
  <img src="https://github.com/alirezaeiii/TMDb-Compose/blob/main/screenshots/screenshot1.gif" width="250" />
  <img src="https://github.com/alirezaeiii/TMDb-Compose/blob/main/screenshots/screenshot2.gif" width="250" />
  <img src="https://github.com/alirezaeiii/TMDb-Compose/blob/main/screenshots/screenshot3.gif" width="250" />
  <img src="https://github.com/alirezaeiii/TMDb-Compose/blob/main/screenshots/screenshot4.gif" width="250" />
  <img src="https://github.com/alirezaeiii/TMDb-Compose/blob/main/screenshots/screenshot5.gif" width="250" />
</p>

## Features
* MVVM Architecture + Repository design Pattern.
* Jetpack Libraries and Architecture Components.

## Prerequisites

Add your [TMDB](https://www.themoviedb.org/) API key in the `local.properties` file:
```
tmdb_api_key=YOUR_API_KEY
```

### Inspired from
| [Jetsnack](https://github.com/android/compose-samples/tree/main/Jetsnack) | [Jetflix](https://github.com/yasinkacmaz/jetflix) 
| :-: | :-: |

## Libraries
* [Android Jetpack](https://developer.android.com/jetpack)
   * [Compose](https://developer.android.com/jetpack/compose) Android’s modern toolkit for building native UI.
   * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) ViewModel is designed to store and manage UI-related data in a lifecycle conscious way. This allows data to survive configuration changes such as screen rotations.
   * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
   * [Navigation-Compose](https://developer.android.com/jetpack/compose/navigation/) The Navigation component provides support for Jetpack Compose applications. You can navigate between composables while taking advantage of the Navigation component’s infrastructure and features.
   * [Paging-Compose](https://developer.android.com/jetpack/androidx/releases/paging) The Paging Library makes it easier for you to load data gradually and gracefully within your app.
* [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) Executing code asynchronously.
* [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) is a state-holder observable flow that emits the current and new state updates to its collectors.
* [Coil-compose](https://coil-kt.github.io/coil/compose/) An image loading library for Android backed by Kotlin Coroutines.
* [Retrofit](https://square.github.io/retrofit/) is a Type-safe HTTP client for Android, Java and Kotlin by Square.
* [Moshi](https://github.com/square/moshi) is a modern JSON library for Android and Java. It makes it easy to parse JSON format data.
* [OkHttp interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) Logs HTTP requests and responses.
* [Material Design](https://material.io/develop/android/) Build beautiful, usable products using Material Components for Android.

## Licence
    MIT License

    Copyright (c) 2022 Mohammadali Rezaei

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
