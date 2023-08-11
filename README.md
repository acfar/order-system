# order-system service

## Stack
- Java 1.8
- PostgreSQL

## Library
- Spring boot starter
- Lombok
- JPA
- PostgreSQL Connection
- Flyway
- Junit

### Current releases
| Version    | Link                   |
| ---------- |------------------------|
| Local      | http://localhost:8080/ |


## Running this application
- Run following commands
    ```
    # Create db connection in PostgreSQL
    - Open application.properties
    - adjust host, port, dbName, user, password as is on your local

    # Make sure Table already exist
    - Table will be generated automatically using flyway db migration
    - Initial Data is also will be injected into table
  
    # Running the apps
    - Running application through OrderApplication.class
    - To Look Documentation API could be through swagger via http://localhost:8080/swagger-ui.html
    ```

