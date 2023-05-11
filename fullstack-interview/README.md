# Tasks

1. Run Backend

Follow the backend's readme to run the backend and ensure it's running

2. Run the Frontend

Follow the frontend's readme to run it and ensure it's running

3. Add some Data

Using swagger, (http://localhost:8080/swagger-ui/index.html, add some data to the H2 database with the `Try it out` feature on the `add` endpoint.

Swagger should return `200 OK` and the new person object every time you `Execute` the request

You can then use the `all` endpoint to view all the data you have added

4. Test Frontend

Navigate top the default frontned page http://localhost:3000/ and ensure the same data that you can see in swagger shows up in the frontend

5. Complete the Frontend's Add View

Just as the ListView calls the backend's `/all` endpoint using axios, complete the AddView component by using axios to call the `/add` endpoint then return to the list view.

6. Add a Frontend test

Add a test to ensure the list is not empty. You can modify the ListView as needed.

7. Extend the Backend (1)

Add endpoints to the backend that modifies and deletes Person entities.

8. Add tests to the Backend (1)

Add unit tests to the backend to ensure entities are being added, editted, and deleted. Add as many as you feel is necessary.

9. Extend the Frontend (1)

Add the functionality to the frontend to Edit and Delete from the List of People.

10. Extend the Backend (2)

Add an endpoint to the backend that searches for People by firstname

11. Add tests to the Backend (2)

Add a unit test that tests the new search functionality

12. Extend the frontend (2)

Extend the ListView to use the new firstname filter (implement it however you like)