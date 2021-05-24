# Overview

Decathlon scoring utility.

## Usage

You should build jar:

`mvn package`

and then run it like this: 
```
java -jar target/decathlon-scoring-1.0-SNAPSHOT.jar assets/sample_input3.csv
```

## Testing

You can run tests using `mvn clean test`.

JaCoCo will generate code coverage report for you on 
path `./target/site/jacoco/index.html`.

## Known issues

Sometime it have a difference with official results for 1 point,
and I really don't have any idea why.