# Event Ticketing System

## Description

The Event Ticketing System is a Java-based application developed using Java 17 and Spring Boot. It provides a platform for managing events, ticket booking, user authentication, and page management functionalities.

### Key Features

1. **User Controller:** The User Controller handles user authentication and authorization. Users can log in with either USER or ADMIN roles, each having specific access rights within the system.

2. **Event Controller:** The Event Controller manages events within the system. It allows for the creation, deletion, updating, and retrieval of events. This feature provides administrators with full control over the event management process.

3. **JWT Security:** The application implements JWT (JSON Web Token) security for authentication and authorization. This ensures secure access to the system's resources and protects against unauthorized access.

4. **Ticket Booking System:** The system includes a sophisticated ticket booking system. Users can book tickets for events, and the system manages available seats and generates tickets accordingly. This feature enhances the overall user experience and simplifies the ticket booking process.

5. **Page Management:** Administrators have the ability to manage page content dynamically. They can control what information is displayed on various pages of the system, providing flexibility and customization options.

### Swagger Implementation

In addition to its core features, the Event Ticketing System also integrates Swagger for API documentation and testing. Swagger provides a user-friendly interface for exploring the application's RESTful APIs, making it easier for developers to understand and interact with the system.

#### Key Points:

- **Swagger UI Integration:** The application incorporates Swagger UI, a web-based interface that dynamically generates documentation for the RESTful APIs. Users can access the Swagger UI to view details about each API endpoint, including its parameters, responses, and usage examples.

- **API Documentation:** Swagger automatically generates comprehensive documentation for all the API endpoints exposed by the Event Ticketing System. This documentation includes information about request and response formats, HTTP methods, authentication requirements, and more.

- **Interactive API Testing:** With Swagger, developers can interactively test the API endpoints directly from the Swagger UI. This allows for real-time experimentation with different request payloads and parameters, facilitating the development and debugging process.

#### How to Access Swagger:

To access the Swagger documentation and testing interface:

1. Start the Event Ticketing System application locally as described in the "How to Run" section.

2. Once the application is running, navigate to the Swagger UI endpoint in your web browser. Typically, this endpoint is located at `http://localhost:8080/swagger-ui.html`.

3. You will be presented with the Swagger UI interface, where you can explore the available API endpoints, view their documentation, and perform test requests.

#### Benefits of Swagger Integration:

- **Improved Developer Experience:** Swagger enhances the developer experience by providing clear and concise documentation for the API endpoints. Developers can quickly understand how to interact with the system's APIs without the need for extensive manual documentation.

- **API Consistency:** By automatically generating documentation from the codebase, Swagger helps ensure consistency between the implemented APIs and their documented specifications. This reduces the risk of discrepancies and misunderstandings during development.

- **Facilitates Collaboration:** Swagger serves as a central hub for API documentation and testing, making it easier for teams to collaborate on API-related tasks. Developers, testers, and other stakeholders can access and interact with the APIs in a unified environment.

## How to Run

To run the Event Ticketing System locally, follow these steps:

1. Ensure you have Java 17 installed on your system.
2. Clone the repository to your local machine.
3. Navigate to the project directory.
4. Build the project using Maven: `mvn clean package`.
5. Run the application: `java -jar target/event-ticketing-system.jar`.
6. Access the application in your web browser at `http://localhost:8080`.
