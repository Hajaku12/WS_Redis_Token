# Interactive-Board-WS-REDIS-TOKEN

## Overview

This app allows clients and a server to communicate in real-time using WebSockets. Clients can draw, and their drawings are shown differently from others. The backend is built with Spring Boot, and the frontend uses ReactJS. Redis helps manage sessions and save the app's state.

## Features

- **Real-time Drawing:** Multiple users can draw at the same time, and everyone sees the updates instantly.
- **Unique Client Strokes:** Each user's drawings are shown in different styles or colors.
- **Responsive UI:** The ReactJS frontend offers an easy-to-use and responsive drawing interface.
- **Session Management:** Redis is used to keep sessions persistent, making the solution stateless.

## Technologies Used

- **Backend:** Spring Boot, WebSocket API, Redis
- **Frontend:** ReactJS, WebSocket client
- **Communication Protocol:** WebSocket for real-time bidirectional communication

## Deployment

[http://ec2-54-209-55-85.compute-1.amazonaws.com:8080/](http://ec2-54-90-71-142.compute-1.amazonaws.com:8080/)

## Architecture

### Architecture 

This project uses ReactJS for the frontend and Spring Boot for the backend to create a real-time drawing app. Users can draw together in real-time with a dynamic interface powered by ReactJS, and P5.js handles the drawing canvas. Spring Boot manages WebSocket connections for instant updates between users. Redis stores session data to keep the app stateless and scalable. AWS provides hosting and storage to keep the app fast and reliable, even with many users. These technologies work together to let users draw collaboratively, with clear visualization of each user's contributions, enhancing the interactive experience.

## Redis Integration

To improve session management and keep the app's state, Redis is used in the backend. Redis stores session data, making the app stateless and scalable. This allows the app to maintain session data across multiple instances, enhancing reliability and performance.

## Web Services

The backend also offers RESTful web services for extra features like ticket generation and validation. These services ensure secure interactions between the client and server, allowing only authenticated users to perform certain actions. RESTful web services work alongside WebSocket-based real-time communication to provide a robust and scalable architecture for the app.


## Getting Started

To run this application locally, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone REPOSITORY_LINK
   cd repository-folder
   ```

2. **Backend Setup:**
    - Navigate to the `backend` directory.
    - Build the project using Maven:
      ```bash
      mvn clean package
      ```
    - Run the Spring Boot application:
      ```bash
      java -jar target/backend.jar
      ```

3. **Frontend Setup:**
    - Navigate to the `frontend` directory.
    - Install dependencies:
      ```bash
      npm install
      ```
    - Start the React application:
      ```bash
      npm start
      ```

4. **Accessing the Application:**
    - Open a web browser and go to `http://localhost:8080` to view and interact with the drawing application.
