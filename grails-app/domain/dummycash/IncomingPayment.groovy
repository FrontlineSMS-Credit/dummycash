package dummycash

class IncomingPayment {
	static belongsTo = [user:User]
	Date dateCreated
	Integer amount
	String sender
	
	def beforeInsert = {
		user.balance += amount
		user.save()
	}
}
