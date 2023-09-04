# Getting Started

### Helpful Links
* [H2 In-Memory Database Console](http://localhost:8080/h2-console/login.jsp) - http://localhost:8080/h2-console/login.jsp
* [Swagger UI](http://localhost:8080/swagger-ui/index.html) - http://localhost:8080/swagger-ui/index.html

### Order of Code Operations
1. <span style="color:green">AuthenticationFilter:</span>
   * Requires an "api-key" passed in the header to authenticate the /api/v1/invoice endpoint.
      * Authentication can be bypassed by setting the property <span style="color:#1F51FF"><ins>(src/main/resources/application.yml)</ins></span> <span style="color:orange">filter:authenticate = false.</span>
      * This allows you to use Swagger-UI to perform the operation.
   * The filter does not require Authentication to access the Swagger-UI (/swagger-ui/*) or H2 DB UI (/h2-console) endpoints in any circumstance.
2. <span style="color:green">InvoiceRequestValidator:</span>
   * Validates the InvoiceRequestDto object to ensure the customerID integer values is greater than 0 but less than 1000.
   * If the input validation fails, a 404 - BAD REQUEST is returned.

### CURL Command for API utilizing authentication
curl -i -X POST http://localhost:8080/api/v1/invoice --header "api-key: aBc129" --header "Content-Type: application/json" --data-raw "{\"customerId\": 2}"


