package plutoDNA_simulator

interface IWorld {

	void AI()
	void update()
	boolean doneUpdating()
	
	def getSize()
	def getSizeTI()
	def getWidthTI()
	def getHeightTI()
	
	def getTile(x, y)
	
	
}
