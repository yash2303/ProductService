# Product Management Application

This is a Spring Boot application for managing products and categories. It provides RESTful APIs to perform CRUD operations on products and categories. The application uses both a local database and an external API (FakeStore API) to fetch and manage product data.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Redis
- Maven
- RESTful APIs

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Redis

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yash2303/ProductService.git
    cd product-management
    ```

2. Install the dependencies:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Configuration

### Application Properties

Configure the application properties in `src/main/resources/application.properties` as needed.

### Security Configuration

The security configuration is defined in `src/main/java/com/yashasvi/product/security/SecurityConfiguration.java`. By default, all endpoints are accessible without authentication.

## API Endpoints

### Product Endpoints

- **GET /products**: Get all products with pagination and sorting.
- **GET /products/{id}**: Get a single product by ID.
- **POST /products**: Add a new product.
- **PATCH /products/{id}**: Update an existing product.
- **PUT /products/{id}**: Replace an existing product.
- **DELETE /products/{id}**: Delete a product by ID.

### Category Endpoints

- **GET /categories**: Get all categories.
- **GET /categories/{name}/products**: Get all products in a specific category.

## Exception Handling

Custom exception handling is implemented in `src/main/java/com/yashasvi/product/controlleradvice/ExceptionHandlers.java`.

## Services

### Product Services

- **SelfProductService**: Manages products using the local database.
- **FakeStoreProductService**: Manages products using the FakeStore API.

### Category Services

- **SelfCategoryService**: Manages categories using the local database.
- **FakeStoreCategoryService**: Manages categories using the FakeStore API.

## Running Tests

To run the tests, use the following command:
```sh
mvn test
```

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
