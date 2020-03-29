# Onesystem API
Api to submit input files with transaction data and query reports.<br/>
#### Pregenerated Output.txt location
* ./bin/Output.txt
## Assumtions
I found it unclear whether the Inputs.txt file was to be loaded into the database initially as raw data and the send to Kafka to then again be persisted as structured data for later retrieval via reports.<br/>
<br/>
I found to me the interpretation of the task that made most sense was as documented in the process flow below. 
* Input file with transactions file is posted to the InputsController.
* InputsController sends each individual transaction to Kafka.
* Kafka listener receives the transactions and persist them in the database.
* Reports can be queries through the ReportsController

## API Documentation
Please see Swagger for API documentation: http://localhost: http://localhost:8080/swagger-ui.html

## Configuration
All the configurations are included in the `application.yml` file.

## Run
### Prerequisites
* Docker Compose installed
### Commands
To run the application please use commands below present in the project bin directory.
* ./run.sh - starts the application as well as dependencies configured in docker-compose.yml
* ./post_input.sh - posts the Input.txt file to the Inputs controller which sends the messages to Kafka
* ./retrieve_report_csv.sh - retrieves the requsted report in CSV format.
* ./retrieve_report_json.sh - retrieves the requsted report in JSON format.

## Test ##
For testing/debugging - execute the script in the scrips folder: src/tests/resources/scripts, which sends a message to the topic/queue.

## Integration test
 Run All Integration Tests
* `mvn clean install -Pintegration-test`