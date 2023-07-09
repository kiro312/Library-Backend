# Author Controller API Documentation

## Get all books

- Route: `http://localhost:8080/api/books`
- Method: GET
- Response:
    - Code: 200
    - Body:
        ```json
        {
            "message": "Getting all books successfully",
            "status": true,
            "data": [
                {
                    "id": 1,
                    "bookAuthor": {
                        "id": 1,
                        "authorName": "new name",
                        "authorEmail": "new_email@example.com",
                        "authorBio": "new bio"
                    },
                    "bookDescription": "book title",
                    "bookImage": "image_link",
                    "bookTitle": "book title"
                },
                {
                    "id": 2,
                    "bookAuthor": {
                        "id": 1,
                        "authorName": "new name",
                        "authorEmail": "new_email@example.com",
                        "authorBio": "new bio"
                    },
                    "bookDescription": "new book description",
                    "bookImage": "image_link",
                    "bookTitle": "new book title"
                }
            ]
        }
        ```
- If no books are found:
    - Code: 204
    - Body: No body

---

## Get one book by ID

- Route: `http://localhost:8080/api/books/1` (replace `1` with the desired book ID)
- Method: GET
- Response:
    - Code: 200
    - Body:
        ```json
        {
            "message": "Getting book successfully",
            "status": true,
            "data": {
                "id": 1,
                "bookAuthor": {
                    "id": 1,
                    "authorName": "new name",
                    "authorEmail": "new_email@example.com",
                    "authorBio": "new bio"
                },
                "bookDescription": "book title",
                "bookImage": "image_link",
                "bookTitle": "book title"
            }
        }
        ```
- If no book is found for the provided ID:
    - Code: 204
    - Body: No body

---

## Create book

- Route: `http://localhost:8080/api/books`
- Method: POST
- Request body:
    ```json
    {
        "book_title": "book title",
        "book_description": "book title",
        "book_image": "image_link",
        "author_id": "1"
    }
    ```
- Response:
    - If any of the fields [title, description, book_image, author_id] is empty, null, or if the author_id does not exist in the authors:
        - Code: 400
        - Body:
            ```json
            {
                "message": "Validation Error",
                "status": false,
                "data": null
            }
            ```
    - If the book is created successfully:
        - Code: 201
        - Body:
            ```json
            {
                "message": "Book created successfully",
                "status": true,
                "data": {
                    "id": 1,
                    "bookAuthor": {
                        "id": 1,
                        "authorName": "new name",
                        "authorEmail": "new_email@example.com",
                        "authorBio": "new bio"
                    },
                    "bookDescription": "book title",
                    "bookImage": "image_link",
                    "bookTitle": "book title"
                }
            }
            ```

---

## Update book by ID

- Route: `http://localhost:8080/api/books/1` (replace `1` with the desired book ID)
- Method: PUT
- Request body:
    ```json
    {
        "book_title": "new book title",
        "book_description": "new book description",
        "book_image": "new_image_link",
        "author_id": "1"
    }
    ```
- Response:
    - If no book is found for the provided ID:
        - Code: 204
        - Body: No body
    - If any of the fields [title, description, book_image, author_id] is empty, null, or if the email is not in the correct format:
        - Code: 400
        - Body:
            ```json
            {
                "message": "Validation Error",
                "status": false,
                "data": null
            }
            ```
    - If the book is updated successfully:
        - Code: 201
        - Body:
            ```json
            {
                "message": "Book updated successfully",
                "status": true,
                "data": {
                    "id": 1,
                    "bookAuthor": {
                        "id": 1,
                        "authorName": "new name",
                        "authorEmail": "new_email@example.com",
                        "authorBio": "new bio"
                    },
                    "bookDescription": "new book description",
                    "bookImage": "new_image_link",
                    "bookTitle": "new book title"
                }
            }
            ```

