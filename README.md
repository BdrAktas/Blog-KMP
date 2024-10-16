# Multi-Platform Blog Project

## Introduction

This project encompasses a comprehensive blogging ecosystem, featuring a responsive Blog Website, an Admin Website for content management, and an Android application. The project leverages modern web and mobile technologies to create a seamless user experience across all platforms.

## Project Components

1. **Blog Website**: A responsive frontend for blog readers
2. **Admin Website**: A backend interface for content management
3. **Android Application**: A native mobile app for on-the-go access

## Technology Stack

### Frontend
- **Kobweb**: A Kotlin library utilizing Compose HTML for building reactive web applications
- **Compose HTML**: A declarative UI framework for web development

### Backend
- **Kobweb API**: A server-side framework built on top of Ktor
- **Ktor**: A lightweight, asynchronous web framework for Kotlin

### Database
- **MongoDB**: A NoSQL database (Note: Currently using the deprecated KMongo library; migration pending due to MongoDB discontinuing Realtime Device Sync support)

### Hosting
- **Render**: Cloud application hosting platform

### Android
- **Jetpack Compose**: Modern toolkit for building native Android UI
- **Material 3**: Latest Material Design component library

## Project Structure

The project is structured into three main modules:

1. `jsMain`: Contains the frontend code for the Blog and Admin websites
2. `jvmMain`: Houses the backend code for server-side operations
3. `android`: The Android application module (to be added later)

## Setup and Running

### Prerequisites
- Kotlin SDK
- MongoDB
- Android SDK (for the mobile app)


### Running the Kobweb Server

To start the Kobweb server locally:

```bash
kobweb run
```

This command will compile your Kotlin code and start a local development server.

### MongoDB Setup

Before running the application, ensure MongoDB is running. Start the MongoDB server with:

```bash
mongod --dbpath ~/mongodb-data
```

Replace `~/mongodb-data` with your preferred data directory path.

## Development Workflow

1. Frontend development occurs in the `jsMain` directory
2. Backend logic is implemented in the `jvmMain` directory
3. Android-specific code will be added to the `android` module

### Git Flow Strategy

This project utilizes the Git Flow branching model to manage development efficiently. Git Flow provides a robust framework for handling features, releases, and hotfixes, making it ideal for the structured release cycle. I chose Git Flow over GitHub Flow due to its support for parallel development and dedicated release preparation. For detailed information about the Git Flow implementation, please refer to the [Git Flow documentation](docs/git_flow.md).

### Commit Message Standards

I adhere to strict commit message standards to maintain a clear and informative Git history. My commit messages follow a specific format, enforced by a `commit-msg` Git hook. This approach ensures consistency across the project, facilitating easier code reviews and automated changelog generation. For a comprehensive guide on commit message format and examples, please see the [Commit Standards documentation](docs/commit_standard.md).

## Building and Deployment

Kobweb compiles the Kotlin code into HTML, CSS, and JavaScript for web deployment. The resulting static files can be hosted on any web server or cloud platform like Render.

## Future Improvements

- Migrate from KMongo to a supported MongoDB Kotlin driver
- Implement real-time synchronization alternative for the mobile app
- Enhance responsive design for various screen sizes and devices

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on the code of conduct and the process for submitting pull requests.

## License

This project is licensed under the [MIT License](LICENSE).


