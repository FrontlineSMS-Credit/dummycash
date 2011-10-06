package dummycash

class BalanceController extends BaseController {
	/*
		request URL: http://frontline-payment-server/balance/?u=yourUsername&p=yourPassword
		response: 1500
	*/
	
	def index = {
		withAuth { u ->
			render text: u.balance
		}
	}
}
