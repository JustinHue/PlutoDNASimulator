package plutoDNA_simulator

import java.awt.image.BufferedImage

class Simulator {

	// Simulator Constants grabbed from global.ini
	def FPS 
	def SECOND
	def NANOSECOND
	def WINDOW_WIDTH
	def WINDOW_HEIGHT
	def WORLD_WIDTH 
	def WORLD_HEIGHT
	
	def window
	def world
	def manipulator
	
	def instance
	def running

	public Simulator() {
		// Set constants
		this.FPS = Assets.globalConfig.graphics.fps
		this.SECOND = Assets.globalConfig.time.second
		this.NANOSECOND = Assets.globalConfig.time.nanosecond
		this.WINDOW_WIDTH = Assets.globalConfig.window.width
		this.WINDOW_HEIGHT = Assets.globalConfig.window.height
		this.WORLD_WIDTH = Assets.globalConfig.world.width
		this.WORLD_HEIGHT = Assets.globalConfig.world.height
		
		this.world = new World(this.WORLD_WIDTH, this.WORLD_HEIGHT)

		this.window = new SimulatorWindow()
		this.manipulator = new SimulatorManipulator(this.world, this.window)
		
		this.running = true
	}
	
	public start() {
		
		this.instance = Thread.start {
			
			def delta_time = this.FPS / this.SECOND

			// Start window and do Initial World Update
			this.window.start()
		    this.world.update(delta_time)
			
			def start = System.nanoTime();
			def end = 0;
			
			while (this.running) {
				if (end - start >= this.SECOND / this.FPS * this.NANOSECOND) {
					if (this.world.doneUpdating()) {
						
						// Render World Map
						def worldBuffer = DrawFactory.renderWorld(world, this.WINDOW_WIDTH, this.WINDOW_HEIGHT, 
							this.manipulator.getScrollValues())
						
						this.window.drawToBuffer(worldBuffer)
						
						// Update Manipulator
						this.manipulator.keyboardInput(this.window.getKeys())
						this.manipulator.update()
						
						// Update World
						this.world.update(delta_time)
						
						start = System.nanoTime()
					}
				} else {
					sleep(1)
				}
				end = System.nanoTime();
			}
			
		}
		
	}
	
	public stop() {
		this.running = false
	}

}
