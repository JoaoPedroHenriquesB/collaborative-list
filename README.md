# Collaborative List API

A collaborative list application using Spring Boot. This application allows users to create rooms, and within these rooms, create and manage shared lists.

## Technologies Used

*   **Java 21**
*   **Spring Boot 3.5.5**
    *   Spring Web
    *   Spring Data JPA
    *   Spring Security
    *   Spring Boot DevTools
*   **MySQL**
*   **Lombok**
*   **Maven**

## How to Run

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/collaborative-list.git
    ```
2.  **Configure the database:**
    *   Open `src/main/resources/application.properties`.
    *   Set the `DB_URL`, `DB_USER`, and `DB_PASSWORD` environment variables to your MySQL database credentials.
3.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```
    The application will be available at `http://localhost:8080`.

## API Endpoints

### Authentication

*   **Register a new user:**
    *   `POST /register`
    *   **Request Body:**
        ```json
        {
            "username": "your-username",
            "password": "your-password"
        }
        ```
*   **Login:**
    *   `POST /login`
    *   **Request Body:**
        ```json
        {
            "username": "your-username",
            "password": "your-password"
        }
        ```

### Rooms

*   **Create a new room:**
    *   `POST /rooms/create`
    *   **Request Body:**
        ```json
        {
            "name": "My Room"
        }
        ```
*   **Delete a room:**
    *   `DELETE /rooms/{roomId}/delete`
*   **Get all rooms:**
    *   `GET /rooms`
*   **Join a room:**
    *   `POST /rooms/join`
    *   **Request Body:**
        ```json
        {
            "roomCode": "your-room-code"
        }
        ```
*   **Get room code:**
    *   `GET /rooms/{roomId}/code`
*   **Get room by ID:**
    *   `GET /rooms/{roomId}`

### Lists

*   **Get lists by room:**
    *   `GET /lists/{roomId}`
*   **Create a new list in a room:**
    *   `POST /lists/{roomId}/create`
    *   **Request Body:**
        ```json
        {
            "name": "My List"
        }
        ```
*   **Get list by ID:**
    *   `GET /lists/id/{listId}`

### Items

*   **Create a new item in a list:**
    *   `POST /items/{roomId}/{listId}/create`
    *   **Request Body:**
        ```json
        {
            "name": "My Item"
        }
        ```
*   **Get items by list ID:**
    *   `GET /items/{listId}`
*   **Get item by ID:**
    *   `GET /items/id/{itemId}`
*   **Delete an item:**
    *   `DELETE /items/{itemId}`
