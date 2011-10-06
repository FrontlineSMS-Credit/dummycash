package dummycash

class User {
	String username
	String password
	Integer balance = 0
	Date lastChecked
	
	static constraints = {
		lastChecked(nullable:true)
	}
}
