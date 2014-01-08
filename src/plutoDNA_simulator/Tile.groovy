package plutoDNA_simulator

class Tile implements ITile {

	def static TILE_DIRT = Assets.globalConfig.world.tiles.DIRT
	def static TILE_WATER = Assets.globalConfig.world.tiles.WATER
	def static TILE_SAND = Assets.globalConfig.world.tiles.SAND
	def static TILE_SIZE = Assets.globalConfig.world.tilesize
	
	def value
	
	// Tile Attributes
	def smell
	def wind_direction
	
	public Tile(value) {
		this.value = value
		this.wind_direction = DirectionEnum.NONE.value()
		this.smell = value
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
	

}
