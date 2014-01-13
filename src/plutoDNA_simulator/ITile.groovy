package plutoDNA_simulator

interface ITile {

	def getValue()
	def getImageValue()
	def getSmell()
	def getWindDirection()
	
	def AI()
	def update()
	def doneUpdating()
	
}
