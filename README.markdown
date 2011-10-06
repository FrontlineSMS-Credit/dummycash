# Dummy Payment Service
Name of the service: DummyCash
Settings required in Payment View service configuration:
* server URL
* username
* password

## Authentication
* use username and password so that it is dissimilar to MPESA

## API

### Transport protocol
* calls to the API should be made with HTTP GET because it's easy to implement and debug
* authentication should be performed on every transaction comparing the params 'u' and 'p' to the service's fixed username and password
* variable values should be URL encoded
* responses should be JSON objects or strings as defined in *Methods* section

### Methods
* check balance
* send payment
* poll for new incoming payments

#### Check Balance
request URL: http://frontline-payment-server/balance/?u=yourUsername&p=yourPassword
response: 1500

#### Send Payment
##### Variables
| name   | description
| u      | username
| p      | password
| to     | phone number of person to pay
| amount | amount of money to transfer

##### Example
request URL: http://frontline-payment-server/send/?u=yourUsername&p=yourPassword&to=071234567&amount=300
response: OK

#### Poll for new Incoming Payments
request URL: http://frontline-payment-server/incoming/?u=yourUsername&p=yourPassword
response:
[
	{"amount":"400", "sender":"asdfghjk", "date":"2011-10-06T12:29:11Z"},
	{"amount":"12", "sender":"0324567", "date":"2011-10-06T12:24:11Z"}
]

### Errors
Errors should be plain text response of mime type text and form:
ERROR: description text

### Server admin user accounts
* There should be an HTTP form available on the server to generate new user/password combos.
* there should be a page for listing all user accounts
* there should be pages for browsing incoming and outgoing payments

### Generating incoming payments
There should be an HTTP form available on the server to generate incoming payments.

## Implementation

### Examples
To make an HTTP request from FrontlineSMS, the simplest method to use is:
net.frontlinesms.FrontlineUtils.makeHttpRequest(url, true)
