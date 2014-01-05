package plutoDNA_simulator

interface IWorld {

	void AI()
	def update(delta_time)
	boolean doneUpdating()
	
	def getSize()
	def getWidth()
	def getHeight()
	def getSizeTI()
	def getWidthTI()
	def getHeightTI()
	
	def getTile(x, y)
	
	
}
