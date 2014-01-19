package plutoDNA_simulator

import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ActionEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener
import java.awt.image.BufferedImage
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JComponent
import javax.swing.KeyStroke


class SimulatorWindow extends JFrame implements KeyListener, MouseListener {
	
	def surface
	def buffer
	
	def keys
	def pressedKeys
	def mousePresses
	def mouseReleased
	def mouseButtonsDown
	def mouseButtonsUp
	
	public SimulatorWindow() {
				
		this.keys = new boolean[Assets.globalConfig.window.max_keys]
		this.pressedKeys = new boolean[Assets.globalConfig.window.max_keys]
		this.mousePresses = new boolean[Assets.globalConfig.window.max_mouse_buttons]
		this.mouseReleased = new boolean[Assets.globalConfig.window.max_mouse_buttons]
		this.mouseButtonsDown = new boolean[Assets.globalConfig.window.max_mouse_buttons]
		this.mouseButtonsUp = new boolean[Assets.globalConfig.window.max_mouse_buttons]
		
		this.addKeyListener(this)
		this.addMouseListener(this)
		
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
	
	// Depreciated, used getKeysDown()
	@Deprecated
	public getKeys() {
		return this.keys
	}
	
	public def getKeysDown() {
		return this.keys
	}
	
	public def getKeysUp() {
		def upKeys = new boolean[Assets.globalConfig.window.max_keys]
		upKeys.eachWithIndex {it, i ->
			upKeys[i] = !it
		}
		return upKeys
	}
	
	public def getKeysPressed() {
		def tempPressedKeys = this.pressedKeys.clone()
		this.pressedKeys.eachWithIndex {it, i ->
			if (this.pressedKeys[i]) {
				this.pressedKeys[i] = false
			}
		}
		return tempPressedKeys
	}
	
	public def getMousePressed() {
		def tempMousePresses = this.mousePresses.clone()
		this.mousePresses.eachWithIndex {it, i ->
			if (this.mousePresses[i]) {
				this.mousePresses[i] = false
			}
		}
		return tempMousePresses
	}
	
	public def getMouseReleased() {
		def tempMouseReleases = this.mouseReleased.clone()
		for (int i = 0; i < this.mouseReleased.size(); ++i) {
			if (this.mouseReleased[i]) {
				this.mouseReleased[i] = false
			}
		}
		return tempMouseReleases
	}
	
	public def getMouseDown() {
		return this.mouseButtonsDown
	}
	
	public def getMouseUp() {
		return this.mouseButtonsUp
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
		if (!this.keys[e.getKeyCode()]) {
			this.pressedKeys[e.getKeyCode()] = true
		} else {
		
			this.pressedKeys[e.getKeyCode()] = false
		}
		this.keys[e.getKeyCode()] = true
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	// ==========================================================================================================
	// Mouse Event Listeners
	// ==========================================================================================================
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.mousePresses[e.getButton()] = true
		this.mouseButtonsDown[e.getButton()] = true
		this.mouseReleased[e.getButton()] = false
		this.mouseButtonsUp[e.getButton()] = false
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.mouseReleased[e.getButton()] = true
		this.mouseButtonsDown[e.getButton()] = false
		this.mouseButtonsUp[e.getButton()] = true
	}
	

}
