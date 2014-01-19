package plutoDNA_simulator

import java.awt.Font
import java.awt.FontMetrics

class TextLabel implements ITextLabel {

	public static def STANDARD_FONT
	
	private def text
	private def position
	private def font

	
	public TextLabel(text, position, font) {
		this.text = text
		this.position = position
		this.font = font
	}
	
	public TextLabel(text, position) {
		this.text = text
		this.position = position
		// Default Font
		this.font = new Font(Assets.globalConfig.default_text.type,
			Assets.globalConfig.default_text.shape,
			Assets.globalConfig.default_text.font_size)
	}
	
	public TextLabel(text) {
		this.text = text
		// Default Position and Font
		this.position = [0, 0]
		this.font = new Font(Assets.globalConfig.default_text.type, 
			Assets.globalConfig.default_text.shape, 
			Assets.globalConfig.default_text.font_size)
	}

	@Override
	public def getText() {
		return this.text
	}

	@Override
	public def getPosition() {
		return this.position
	}

	@Override
	public def getFont() {
		return this.font
	}

	@Override
	public def getWidth(graphics) {
		FontMetrics metrics = graphics.getFontMetrics(this.font);
		return metrics.stringWidth(this.textWidth)
	}

	@Override
	public def getHeight(graphics) {
		FontMetrics metrics = graphics.getFontMetrics(this.font);
		return metrics.getHeight()
	}

	@Override
	public def getX() {
		return this.position[0]
	}

	@Override
	public def getY() {
		return this.position[1]
	}
	
}
