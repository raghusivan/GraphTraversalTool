# RESTful API Endpoint Reference

This document outlines a set of common RESTful API endpoints typically found in web services. Each section includes example requests and notes about response status codes and payloads. These examples assume a JSON-based API following HTTP conventions.

## User Management Endpoints

| Method | Endpoint            | Description                         |
|-------|--------------------|-------------------------------------|
| GET   | `/users`           | Retrieve a list of all users.       |
| GET   | `/users/{id}`      | Retrieve a specific user by ID.     |
| POST  | `/users`           | Create a new user. Expects user data in the request body. |
| PUT   | `/users/{id}`      | Replace an existing user with the data provided. |
| PATCH | `/users/{id}`      | Update partial information for an existing user. |
| DELETE| `/users/{id}`      | Remove a user by ID.                |

### Example: Create User
```http
POST /users HTTP/1.1
Content-Type: application/json
{
  "name": "Alice",
  "email": "alice@example.com"
}
```

Successful creation should return status `201 Created` and include a `Location` header with the new resource's URL.

## Authentication Endpoints

| Method | Endpoint      | Description                                   |
|-------|---------------|-----------------------------------------------|
| POST  | `/login`      | Authenticate and receive a token.             |
| POST  | `/logout`     | Invalidate the current authentication token.  |
| POST  | `/register`   | Register a new account.                        |

### Example: Login
```http
POST /login HTTP/1.1
Content-Type: application/json
{
  "username": "alice",
  "password": "secret"
}
```

A successful login typically returns a JSON payload with a JWT or session token.

## Product Catalog Endpoints

| Method | Endpoint              | Description                                       |
|-------|----------------------|---------------------------------------------------|
| GET   | `/products`          | Retrieve all products with optional filtering.     |
| GET   | `/products/{id}`     | Retrieve a specific product by ID.                |
| POST  | `/products`          | Add a new product.                                |
| PUT   | `/products/{id}`     | Replace a product entirely.                       |
| PATCH | `/products/{id}`     | Update product details.                           |
| DELETE| `/products/{id}`     | Remove a product.                                 |

## Order Processing Endpoints

| Method | Endpoint               | Description                                         |
|-------|-----------------------|-----------------------------------------------------|
| GET   | `/orders`             | Get a list of all orders.                            |
| GET   | `/orders/{id}`        | Retrieve a specific order by ID.                     |
| POST  | `/orders`             | Create a new order.                                  |
| PUT   | `/orders/{id}`        | Update an order by replacing it.                     |
| DELETE| `/orders/{id}`        | Cancel or remove an order.                           |

### Example: Get Orders with Query Parameters
```http
GET /orders?status=OPEN&page=0&size=20 HTTP/1.1
```

Query parameters allow pagination and filtering of results.

## Error Handling

APIs typically return informative error messages with appropriate status codes. For example, attempting to fetch a non-existent resource should return `404 Not Found` along with a JSON body explaining the error.

```json
{
  "timestamp": "2024-01-01T12:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "User with id 42 does not exist",
  "path": "/users/42"
}
```

## Versioning Strategy

A simple approach to versioning is to include the version in the URL, such as `/v1/users`. Alternatives include using a request header like `Accept: application/vnd.example.v1+json`.

## Pagination Conventions

Common pagination patterns involve `page` and `size` query parameters, or using `limit` and `offset`. APIs may also return pagination metadata (total items, current page) to help clients navigate large datasets.

## Best Practices

- Use nouns for endpoints (e.g., `/users`) and plural forms for collections.
- Support standard HTTP methods and status codes.
- Document authentication and authorization requirements clearly.
- Prefer JSON for request and response bodies.
- Provide consistent error responses.

