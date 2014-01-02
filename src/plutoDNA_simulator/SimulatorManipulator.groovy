package plutoDNA_simulator

import java.awt.event.KeyEvent;

class SimulatorManipulator {

	private def final SCROLL_SPEED = 0.5
	private def final SHIFT_SCROLL_SPEED = 2
	
	private def world
	private def window
	
	private def scrollx, scrolly
	
	public SimulatorManipulator(world, window) {
		this.scrollx = 0
		this.scrolly = 0
		this.world = world
		this.window = window
	}
	
	def getScrollX() {
		return this.scrollx	
	}
	
	def getScrollY() {
		return this.scrolly
	}
	
	def getScrollValues() {
		return [this.scrollx, this.scrolly]
	}
	
	def keyboardInput(keys) {
		if (keys[KeyEvent.VK_UP]) {
			this.scrolly -= keys[KeyEvent.VK_SHIFT] ? this.SHIFT_SCROLL_SPEED : this.SCROLL_SPEED
		} 
		if (keys[KeyEvent.VK_LEFT]) {
			this.scrollx -= keys[KeyEvent.VK_SHIFT] ? this.SHIFT_SCROLL_SPEED : this.SCROLL_SPEED
		}
		if (keys[KeyEvent.VK_DOWN]) {
			this.scrolly += keys[KeyEvent.VK_SHIFT] ? this.SHIFT_SCROLL_SPEED : this.SCROLL_SPEED
		}
		if (keys[KeyEvent.VK_RIGHT]) {
			this.scrollx += keys[KeyEvent.VK_SHIFT] ? this.SHIFT_SCROLL_SPEED : this.SCROLL_SPEED
		}
		// Shift scrolling
		
		if (this.scrollx < 0) {
			this.scrollx = 0
			println 'Left Boundary Reached'
		}
		if (this.scrolly < 0) {
			this.scrolly = 0
			println 'Top Boundary Reached'
		}
		if (this.scrollx > this.world.getWidthTI() - this.window.getWidthTI() - 1) {
			this.scrollx = this.world.getWidthTI() - this.window.getWidthTI() - 1
			println 'Bottom Boundary Reached'
		}
		if (this.scrolly > this.world.getHeightTI() - this.window.getHeightTI() - 1) {
			this.scrolly = this.world.getHeightTI() - this.window.getHeightTI() - 1
			println 'Right Boundary Reached'
		}

	}
	

}
