package plutoDNA_simulator

import java.awt.image.BufferedImage
import java.awt.Color
import java.awt.Rectangle

class DrawFactory {

	def static renderWorld(world, width, height, drawPosition) {
		def buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
		def g2d = buffer.createGraphics()
		// Only draw portion of the world thats visible to the dimensions passed through.
		def visibleIW = width / Assets.globalConfig.world.tilesize
		def visibleIH = height / Assets.globalConfig.world.tilesize
		def y = 0
		
		drawPosition[0] = (int) drawPosition[0]
		drawPosition[1] = (int) drawPosition[1]
		
		println drawPosition
		
		for (iy in drawPosition[1]..drawPosition[1]+visibleIH) {
			def x = 0
			for (ix in drawPosition[0]..drawPosition[0]+visibleIW) {
				def tileImage = DrawFactory.renderTile(world.getTile(ix, iy))
				g2d.drawImage(tileImage, 
					x, y, Assets.globalConfig.world.tilesize, Assets.globalConfig.world.tilesize, null)
				x += Assets.globalConfig.world.tilesize
			}
			y += Assets.globalConfig.world.tilesize
		}
		g2d.dispose()
		return buffer
	}
	
	def static renderTile(tile) {
		def tileImage = new BufferedImage(Assets.globalConfig.world.tilesize, Assets.globalConfig.world.tilesize, 
									BufferedImage.TYPE_INT_RGB)
		def g2d = tileImage.createGraphics()
		switch (tile.getValue()) {
			case Tile.TILE_WATER:
				g2d.setColor(Color.BLUE)
				break
			case 1:
				g2d.setColor(Color.GREEN)
				break
			case Tile.TILE_DIRT:
				g2d.setColor(new Color(92, 51, 23))
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
		g2d.fillRect(0, 0, Assets.globalConfig.world.tilesize, Assets.globalConfig.world.tilesize)
		return tileImage
	}
	
	def static renderEntity(entity) {
		
	}
	
}
