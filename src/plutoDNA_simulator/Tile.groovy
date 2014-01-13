package plutoDNA_simulator

class Tile implements ITile {

	def static TILE_DIRT = Assets.globalConfig.world.tiles.DIRT
	def static TILE_WATER = Assets.globalConfig.world.tiles.WATER
	def static TILE_SAND = Assets.globalConfig.world.tiles.SAND
	def static TILE_SIZE = Assets.globalConfig.world.tilesize
	
	def value
	def imageValue
	
	def instance
	def running
	def updating
	
	// Tile Properties
	def smell
	def wind_direction

	
	public Tile(value) {
		def random = new Random()
		this.value = value
		this.wind_direction = DirectionEnum.NONE.value()
		this.smell = value

		this.setImageValue()
	}
	
	public Tile(value, wind_direction, smell, elevation) {
		def random = new Random()
		this.value = value
		this.wind_direction = wind_direction
		this.smell = smell
		this.setImageValue()
	}
	
	public setImageValue() {
		def imageCollection = Assets.ASSETS["environment"][Environment.getTileName(this)]
		if (imageCollection) {
			def random = new Random()
			this.imageValue = random.nextInt(imageCollection.size())
		}
	}
	
	@Override
	public def getValue() {
		return this.value
	}
	
	@Override
	public def getSmell() {
		return this.smell
	}
	@Override
	public def getWindDirection() {
		return this.wind_direction
	}
	
	@Override
	public def getImageValue() {
		return this.imageValue
	}

	@Override
	public def AI() {

	}

	@Override
	public def update() {
		println 'updating'
		this.updating = true
		this.running = true
		this.instance = Thread.start {
			if (this.updating) this.AI()
		}
		this.running = false
	}

	@Override
	public def doneUpdating() {
		return !this.updating
	}
}
