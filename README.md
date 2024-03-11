## Getting Started

1. GET /project
2. GET /project/{id}
3. POST /project
4. PUT /project/{id}
5. DELETE /project/{id}

## API Documentation

    Open your browser and go to "http://localhost:8080/swagger-ui/index.html"
    to view and interact with the API documentation.

## Demo Data

    For testing purposes, you can use the following demo data:

    {
      "id": 1,
       "name": "Sample Project",
       "description": "This is a sample project.",
        "startDate": "2022-01-01",
         "endDate": "2022-12-31"
     }


## API Usage

    Get All Projects:
Endpoint: GET /project
Retrieve a list of all projects.

    Get Project by ID:
Endpoint: GET /project/{id}
Retrieve details of a project by its unique ID.

    Create a New Project:
Endpoint: POST /project
Create a new project by providing project details in the request body.
 
     Update an Existing Project:
Endpoint: PUT /project/{id}
Update an existing project with the provided details.

     Delete a Project:
Endpoint: DELETE /project/{id}
Delete a project based on its unique ID.