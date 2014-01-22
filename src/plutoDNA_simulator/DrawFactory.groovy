package plutoDNA_simulator

import java.awt.image.BufferedImage
import java.awt.Color
import java.awt.Font
import java.awt.FontMetrics
import java.awt.Rectangle
import gui.AlignmentEnum

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
	
	def static renderScene(scene) {
		
		def width = scene.getWidth()
		def height = scene.getHeight()
		
		def sceneImage = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB)
		def g2d = sceneImage.createGraphics()
		
		// Render Background
		g2d.setColor(Color.BLACK)
		g2d.fillRect(0, 0, width, height)
		
		scene.getComponents().each {it ->
			// Determine what type of component it is
			switch (it.getClass().getName()) {
				case "gui.TextLabel":
					renderTextLabel(scene, g2d, it)
				break
				case "gui.Menu":
					renderMenu(scene, g2d, it)
				break
			}
			
		}
		
		return sceneImage
	}
	
	/*
	 * Render Menu onto scene using g2d
	 */
	def static renderMenu(scene, g2d, menu) {
		menu.items.each {it ->
			it.color = (it.selected) ? menu.selectedColor : menu.unselectedColor			
			renderMenuItem(scene, g2d, it)
		}
	}
	
	/*
	 * Render Menu Item onto scene using g2d
	 */
	def static renderMenuItem(scene, g2d, menuItem) {
		
		def width = scene.getWidth()
		def height = scene.getHeight()
		
		g2d.setColor(menuItem.color)
		g2d.setFont(menuItem.font);
		FontMetrics fontMetrics = g2d.getFontMetrics(menuItem.font)
		def itwidth = fontMetrics.stringWidth(menuItem.text)
		def itheight = fontMetrics.getHeight()
		def coordinates = menuItem.coordinate.clone()
		// Set position of label based on Alignment and coordinates. If the alignment
		// is already set the coordinates will be used as an offset.
		switch (menuItem.alignment) {
			case AlignmentEnum.TOP_LEFT:
				coordinates[0] = width / 4 - itwidth / 2
				coordinates[1] = height / 4 - itheight / 2
			break
			case AlignmentEnum.TOP_MIDDLE:
				coordinates[0] = width / 2 - itwidth / 2
				coordinates[1] = height / 4 - itheight / 2
			break
			case AlignmentEnum.TOP_RIGHT:
				coordinates[0] = width / 4 * 3 - itwidth / 2
				coordinates[1] = height / 4 - itheight / 2
			break
			case AlignmentEnum.LEFT:
				coordinates[0] = width / 4 - itwidth / 2
				coordinates[1] = height / 2 - itheight / 2
			break
			case AlignmentEnum.MIDDLE:
				coordinates[0] = width / 2 - itwidth / 2
				coordinates[1] = height / 2 - itheight / 2
			break
			case AlignmentEnum.RIGHT:
				coordinates[0] = width / 4 * 3 - itwidth / 2
				coordinates[1] = height / 2 - itheight / 2
			break
			case AlignmentEnum.BOTTOM_LEFT:
				coordinates[0] = width / 4 - itwidth / 2
				coordinates[1] = 3 * height / 4 - itheight / 2
			break
			case AlignmentEnum.BOTTOM_MIDDLE:
				coordinates[0] = width / 2 - itwidth / 2
				coordinates[1] = 3 * height / 4 - itheight / 2
			break
			case AlignmentEnum.BOTTOM_RIGHT:
				coordinates[0] = width / 4 * 3 - itwidth / 2
				coordinates[1] = 3 * height / 4 - itheight / 2
			break
			case AlignmentEnum.FLUSH_TOP_LEFT:
				coordinates[0] = 0
				coordinates[1] = 0
			break
			case AlignmentEnum.FLUSH_TOP_RIGHT:
				coordinates[0] = width - itwidth
				coordinates[1] = 0
			break
			case AlignmentEnum.FLUSH_BOTTOM_LEFT:
				coordinates[0] = 0
				coordinates[1] = height - itheight
			break
			case AlignmentEnum.FLUSH_BOTTOM_RIGHT:
				coordinates[0] = width - itwidth
				coordinates[1] = height - itheight
			break
			case AlignmentEnum.FLUSH_TOP:
				coordinates[0] = width / 2 - itwidth / 2
				coordinates[1] = 0
			break
			case AlignmentEnum.FLUSH_LEFT:
				coordinates[0] = 0
				coordinates[1] = height / 2 - itheight / 2
			break
			case AlignmentEnum.FLUSH_RIGHT:
				coordinates[0] = width - itwidth
				coordinates[1] = height / 2 - itheight / 2
			break
			case AlignmentEnum.FLUSH_BOTTOM:
				coordinates[0] = width / 2 - itwidth / 2
				coordinates[1] = height - itheight
			break
		}
		coordinates[0] += menuItem.coordinate[0]
		coordinates[1] += menuItem.coordinate[1]
		g2d.drawString(menuItem.text, coordinates[0], coordinates[1] + fontMetrics.getAscent())

		
	}
	
	/*
	 * Render Text Label onto scene using g2d
	 */
	def static renderTextLabel(scene, g2d, label) {
		
		def width = scene.getWidth()
		def height = scene.getHeight()
		
		g2d.setColor(label.color)
		g2d.setFont(label.font);
		FontMetrics fontMetrics = g2d.getFontMetrics(label.font)
		def itwidth = fontMetrics.stringWidth(label.text)
		def itheight = fontMetrics.getHeight()
		def coordinates = label.coordinate.clone()
		// Set position of label based on Alignment and coordinates. If the alignment
		// is already set the coordinates will be used as an offset.
		switch (label.alignment) {
			case AlignmentEnum.TOP_LEFT:
				coordinates[0] = width / 4 - itwidth / 2
				coordinates[1] = height / 4 - itheight / 2
			break
			case AlignmentEnum.TOP_MIDDLE:
				coordinates[0] = width / 2 - itwidth / 2
				coordinates[1] = height / 4 - itheight / 2
			break
			case AlignmentEnum.TOP_RIGHT:
				coordinates[0] = width / 4 * 3 - itwidth / 2
				coordinates[1] = height / 4 - itheight / 2
			break
			case AlignmentEnum.LEFT:
				coordinates[0] = width / 4 - itwidth / 2
				coordinates[1] = height / 2 - itheight / 2
			break
			case AlignmentEnum.MIDDLE:
				coordinates[0] = width / 2 - itwidth / 2
				coordinates[1] = height / 2 - itheight / 2
			break
			case AlignmentEnum.RIGHT:
				coordinates[0] = width / 4 * 3 - itwidth / 2
				coordinates[1] = height / 2 - itheight / 2
			break
			case AlignmentEnum.BOTTOM_LEFT:
				coordinates[0] = width / 4 - itwidth / 2
				coordinates[1] = 3 * height / 4 - itheight / 2
			break
			case AlignmentEnum.BOTTOM_MIDDLE:
				coordinates[0] = width / 2 - itwidth / 2
				coordinates[1] = 3 * height / 4 - itheight / 2
			break
			case AlignmentEnum.BOTTOM_RIGHT:
				coordinates[0] = width / 4 * 3 - itwidth / 2
				coordinates[1] = 3 * height / 4 - itheight / 2
			break
			case AlignmentEnum.FLUSH_TOP_LEFT:
				coordinates[0] = 0
				coordinates[1] = 0
			break
			case AlignmentEnum.FLUSH_TOP_RIGHT:
				coordinates[0] = width - itwidth
				coordinates[1] = 0
			break
			case AlignmentEnum.FLUSH_BOTTOM_LEFT:
				coordinates[0] = 0
				coordinates[1] = height - itheight
			break
			case AlignmentEnum.FLUSH_BOTTOM_RIGHT:
				coordinates[0] = width - itwidth
				coordinates[1] = height - itheight
			break
			case AlignmentEnum.FLUSH_TOP:
				coordinates[0] = width / 2 - itwidth / 2
				coordinates[1] = 0
			break
			case AlignmentEnum.FLUSH_LEFT:
				coordinates[0] = 0
				coordinates[1] = height / 2 - itheight / 2
			break
			case AlignmentEnum.FLUSH_RIGHT:
				coordinates[0] = width - itwidth
				coordinates[1] = height / 2 - itheight / 2
			break
			case AlignmentEnum.FLUSH_BOTTOM:
				coordinates[0] = width / 2 - itwidth / 2
				coordinates[1] = height - itheight
			break
		}
		coordinates[0] += label.coordinate[0]
		coordinates[1] += label.coordinate[1]
		g2d.drawString(label.text, coordinates[0], coordinates[1] + fontMetrics.getAscent())

	}
	
}
