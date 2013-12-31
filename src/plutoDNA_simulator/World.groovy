package plutoDNA_simulator

class World implements Runnable {

	private def tiles
	private def instance
	
	public World(sizew, sizeh) {
		this.instance = new Thread(this)
		this.tiles = new Tile[sizew][sizeh]	
		this.tiles.eachWithIndex {row, rindex ->
			row.eachWithIndex {tile, cindex ->
				this.tiles[rindex][cindex] = new Tile()			
			}
		} 
	}
	
	public getSize() {
		return [this.tiles.size(), this.tiles[0].size()]
	}
	
	public getTile(x, y) {
		return this.tiles[x][y]
	}

	public start() {
		this.instance.start()
	}	

	@Override
	public void run() {
		
	}
}
