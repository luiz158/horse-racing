 README
========

## Notes From Darren

Since we would be needing to retrieve values from the hashmap regularly in order to move the horses I thought it would be better to use a Hashmap. Then sort it once at the end.

This may have been different if we had more data, and needed to print out the results more regularly than just process once and spit them out at the end.

I used a Compare instead of implementing Comparable on Horse because Comparable is normally used for natural ordering and I did not think that distance was a natural ordering for a Horse.

I used guava because I think it keeps the code cleaner.

I made the assumption that we are not running this concurrently and so do not need to keep everything immutable. If we wanted to keep the Horse immutable
then I would have had the addDistance function return a new horse instead of altering the horse covered distance but didn't in order to keep teh code simpler.

I put the sorting into the RacingService since I think it is better suited in that class rather than the outputWriter.

I assumed that a horse only needs to cover 220 yards to finish the race not 221 Yards looking at your expected output I think this
is what you wanted anyway.

I decided to leave the Front end code as it is, I could have returned the correct error codes in the case of invalid data from
the controller but decided against it because I didn't feel that this was required for this exercise.


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
