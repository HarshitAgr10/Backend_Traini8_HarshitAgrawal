# Training Center Registration API

This project provides a RESTful API for registering training centers. It includes features for
validating input data, handling exceptions, and managing training center information.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [API Endpoints](#api-endpoints)
- [Error Handling](#error-handling)

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL Database
- Lombok
- Maven

## Setup Instructions

Follow these steps to set up the project locally:

1. Clone the Repository

       git clone <repository-url>
       cd <repository-directory>

2. Install Dependencies
   Make sure you have Maven installed. Install the project dependencies by running:

        mvn install


3. Configure MySQL Database
    Ensure that you have MySQL installed and running.
    Create a new database (e.g., trainingcenter):

        CREATE DATABASE training_center;


    Update the src/main/resources/application.properties file with your MySQL
    database configuration:

        spring.jpa.hibernate.ddl-auto=update
        spring.datasource.url=jdbc:mysql://localhost:3306/training_center
        spring.datasource.username=<your-username>
        spring.datasource.password=<your-password>
        spring.jpa.show-sql=true


4. Access the API: The APIs will be available at http://localhost:8080/api.

    API Endpoints
    1. Register Training Center
        Endpoint: POST /api/trainingcenters

        Request Body:

            {
                "centerName": "Example Center",
                "centerCode": "CENTER123456",
                "address": {
                    "detailedAddress": "123 Main St",
                    "city": "Bangalore",
                    "state": "Karnataka",
                    "pinCode": "560001"
                },
                "studentCapacity": 50,
                "coursesOffered": ["Java", "Python"],
                "contactEmail": "contact@example.com",
                "contactPhone": "9876543210"
            }

        Responses:
            200 OK: Training center registered successfully.
            400 Bad Request: Validation errors.
            500 Internal Server Error: Duplicate center code.

    2. Get Registered Training Centers
        Endpoint: GET /api/trainingcenters

        Query Parameters:
            city (optional): Filter by city.
            state (optional): Filter by state.

        Responses:
            200 OK: List of training centers.


5. Error Handling
    The API uses a global exception handler to manage errors and provides meaningful error
    messages in the response.

    Example Error Response

        {
            "message": "CenterCode is required",
            "statusCode": 400
        }
