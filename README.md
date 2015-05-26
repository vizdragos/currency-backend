# currency-backend

## Build for deployment (for example in `Tomcat`)

Run `mvn clean install` to create a war file in target/ which can be deployed to an application server.

## Build for development

Run `mvn jetty:run` to start application in embedded Jetty.

## Testing

Run `mvn clean install` or `mvn test` (`test` is includded in the build lifecycle before `install`)

## REST endpoint

`GET http://localhost:8080/currency-backend/trades` - get all trades

`POST http://localhost:8080/currency-backend/trades` - create new trade
