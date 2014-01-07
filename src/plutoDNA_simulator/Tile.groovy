package plutoDNA_simulator

class Tile implements ITile {

	public def static TILE_DIRT = 0
	public def static TILE_WATER = 1
	public def static TILE_SAND = 2

	private def value
	
	private def smell
	private def windDirection
	
	public Tile(value) {
		this.value = value
		this.windDirection = DirectionEnum.NONE.value()
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
		return this.windDirection
	}
	

}
