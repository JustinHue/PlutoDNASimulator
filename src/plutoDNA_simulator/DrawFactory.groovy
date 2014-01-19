package plutoDNA_simulator

import java.awt.image.BufferedImage
import java.awt.Color
import java.awt.Rectangle

class DrawFactory {

	def static renderWorld(world, width, height, drawPosition) {
		
		def buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
		def g2d = buffer.createGraphics()
		
		// Only draw portion of the world thats visible to the dimensions passed through.
		def visibleIW = width / Tile.TILE_SIZE
		def visibleIH = height / Tile.TILE_SIZE
		def y = 0
		
		drawPosition[0] = (int) drawPosition[0]
		drawPosition[1] = (int) drawPosition[1]
		def drawTilePosition = [(int)(drawPosition[0] / Tile.TILE_SIZE), (int)(drawPosition[1] / Tile.TILE_SIZE)]
		
		// Draw Tiles
		for (iy in drawTilePosition[1]..drawTilePosition[1]+visibleIH) {
			def x = 0
			for (ix in drawTilePosition[0]..drawTilePosition[0]+visibleIW) {
				def tileImage = DrawFactory.renderTile(world.getTile(ix, iy))
				g2d.drawImage(tileImage, 
					x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, null)
				x += Tile.TILE_SIZE
			}
			y += Tile.TILE_SIZE
		}
		
		// Draw Entities
		for (entity in world.getEntities()) {
			def entityImage = DrawFactory.renderEntity(entity)
			def capabilities = entity.getCapabilities()
			def entityCoordinate = [capabilities["coordinate"].getX(), capabilities["coordinate"].getY()]
			// If entity is in viewable range, draw it
			if (entityCoordinate[0] >= drawPosition[0] && entityCoordinate[0] <= drawPosition[0] + width - Tile.TILE_SIZE &&
				entityCoordinate[1] >= drawPosition[1] && entityCoordinate[1] <= drawPosition[1] + height - Tile.TILE_SIZE) {

				def entityDrawCoordinate = [((int)(entityCoordinate[0] / Tile.TILE_SIZE)) * Tile.TILE_SIZE,
											((int)(entityCoordinate[1] / Tile.TILE_SIZE)) * Tile.TILE_SIZE]
				
				def drawCoordinate = [(int)(entityDrawCoordinate[0] - drawPosition[0]), (int)(entityDrawCoordinate[1] - drawPosition[1])]
				
				g2d.drawImage(entityImage, drawCoordinate[0], drawCoordinate[1],
						 	Tile.TILE_SIZE, Tile.TILE_SIZE, null)
			}
		}
		g2d.dispose()
		return buffer
	}
	
	def static renderTile(tile) {

		def tileImage = new BufferedImage(Tile.TILE_SIZE, Tile.TILE_SIZE, 
									BufferedImage.TYPE_INT_RGB)
		def imageValue = tile.getImageValue()

		def g2d = tileImage.createGraphics()

		switch (tile.getValue()) {
			case Tile.TILE_WATER:
				g2d.drawImage(Assets.ASSETS["environment"]["water"][imageValue], 0, 0, Tile.TILE_SIZE, Tile.TILE_SIZE, null)
				break
			case 1:
				//g2d.drawImage(Assets.ASSETS["environment"]["grass"][imageValue], 0, 0, Tile.TILE_SIZE, Tile.TILE_SIZE, null)
				break
			case Tile.TILE_DIRT:
				g2d.drawImage(Assets.ASSETS["environment"]["dirt"][imageValue], 0, 0, Tile.TILE_SIZE, Tile.TILE_SIZE, null)
				break
			case Tile.TILE_SAND:
				g2d.drawImage(Assets.ASSETS["environment"]["sand"][imageValue], 0, 0, Tile.TILE_SIZE, Tile.TILE_SIZE, null)
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
		return tileImage
	}
	
	def static renderEntity(entity) {
		def entityImage = new BufferedImage(Tile.TILE_SIZE, Tile.TILE_SIZE,
											BufferedImage.TYPE_INT_RGB)
		def g2d = entityImage.createGraphics()
		g2d.setColor(Color.WHITE)
		
		g2d.fillRect(0, 0, Tile.TILE_SIZE, Tile.TILE_SIZE)
		return entityImage
	}
	
	def static renderMenu(menu, width, height) {
		def menuImage = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB)
		def g2d = menuImage.createGraphics()
		
		g2d.setColor(Color.BLACK)
		g2d.fillRect(0, 0, width, height)
		
		return menuImage
	}
	
}
