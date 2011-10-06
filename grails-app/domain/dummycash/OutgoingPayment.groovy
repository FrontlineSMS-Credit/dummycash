package dummycash

class OutgoingPayment {
	static belongsTo = User
	Integer amount
	String recipient
}
