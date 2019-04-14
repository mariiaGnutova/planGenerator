# planGenerator
Generates Repayment Plan for given Loan parameters.

## How to run
+ Start DemoApplication

## Links
+ Daten Bunk H2 http://localhost:8080/h2-console/
+ Swager2 http://localhost:8080/swagger-ui.html#/main-controller/createRepaymentUsingPOST

## How to use
+ send POST request to /v1/repayment/response with payload:
  + loanAmount
  + nominalRate
  + duration
  + startDate

`startDate` format: YYYY-MM-DDThh:mm:ssZ

## How to run tests
+ Start from planGenerator/src/test/java/com/plangenerator/controller/MainControllerTest.java
