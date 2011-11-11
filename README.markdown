# Running DummyCash Web App
To run the dummy cash web app:

1. make sure you have grails installed (v 1.3.7) - http://grails.org/Installation
2. clone the DummyCash git repository
3. navigate to the DummyCash source directory on your local machine and execute command `grails run-app`

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

<table>
	<tr><td>name</td><td>description</td></tr>
	<tr><td>u</td><td>username</td></tr>
	<tr><td>p</td><td>password</td></tr>
	<tr><td>to</td><td>phone number of person to pay</td></tr>
	<tr><td>amount</td><td>amount of money to transfer</td></tr>
</table>

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


# Integration with FrontlineSMS

## Structure

The PaymentService itself should extend an abstract payment service class allowing hooks for:

* running an incoming payment checking service
* checking balance on demand
* making an outgoing payment

## Settings

Settings should be defined for a PaymentService in a similar way to `net.frontlinesms.messaging.sms.internet.SmsInternetService`.  These classes just define the method `getPropertiesStructure()` and the UI for creating or modifying settings of a service is generated from that.  See also `net.frontlinesms.ui.handler.settings.SmsInternetServiceSettingsHandler` for UI handling.

	/** 
	 * Get the default properties for this class.
	 */
	public LinkedHashMap<String, Object> getPropertiesStructure() {
		LinkedHashMap<String, Object> defaultSettings = new LinkedHashMap<String, Object>();
		defaultSettings.put(PROPERTY_USERNAME, "");
		defaultSettings.put(PROPERTY_PASSWORD, new PasswordString(""));
		defaultSettings.put(PROPERTY_SERVER_URL, "");
		return defaultSettings;
	}

## Service Detection

Services should be detected using an implementation of `ImplementationLoader<PaymentService>` which should check in `src/main/resources/META-INF/frontlinesms/payment/PaymentServices`.

# Possible Extensions

* add "reason" field to incoming payments, and pass this into the `IncomingPayment.notes` field in FrontlineSMS
* require different Client metadata than name+phonenumber (as per e.g. MPESA PayBill) using `CustomField`s
* examine outgoing payments to PayBill in more detail and look at option of a `PaymentService` which allows multiple different classes of outgoing payment, each with own contraints and Client data requirements
