package plutoDNA_simulator

class Tile implements ITile {

	public def static TILE_DIRT = 0
	public def static TILE_WATER = 1
	public def static TILE_SAND = 2

	private def value
	
	public Tile(value) {
		this.value = value
	}
	@Override
	public getValue() {
		return this.value
	}
	

}
