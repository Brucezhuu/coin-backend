# Dropwizard Backend for Coin Calculator
## Introduction
This is the backend service for the Coin Calculator application. It is built using Dropwizard, a lightweight Java framework for building REST APIs. The backend exposes an API that calculates the minimum number of coins needed to achieve a target amount using specified denominations.

The backend uses a Dropwizard application with REST resources for API endpoints, leveraging Maven for build and dependency management.

Deployment was done on AWS EC2, where the backend listens on port 8080 and the frontend on port 80.

My Application Website: http://3.27.234.183/
    
## How to start the application locally

1. Run `mvn clean install` to build your application
2. Run `mvn clean package exec:java` or  `java -jar target/hola-dropwizard-1.0.jar server config.yml` to start backend application
3. To check that your application is running enter url `http://localhost:8080`
## Health Check
To see your applications health enter url `http://localhost:8081/healthcheck`

## API Endpoint
### Compute Minimum Coins
- Endpoint: POST /api/compute
- Request Body:
```json

{
"targetAmount": 7.03,
"coinDenominations": [0.01, 0.5, 1, 5, 10]
}
```
Response Body:
```json
[5.0, 1.0, 1.0, 0.01, 0.01, 0.01]
```

## Step to Build and Run Docker Container
1. Build the Docker Image
   Navigate to the backend project directory and run the following command:

```bash
docker build -t hola-dropwizard-backend .
```

2. Run the Docker Container
   Start the container by running:

```bash
docker run -p 8080:8080 hola-dropwizard-backend
```

3. Test the Backend Service
   Use curl or Postman to test the API endpoint. For example:

``` bash

curl -X POST http://localhost:8080/api/compute \
-H "Content-Type: application/json" \
-d '{
"targetAmount": 7.03,
"coinDenominations": [0.01, 0.5, 1, 5, 10]
}'
```
Expected Response:

```json
[5.0, 1.0, 1.0, 0.01, 0.01, 0.01]
```

## Using Docker Compose to Run Frontend and Backend
You can use docker-compose to run both the frontend and backend services together. Here is how:

1. Create a docker-compose.yml File
   In the root directory of your project (outside both the frontend and backend folders), create a docker-compose.yml file with the following content:

```yaml
version: "3.8"
services:
  backend:
    build:
      context: ./hola-dropwizard
    ports:
      - "8080:8080"

  frontend:
    build:
      context: ./coinfrontend
    ports:
      - "80:80"
```
2. Directory Structure
   Ensure your project directory is structured as follows:
```lua
project-root/
├── docker-compose.yml
├── hola-dropwizard/
│   ├── Dockerfile
│   ├── config.yml
│   └── ... (backend files)
├── coinfrontend/
│   ├── Dockerfile
│   ├── package.json
│   └── ... (frontend files)
```
3. Start Both Services
   Run the following command in the root directory where docker-compose.yml is located:
```bash
docker-compose up --build
```

4. Access the Services

- Frontend: Open your browser and go to http://<EC2 Public IP> (or http://localhost if running locally).

- Backend: The backend will be accessible at http://<EC2 Public IP>:8080/api/compute.
