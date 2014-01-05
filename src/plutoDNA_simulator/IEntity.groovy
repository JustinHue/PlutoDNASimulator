package plutoDNA_simulator

interface IEntity {

	void AI()
	void update(delta_time)
	boolean doneUpdating()
	
	def getDNA()
	def getPhysical()
	def getDeltaTime()
}
