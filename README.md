# Bank-Transactions

Microservice that will handle bank transactions

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes

### Running the application locally

The application can be started locally using the following commands:

```
mvn clean install
java -jar bank-transactions-application/target/bank-transactions-application-0.0.1-SNAPSHOT.jar
```

### Running the application on Docker

However, you can run the application on Docker too:

```
docker run -d -p 5000:8080 rbailen/bank-transactions:latest
```

### Testing

You can test the application using bank-transactions.postman_collection.json into Postman

### Requirements

- A field has been created in the database to store the total balance
- If the date is not entered in the creation of a transaction, a default one is generated
- The date of each transaction must be greater than the last one
- If the channel is not entered in the payload of the status endpoint, it returns an invalid status
- The strategy pattern has been applied to manage the status of transactions
- The application uses H2 database to run as in-memory database
- You can verify the functionality with the integration tests
- Swagger has been enabled to visualize and interact with the API’s resources