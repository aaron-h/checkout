# The checkout service
> The checkout service provide the api for online shopping checkout

The service providing an api for shopping cart items price calculation.

## Installing / Getting started

Env: jdk1.8
From a command line in a Terminal window you can use the java -jar command to run jar package
```
$ java -Dserver.port=8090  -jar target/checkout-0.0.1-SNAPSHOT.jar

```
Test the api with curl in another terminal
curl -H "Content-Type:application/json" -X POST --data '["001","003","001","002","001","002","003"]' t:8090/checkout

### Building
Build with maven
./mvnw
./mvn clean install

## Features

Watch catalogue
Below is a catalogue of four watches and their associated prices:
Watch ID |   Watch Name  |  Unit Price   |  Discount
001      |   Rolex       |  100          |  3 for 200
002      |   Michael Kors|  80           |2 for 120
003      |   Swatch      |  50           |
004      |   Casio       |  30           |
There are a few requirements worth noting here:
● The first two products have a possible discount. As an example, if the user attempts to
checkout three or six Rolex watches then they will receive the discount price once or twice,
respectively.
● There is no limit to the number of items or combinations of watches a user can checkout.
● There is no limit to the number of times a discount can be used.
● Similarly, a user can checkout a single item if they wish

## Contributing
Aaron He

## Licensing

This project is licensed under Unlicense license. This license does not require you to take the license with you to your project.