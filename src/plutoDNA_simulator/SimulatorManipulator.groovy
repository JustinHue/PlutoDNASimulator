package plutoDNA_simulator

import java.awt.event.KeyEvent;

class SimulatorManipulator implements ISimulatorManipulator {

	def static SCROLL_SPEED = Assets.globalConfig.manipulator.scroll_speed
	def static SHIFT_SCROLL_SPEED = Assets.globalConfig.manipulator.shift_scroll_speed
	
	def world
	def window
	
	def scroll_point
	
	
	public SimulatorManipulator(world, window) {
		// Set Regular field variables
		this.scroll_point = new PhysicsPoint(0, 0)
		this.world = world
		this.window = window
	}
	
	@Override
	def getScrollX() {
		return this.scroll_point.getX()
	}
	
	@Override
	def getScrollY() {
		return this.scroll_point.getY()
	}
	
	@Override
	def getScrollValues() {
		return [this.scroll_point.getX(), this.scroll_point.getY()]
	}
	
	@Override
	def getScrollTIValues() {
		return [this.scroll_point.getX() / Tile.TILE_SIZE, this.scroll_point.getY() / Tile.TILE_SIZE]
	}
	
	@Override
	def keyboardInput(keys) {
		// Check if manipulator is scrolling the map
		if (keys[KeyEvent.VK_UP]) {
			PhysicsHandler.move_point_v(this.scroll_point, -(keys[KeyEvent.VK_SHIFT] ? 
				SimulatorManipulator.SHIFT_SCROLL_SPEED : SimulatorManipulator.SCROLL_SPEED))
		} 
		if (keys[KeyEvent.VK_LEFT]) {
			PhysicsHandler.move_point_h(this.scroll_point, -(keys[KeyEvent.VK_SHIFT] ? 
				SimulatorManipulator.SHIFT_SCROLL_SPEED : SimulatorManipulator.SCROLL_SPEED))
		}
		if (keys[KeyEvent.VK_DOWN]) {
			PhysicsHandler.move_point_v(this.scroll_point, (keys[KeyEvent.VK_SHIFT] ? 
				SimulatorManipulator.SHIFT_SCROLL_SPEED : SimulatorManipulator.SCROLL_SPEED))
		}
		if (keys[KeyEvent.VK_RIGHT]) {
			PhysicsHandler.move_point_h(this.scroll_point, (keys[KeyEvent.VK_SHIFT] ? 
				SimulatorManipulator.SHIFT_SCROLL_SPEED : SimulatorManipulator.SCROLL_SPEED))
		}
	}

	@Override
	public def getScrollPoint() {
		return this.scroll_point
	}

	@Override
	public def update() {
		// Boundary Check
		PhysicsHandler.point_boundary_collision(this.scroll_point,
			this.world.getWidth() - this.window.getWidth() - 1, this.world.getHeight() - this.window.getHeight() - 1)

	}
	

}
