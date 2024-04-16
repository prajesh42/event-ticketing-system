# Event Ticketing System

## Description

The Event Ticketing System is a Java-based application developed using Java 17 and Spring Boot. It provides a platform for managing events, ticket booking, user authentication, and page management functionalities.

### Key Features

1. **User Controller:** The User Controller handles user authentication and authorization. Users can log in with either USER or ADMIN roles, each having specific access rights within the system.

2. **Event Controller:** The Event Controller manages events within the system. It allows for the creation, deletion, updating, and retrieval of events. This feature provides administrators with full control over the event management process.

3. **JWT Security:** The application implements JWT (JSON Web Token) security for authentication and authorization. This ensures secure access to the system's resources and protects against unauthorized access.

4. **Ticket Booking System:** The system includes a sophisticated ticket booking system. Users can book tickets for events, and the system manages available seats and generates tickets accordingly. This feature enhances the overall user experience and simplifies the ticket booking process.

5. **Page Management:** Administrators have the ability to manage page content dynamically. They can control what information is displayed on various pages of the system, providing flexibility and customization options.

## How to Run

To run the Event Ticketing System locally, follow these steps:

1. Ensure you have Java 17 installed on your system.
2. Clone the repository to your local machine.
3. Navigate to the project directory.
4. Build the project using Maven: `mvn clean package`.
5. Run the application: `java -jar target/event-ticketing-system.jar`.
6. Access the application in your web browser at `http://localhost:8080`.
