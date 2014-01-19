package plutoDNA_simulator

import java.awt.event.KeyEvent
import java.awt.event.MouseEvent

class MenuScene implements IMenuScene {

	def instance
	
	def running
	def updating
	
	public MenuScene() {

		this.running = false
		this.updating = false
	}
	
	// *****************************************************************************************************************************************
	// AI + Update
	// *****************************************************************************************************************************************
	@Override
	public def doneUpdating() {
		return !this.updating
	}

	@Override
	public def AI() {

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

	// *****************************************************************************************************************************************
	// Key Event Listeners
	// *****************************************************************************************************************************************
	@Override
	public def keysDown(keys) {
		if (keys[KeyEvent.VK_UP]) {

		}
	}

	@Override
	public def keysUp(keys) {
		if (keys[KeyEvent.VK_UP]) {

		}
	}

	@Override
	public def keysPressed(keys) {
		if (keys[KeyEvent.VK_UP]) {

		}
	}

	@Override
	public def mousePressed(buttons) {
		if (buttons[MouseEvent.BUTTON1]) {

		}
	}

	@Override
	public def mouseReleased(buttons) {
		if (buttons[MouseEvent.BUTTON1]) {

		}
	}

	@Override
	public def mouseDown(buttons) {
		if (buttons[MouseEvent.BUTTON1]) {

		}
	}

	@Override
	public def mouseUp(buttons) {
		if (buttons[MouseEvent.BUTTON1]) {

		}
	}

		
}
