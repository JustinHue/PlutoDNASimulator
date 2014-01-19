package plutoDNA_simulator

interface IMenuScene {

	def getTitle()
	def getVersion()
	def getCopyright()
	
	def getDimensions()
	def getWidth()
	def getHeight()
	
	// AI + Update
	def AI()
	def update(delta_time)
	def doneUpdating()
	
	// Key Events
	def keysDown(keys)
	def keysUp(keys)
	def keysPressed(keys)

	def mousePressed(buttons)
	def mouseReleased(buttons)
	def mouseDown(buttons)
	def mouseUp(buttons)
	
}
