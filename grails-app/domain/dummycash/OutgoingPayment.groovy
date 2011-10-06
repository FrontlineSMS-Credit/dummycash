package dummycash

class OutgoingPayment {
	static belongsTo = [user:User]
	Date dateCreated
	Integer amount
	String recipient
	
	def beforeInsert = {
		user.balance -= amount
		user.save()
	}
}
