package dummycash

class IncomingController extends BaseController {
	/*
		request URL: http://frontline-payment-server/incoming/?u=yourUsername&p=yourPassword
	*/
	
	def index = {
		withAuth { u ->
			render(contentType:"text/json") {
				IncomingPayment.findAllByUser(u)
			}
		}
	}
}
