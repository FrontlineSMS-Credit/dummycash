package dummycash

abstract class BaseController {
	def withAuth(Closure c) {
		def u = User.findByUsernameAndPassword(params.u, params.p)
		if(u) {
			c.call(u)
		} else {
			render text:'ERROR: Username or password did not match.'
		}
	}
}