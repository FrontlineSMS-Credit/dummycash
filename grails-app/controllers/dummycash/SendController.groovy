package dummycash

class SendController extends BaseController {
	/*
		# Variables
		| name   | description
		| u      | username
		| p      | password
		| to     | phone number of person to pay
		| amount | amount of money to transfer

		# Example
		request URL: http://frontline-payment-server/send/?u=yourUsername&p=yourPassword&to=071234567&amount=300
		response: OK
	*/
	
	def index = {
		withAuth { u ->
			def p = new OutgoingPayment(user:u, amount:params.amount, recipient:params.to)
			if(p.save()) {
				render text:'OK'
			} else {
				render text:'ERROR: saving outgoing payment failed'
			}
		}
	}
}
