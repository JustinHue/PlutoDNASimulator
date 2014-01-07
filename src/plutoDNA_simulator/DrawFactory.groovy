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
		def drawTilePosition = [(int)(drawPosition[0] / Assets.globalConfig.world.tilesize), (int)(drawPosition[1] / Assets.globalConfig.world.tilesize)]

		for (iy in drawTilePosition[1]..drawTilePosition[1]+visibleIH) {
			def x = 0
			for (ix in drawTilePosition[0]..drawTilePosition[0]+visibleIW) {
				def tileImage = DrawFactory.renderTile(world.getTile(ix, iy))
				g2d.drawImage(tileImage, 
					x, y, Assets.globalConfig.world.tilesize, Assets.globalConfig.world.tilesize, null)
				x += Assets.globalConfig.world.tilesize
			}
			y += Assets.globalConfig.world.tilesize
		}
		// Draw Entities
		
		for (entity in world.getEntities()) {
			def entityImage = DrawFactory.renderEntity(entity)
			def capabilities = entity.getCapabilities()
			def entityCoordinate = capabilities["coordinate"]
			// If entity is in viewable range, draw it
			if (entityCoordinate[0] >= drawPosition[0] && entityCoordinate[0] <= drawPosition[0] + width - Assets.globalConfig.world.tilesize &&
				entityCoordinate[1] >= drawPosition[1] && entityCoordinate[1] <= drawPosition[1] + height - Assets.globalConfig.world.tilesize) {

				def entityDrawCoordinate = [((int)(entityCoordinate[0] / Assets.globalConfig.world.tilesize)) * Assets.globalConfig.world.tilesize,
											((int)(entityCoordinate[1] / Assets.globalConfig.world.tilesize)) * Assets.globalConfig.world.tilesize]
				
				def drawCoordinate = [(int)(entityDrawCoordinate[0] - drawPosition[0]), (int)(entityDrawCoordinate[1] - drawPosition[1])]
				
				g2d.drawImage(entityImage, drawCoordinate[0], drawCoordinate[1],
						 	Assets.globalConfig.world.tilesize, Assets.globalConfig.world.tilesize, null)
			}
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
		def entityImage = new BufferedImage(Assets.globalConfig.world.tilesize, Assets.globalConfig.world.tilesize,
											BufferedImage.TYPE_INT_RGB)
		def g2d = entityImage.createGraphics()
		g2d.setColor(Color.WHITE)
		
		g2d.fillRect(0, 0, Assets.globalConfig.world.tilesize, Assets.globalConfig.world.tilesize)
		return entityImage
	}
	
}
