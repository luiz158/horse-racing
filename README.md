 README
========

## Design decisions, assumptions
- If the input is not valid, a message will be shown in the output field
- So that the example input can produce the expected output, ball throws for absent lines are discarded.
- There is no validation on the value of the ball throw, so you could pass in any value, including negative numbers.


## Requisites
- Java
- Gradle
- Preferred IDE/Text editor
- Internet connection

## Basic commands
- `$ ./gradlew test`: run tests
- `$ ./gradlew build`: builds the project
- `$ java -jar build/libs/horse-racing-0.1.0.jar`: runs the server

Point your browser to [http://localhost:8080/horse-racing](http://localhost:8080/horse-racing) to read the wording of the exercise.
