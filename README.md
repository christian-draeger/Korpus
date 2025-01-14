# Korpus
A Kotlin multiplatform project trying to create a smooth fullstack webapp development experience.

The Application uses Kotlin/Js on frontend and Kotlin/Jvm on backend,that uses kRPC with Ktor to communicate.

- [x] Ktor Server (Kotlin/Jvm)
    - [x] kRPC
    - [x] kotlinx-html
    - [x] kotlinwind.css
- [x] Frontend (Kotlin/Js)
    - [x] kRPC
    - [x] react
    - [x] emotion css
    - [x] kotlinx-html
    - [x] kotlinwind.css
- [x] Common code 
    - [x] kRPC
    - [x] kotlinx-html
    - [x] kotlinwind.css

### Running frontend
To run frontend in development mode, run this command:
```bash
./gradlew frontend:jsRun
```
The client webpack app will start on port 3000 and it will route API requests to http://localhost:8080

### Running server
To run server without compiling frontend, simply run `main` function in [Application.kt](/server/src/main/kotlin/codes/draeger/korpus/Application.kt) from IDEA UI.

To Run server with latest frontend use this command:
```bash
./gradlew server:runApp
```
Note that this configuration uses production distribution of frontend app, which makes each build slower, as it takes more time to compile production webpack.