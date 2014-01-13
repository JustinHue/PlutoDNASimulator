package plutoDNA_simulator

class Environment {

	public static TILE_NAMES = ["dirt" : Tile.TILE_DIRT, 
								"water" : Tile.TILE_WATER,
								"sand" : Tile.TILE_SAND]
	
	def static getTileName(tile) {
		return this.TILE_NAMES.find { it.value == tile.getValue() }?.key
	}
}
