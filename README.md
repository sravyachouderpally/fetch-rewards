## Steps to Run the Application

### 1. Install IntelliJ IDEA
- To install IntelliJ IDEA:
    - Go to the JetBrains website [here](https://www.jetbrains.com/idea/download/?section=windows)
    - Download the Community (free) version of IntelliJ IDEA.
    - Follow the on-screen instructions to install it on your computer.
- Reference: [Video Tutorial](https://www.youtube.com/watch?v=viNG3VVnzFE) or [Tutorial](https://www.tutorialspoint.com/step-by-step-guide-to-install-intellij-idea)

### 2. Setup JDK
- Download Oracle JDK from [Oracle's website](https://www.oracle.com/java/technologies/downloads).
- Follow the installation instructions specific to your operating system.
- Example: I downloaded Oracle JDK 21 for Windows 64-bit.

### 3. Clone the GitHub Repository
- Open your web browser and go to the GitHub repository.
- Click the "Download" button to download the project.
- Replace "//git url" with the actual Git repository URL.

### 4. Import the Downloaded Project in IntelliJ IDEA
- Open IntelliJ IDEA.
- Click "File" > "Open."
- Select the project downloaded as a project.

### 5. Build and Run Your Application
- Open the project in IntelliJ IDEA.
- Locate the main Java class, usually with a main method.
- Before running the project, do a Maven clean and install.
- Right-click on the main class and select "Run" or "Debug" to start your application.
- Ensure it's running on port 8000, as mentioned in the application.properties file.

## Steps to Test My Application

You can test the application using either Postman or cURL.

### Using cURL

#### Process Receipt
- Run the following cURL commands to process receipt with the below payload:
```shell
curl -i -X POST -H "Content-Type: application/json" -d "{\"retailer\": \"Target\", \"purchaseDate\": \"2022-01-01\", \"purchaseTime\": \"13:01\", \"items\": [{\"shortDescription\": \"Mountain Dew 12PK\", \"price\": \"6.49\" }, { \"shortDescription\": \"Emils Cheese Pizza\", \"price\": \"12.25\" }, { \"shortDescription\": \"Knorr Creamy Chicken\", \"price\": \"1.26\" }, { \"shortDescription\": \"Doritos Nacho Cheese\", \"price\": \"3.35\" }, { \"shortDescription\": \"Klarbrunn 12-PK 12 FL OZ\", \"price\": \"12.00\" } ], \"total\": \"35.35\" }" http://localhost:8080/receipts/process
```
- Copy the ID from the response and utilize it to retrieve points in the subsequent GET call.

#### Get Points
- After processing receipt, fetch the points of the receipt by running the following cURL command:
```shell
curl -X GET -H "Content-Type: application/json" http://localhost:8080/receipts/{id}/points
```
Example
```shell
curl -X GET -H "Content-Type: application/json" http://localhost:8080/receipts/de14215d-40eb-441d-8c99-7ff55518e52c/points
```

## Using Postman

### Install Postman
- Download Postman from [here](https://www.postman.com/downloads/).
- Once Postman is downloaded, follow the steps below.

### Import the Postman Collection
- To import the Postman collection:
    - In the top left corner of Postman, click on the "Import" button.
    - Import this [Postman collection](https://github.com/sravyachouderpally/fetch-internship-challenge/blob/main/fetch-application-collection) from the repository.
    - After importing, you will see the imported collection listed in the left sidebar of Postman under "Collections."
    - Click on the collection to expand it and see the requests and folders it contains.
    - To run a request within the collection, click on the request's name in the collection.
    - In the request view, you can configure parameters, headers, and request body if needed.
    - Click the "Send" button to execute the request.

### Test Scenarios

#### Process Receipt
- Run the `/process` POST call with request body payload. The URL is `http://localhost:8080/receipts/process`.
- To add the payload, go to Body -> raw and change the type to JSON.
    - Example payloads:
        - `{"retailer": "Target", "purchaseDate": "2022-01-01", "purchaseTime": "13:01", "items": [{"shortDescription": "Mountain Dew 12PK", "price": "6.49"}, {"shortDescription": "Emils Cheese Pizza", "price": "12.25"}, {"shortDescription": "Knorr Creamy Chicken", "price": "1.26"}, {"shortDescription": "Doritos Nacho Cheese", "price": "3.35"}, {"shortDescription": "Klarbrunn 12-PK 12 FL OZ", "price": "12.00"}], "total": "35.35"}`

#### Get Points
- Once the receipt has been processed, obtain the points associated with each receipt using the ID received in the response from the `/process` POST call, by making a subsequent GET request to `/points`.
- The URL is `http://localhost:8080/receipts/{id}/points`.
    - Example url:
      -  `http://localhost:8080/receipts/de14215d-40eb-441d-8c99-7ff55518e52c/points`