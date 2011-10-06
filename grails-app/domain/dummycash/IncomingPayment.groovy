package dummycash

class IncomingPayment {
	static belongsTo = User
	Integer amount
	String sender
}
