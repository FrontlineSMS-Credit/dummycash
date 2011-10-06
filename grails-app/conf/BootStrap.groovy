import dummycash.*

class BootStrap {
	def init = { servletContext ->
		// create test users
		[
			['Nathan', 'secret', 100],
			['Kim', 'password', 2000],
			['Alex', '12345', -100]
		].each {
			new User(username:it[0], password:it[1], balance:it[2]).save(failOnError:true)
		}
	}
	def destroy = {
	}
}
