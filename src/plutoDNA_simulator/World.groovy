package plutoDNA_simulator

import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.awt.Color

class World {

	
	private def tiles
	private def instance
	
	private def running
	private def updating
	
	// Scroll Values
	private def scrollx, scrolly
	
	public World(sizew, sizeh) {
		Random rand = new Random()
		this.tiles = new Tile[sizew][sizeh]	
		this.tiles.eachWithIndex {row, rindex ->
			row.eachWithIndex {tile, cindex ->
				this.tiles[rindex][cindex] = new Tile(rand.nextInt(10))			
			}
		} 
		
		this.scrollx = rand.nextInt(Assets.globalConfig.world.width - 32)
		this.scrolly = rand.nextInt(Assets.globalConfig.world.height - 32)
		
		this.running = true
		this.updating = true
	}
	
	private AI() {
		for (row in this.tiles) {
			for (tile in row) {
				def tileValue = tile.getValue()
			}
		}
		
		this.updating = false
	}
	
	public getSize() {
		return [tiles.size(), tiles[0].size()]
	}
	
	public getTile(x, y) {
		return tiles[x][y]
	}

	public start() {
		
		this.instance = Thread.start {
			while (this.running) {
				if (this.updating) { 
					this.AI() 
				}
			}
		}
		
	}	

	public stop() {
		this.running = true
	}

	public doUpdate() {
		this.updating = true
	}
	
	public render(width, height) {
		def buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
		def g2d = buffer.createGraphics()
		// Only draw portion of the world thats visible to the dimensions passed through. 
		def visibleIW = width / Assets.globalConfig.world.tilesize
		def visibleIH = height / Assets.globalConfig.world.tilesize
		def y = 0
		

		for (ix in this.scrolly..this.scrolly+visibleIH) {
			def x = 0
			for (iy in this.scrollx..this.scrollx+visibleIW) {
				switch (this.getTile(ix, iy).getValue()) {
					case 0:
						g2d.setColor(Color.BLUE)
						break
					case 1:
						g2d.setColor(Color.GREEN)
						break
					case 2:
						g2d.setColor(Color.RED)
						break
					case 3:
						g2d.setColor(Color.YELLOW)
						break
					case 4:
						g2d.setColor(Color.GRAY)
						break
					case 5:
						g2d.setColor(Color.WHITE)
						break
					default:
						g2d.setColor(Color.BLACK)
						break
				}
				g2d.fill(new Rectangle(x, y, Assets.globalConfig.world.tilesize, Assets.globalConfig.world.tilesize))
				x += Assets.globalConfig.world.tilesize
			}	
			y += Assets.globalConfig.world.tilesize
		}
		g2d.dispose()
		return buffer
	}
	
	public doneUpdating() {
		return !this.updating
	}

}
