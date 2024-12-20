# Travel Agency Management System

This project is a Travel Agency Management System that allows users to:

1. Book hotel rooms.
2. View recommendations for attractions and activities in the hotel location.
3. Manage user accounts for registration and login.
4. Add and manage recommendations for hotels.

The system is built using **Java**, **Spring Boot**, and **MySQL**.

---

## Features

### 1. Hotel Management
- Add hotels with details like location and room availability.
- Book rooms (single, double, family) in hotels.

### 2. User Management
- User registration and login functionalities.
- Track user-specific bookings.

### 3. Recommendations
- Add recommendations for hotels and specific locations.
- Retrieve recommendations for a user after booking a hotel room.

### 4. Notification System
- Notify users upon successful booking.
- Include recommendations in notifications.

---

## Technologies Used

- **Spring Boot**: Framework for building the backend.
- **MySQL**: Database to store user, hotel, and recommendation data.
- **Postman**: API testing and integration.
- **Hibernate/JPA**: ORM framework for database interaction.
- **Lombok**: Simplifies Java object generation.

---

## Project Structure

### Directories and Packages

- `controller`: Contains API controllers for user, hotel, and recommendation management.
- `model`: Defines the entity classes for the database (User, Hotel, Recommendation).
- `repository`: Interfaces for data access using JPA.
- `service`: Business logic for handling user, hotel, and recommendation functionalities.

---

## Prerequisites

1. **Java 17+** installed.
2. **Maven** for dependency management.
3. **MySQL** installed and configured.
4. **Postman** for API testing.

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
cd travel-agency-management
```

### 2. Configure Database
Update the `application.properties` file in the `src/main/resources` directory with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/travel_agency_db
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the Application
Use the following command to build and run the project:
```bash
mvn spring-boot:run
```

The server will start on `http://localhost:8080`.

---

## API Endpoints

### User Endpoints
1. **Register User**
   - **POST** `/users/register`
   - **Body**:
     ```json
     {
       "username": "john_doe",
       "email": "john.doe@example.com",
       "password": "password123"
     }
     ```

2. **Login User**
   - **POST** `/users/login`
   - **Body**:
     ```json
     {
       "username": "john_doe",
       "password": "password123"
     }
     ```

### Hotel Endpoints
1. **Add Hotel**
   - **POST** `/hotels/add`
   - **Body**:
     ```json
     {
       "name": "Sunset Resort",
       "location": "Cairo",
       "singleRooms": 10,
       "doubleRooms": 5,
       "familyRooms": 3
     }
     ```

2. **Search Hotels**
   - **GET** `/hotels/search?location=Cairo`

3. **Book Room**
   - **POST** `/hotels/book/{hotelId}`
   - **Body**:
     ```json
     {
       "userId": 1,
       "roomType": "single"
     }
     ```

### Recommendation Endpoints
1. **Add Recommendation**
   - **POST** `/recommendations/add`
   - **Body**:
     ```json
     {
       "location": "Cairo",
       "details": "Visit the Pyramids of Giza!",
       "hotel": {
         "id": 1
       }
     }
     ```

2. **Generate Recommendations**
   - **POST** `/recommendations/generate`
   - **Body**:
     ```json
     {
       "userId": 1,
       "location": "Cairo"
     }
     ```

---

## Example Workflow
1. Add a hotel.
2. Add recommendations for the hotel/location.
3. Register and log in a user.
4. Book a room in the hotel.
5. Receive recommendations in the response.

---

## Testing with Postman

1. Import the provided API endpoints into Postman.
2. Use the workflow example to test the system functionality step-by-step.

---

## Future Enhancements

1. Add payment integration.
2. Implement a review system for hotels.
3. Add role-based access control (e.g., admin, user).
4. Enhance the notification system to include email and SMS.


