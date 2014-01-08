package plutoDNA_simulator

interface IEntity {

	void AI()
	void update(delta_time)
	boolean doneUpdating()
	
	// Getters
	def getDNA()
	def getCapabilities()

	def getRect()
	def getPoint()
	
}
