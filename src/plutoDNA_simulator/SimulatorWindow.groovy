package plutoDNA_simulator

import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ActionEvent
import java.awt.event.KeyListener
import java.awt.image.BufferedImage
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JComponent
import javax.swing.KeyStroke


class SimulatorWindow extends JFrame implements KeyListener {
	
	def surface
	def buffer
	
	def keys

	public SimulatorWindow() {
				
		this.keys = new boolean[Assets.globalConfig.window.max_keys]
		
		this.addKeyListener(this)
		
		// Add surface to window
		this.surface = new JPanel()
		this.add(this.surface)
		
		// Set window properties
		this.setTitle(Assets.globalConfig.window.title)
		this.setSize(Assets.globalConfig.window.width, Assets.globalConfig.window.height)
		if (Assets.globalConfig.window.options.setDefaultCloseOperation) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		}
		if (Assets.globalConfig.window.options.setLocationRelativeTo) {
			setLocationRelativeTo(null)
		}		

		this.buffer = new BufferedImage(this.width , this.height,
				BufferedImage.TYPE_INT_RGB)
	}
	
	public start() {
		this.setVisible(Assets.globalConfig.window.options.setVisible)
	}
	
	public drawToBuffer(buffer) {
		def g2d = (Graphics2D) this.surface.getGraphics()
		def surfaceDimension = this.surface.getSize()
		
		g2d.drawImage(buffer, 0, 0, this.width, this.height, 
						      0, 0, this.width, this.height, null)
	}
	
	public getKeys() {
		return this.keys
	}
	
	public def getWidthTI() {
		return this.width / Assets.globalConfig.world.tilesize
	}
	
	public def getHeightTI() {
		return this.height / Assets.globalConfig.world.tilesize
	}
	
	
	// ==========================================================================================================
	// Key Event Listeners
	// ==========================================================================================================
	@Override
	public void keyPressed(KeyEvent e) {
		this.keys[e.getKeyCode()] = true
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	


}
