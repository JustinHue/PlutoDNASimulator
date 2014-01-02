package plutoDNA_simulator

import java.awt.image.BufferedImage

class Simulator {

	private def window
	private def world
	private def manipulator
	
	private def instance
	private def running
	
	private def scrollx, scrolly
	

	public Simulator() {
		this.world = new World(Assets.globalConfig.world.width, Assets.globalConfig.world.height)
		this.window = new SimulatorWindow()
		this.manipulator = new SimulatorManipulator(this.world, this.window)
		
		this.running = true
		this.scrollx = 0
		this.scrolly = 0
	}
	
	public start() {
		
		this.instance = Thread.start {
			
			this.window.start()
		    this.world.update()
			
			def start = System.nanoTime();
			def end = 0;
			
			while (this.running) {
				if (end - start >= 1000 / Assets.globalConfig.graphics.fps * 1000000) {
					//println 'Tick Elapse Time Per Frame: ' + ((end - start) / 1000000) + ' ms'
					if (this.world.doneUpdating()) {
						// Update Manipulator
						this.manipulator.keyboardInput(this.window.getKeys())
						
						def worldBuffer = DrawFactory.renderWorld(world, Assets.globalConfig.window.width, Assets.globalConfig.window.height, 
							this.manipulator.getScrollValues())
						this.window.drawToBuffer(worldBuffer)
						this.world.update()
						start = System.nanoTime();
					}
				} else {
					sleep(1)
				}
				end = System.nanoTime();
			}
			
		}
		
	}
	
	public stop() {
		this.running = true
	}
	
	public static void main (String [] args) {
			
			Assets.prepare()
			def simulator = new Simulator().start()
	
	}
}
