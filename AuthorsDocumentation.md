# Author Controller API Documentation

## Get all authors

- Route: `http://localhost:8080/api/authors`
- Method: GET
- Response:
  - Code: 200
  - Body:
    ```json
    {
      "message": "Success",
      "status": true,
      "data": [
        {
          "id": 1,
          "authorBio": "new bio",
          "authorName": "new name",
          "authorEmail": "new_email@example.com"
        },
        {
          "id": 2,
          "authorBio": "new bio2",
          "authorName": "new name2",
          "authorEmail": "new_email@example.com2"
        }
      ]
    }
    ```
- If no authors are found:
  - Code: 204
  - Body: No body

---

## Get one author by ID

- Route: `http://localhost:8080/api/authors/1` (replace `1` with the desired author ID)
- Method: GET
- Response:
  - Code: 200
  - Body:
    ```json
    {
      "message": "Getting author successfully",
      "status": true,
      "data": {
        "id": 1,
        "authorBio": "new bio",
        "authorEmail": "new_email@example.com",
        "authorName": "new name"
      }
    }
    ```
- If no author is found for the provided ID:
  - Code: 204
  - Body: No body

---

## Create author

- Route: `http://localhost:8080/api/authors`
- Method: POST
- Request body:
  ```json
  {
    "author_name": "new name",
    "author_email": "new_email@example.com",
    "author_bio": "new bio"
  }
  ```
- Response:
  - If any of the fields [name, email, bio] is empty or null, or if the email is not in the correct format:
    - Code: 400
    - Body:
      ```json
      {
        "message": "Validation Error",
        "status": false,
        "data": null
      }
      ```
  - If the author is created successfully:
    - Code: 201
    - Body:
      ```json
      {
        "message": "Author Created Successfully",
        "status": true,
        "data": {
          "id": 7,
          "authorBio": "new bio",
          "authorEmail": "new_email@example.com",
          "authorName": "new name"
        }
      }
      ```

---

## Update author by ID

- Route: `http://localhost:8080/api/authors/1` (replace `1` with the desired author ID)
- Method: PUT
- Request body:
  ```json
  {
    "author_name": "new name",
    "author_email": "new_email@example.com",
    "author_bio": "new bio"
  }
  ```
- Response:
  - If no author is found for the provided ID:
    - Code: 204
    - Body: No body
  - If any of the fields [name, email, bio] is empty or null, or if the email is not in the correct format:
    - Code: 400
    - Body:
      ```json
      {
        "message": "Validation Error",
        "status": false,
        "data": null
      }
      ```
  - If the author is updated successfully:
    - Code: 201
    - Body:
      ```json
      {
        "message": "Author Updated Successfully",
        "status": true,
        "data": {
          "id": 1,
          "authorBio": "new bio",
          "authorEmail": "new_email@example.com",
          "authorName": "new name"
        }
      }
      ```
