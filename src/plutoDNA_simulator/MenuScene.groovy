package plutoDNA_simulator

import gui.AlignmentEnum;
import gui.TextLabel;

import java.awt.FontMetrics
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage

abstract class MenuScene implements IMenuScene {

	def instance
	def running
	def updating
	
	def title
	def version
	def copyright
	
	def width, height
	
	public MenuScene(width, height, title, version, copyright) {
		
		// Set General Field Values
		this.width = width
		this.height = height
		this.running = false
		this.updating = false
		
		// Temporary Graphics, used to set position of labels
		def tg2d = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB).getGraphics()
		def metrics = tg2d.getFontMetrics(TextLabel.STANDARD_FONT)
		// Grab title alignment 
		def title_alignment = Assets.globalConfig.MenuScene.title_alignment
		def titlex = 0, titley = 0
		// Grab vertical and horizontal offsets
		def version_hoffset = Assets.globalConfig.MenuScene.version_hoffset
		def version_voffset = Assets.globalConfig.MenuScene.version_voffset
		def copyright_hoffset = Assets.globalConfig.MenuScene.copyright_hoffset
		def copyright_voffset = Assets.globalConfig.MenuScene.copyright_voffset
		def versiony = height - metrics.getHeight() * 2
		def copyrightx = width - metrics.stringWidth(copyright) - copyright_hoffset
		def copyrighty = versiony

		// Set Title Alignment
		switch (title_alignment) {
			case AlignmentEnum.TOP_LEFT:
				titlex = width / 2 - metrics.stringWidth(title) / 2 - width / 4
				titley = height / 4 - metrics.getHeight() / 2
			break
			case AlignmentEnum.TOP_MIDDLE:
				titlex = width / 2 - metrics.stringWidth(title) / 2
				titley = height / 4 - metrics.getHeight() / 2 
			break
			case AlignmentEnum.TOP_RIGHT:
				titlex = width / 2 - metrics.stringWidth(title) / 2 + width / 4
				titley = height / 4 - metrics.getHeight() / 2
			break
			case AlignmentEnum.LEFT:
				titlex = width / 2 - metrics.stringWidth(title) / 2 - width / 4
				titley = height / 2 - metrics.getHeight() / 2
			break
			case AlignmentEnum.MIDDLE:
				titlex = width / 2 - metrics.stringWidth(title) / 2
				titley = height / 2 - metrics.getHeight() / 2
			break
			case AlignmentEnum.RIGHT:
				titlex = width / 2 - metrics.stringWidth(title) / 2 + width / 4
				titley = height / 2 - metrics.getHeight() / 2
			break
			case AlignmentEnum.BOTTOM_LEFT:
				titlex = width / 2 - metrics.stringWidth(title) / 2 - width / 4
				titley = height - metrics.getHeight() / 2 - height / 4
			break
			case AlignmentEnum.BOTTOM_MIDDLE:
				titlex = width / 2 - metrics.stringWidth(title) / 2
				titley = height - metrics.getHeight() / 2 - height / 4
			break
			case AlignmentEnum.BOTTOM_RIGHT:
				titlex = width / 2 - metrics.stringWidth(title) / 2 + width / 4
				titley = height - metrics.getHeight() / 2 - height / 4
			break
		}
		
		this.title = new TextLabel(title, [titlex, titley])
		this.version = new TextLabel(version, [version_hoffset, versiony - version_voffset])
		this.copyright = new TextLabel(copyright, [copyrightx, copyrighty - copyright_voffset])
	}
	
	@Override
	public def getWidth() {
		return this.width
	}
	
	@Override
	public def getHeight() {
		return this.height
	}
	
	public def getDimensions() {
		return [this.width, this.height]
	}
	
	@Override
	public def getTitle() {
		return this.title	
	}
	
	@Override
	public def getVersion() {
		return this.version
	}
	
	@Override
	public def getCopyright() {
		return this.copyright
	}
	
	
	// *****************************************************************************************************************************************
	// Update handlers
	// *****************************************************************************************************************************************
	@Override
	public def doneUpdating() {
		return !this.updating
	}

	@Override
	public def update(delta_time) {
		this.updating = true
		this.running = true
		this.instance = Thread.start {
			this.AI()
			this.updating = false
		}
		this.running = false
	}
		
}
