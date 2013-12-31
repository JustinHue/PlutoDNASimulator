package plutoDNA_simulator

import java.awt.image.BufferedImage

class Simulator {

	private def window
	private def world
	
	private def instance
	private def running
	
	public Simulator() {
		this.world = new World(Assets.globalConfig.world.width, Assets.globalConfig.world.height)
		this.window = new SimulatorWindow()
		
		this.running = true
	}
	
	public start() {
		
		this.instance = Thread.start {
			
			this.window.start()
		    this.world.start()
			
			def start = System.nanoTime();
			def end = 0;
			
			while (this.running) {
				if (end - start >= 1000 / Assets.globalConfig.graphics.fps * 1000000) {
					println 'Tick Elapse Time Per Frame: ' + ((end - start) / 1000000) + ' ms'
					if (this.world.doneUpdating()) {
						def worldBuffer = this.world.render(800, 600)
						this.window.drawBuffer(worldBuffer)
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
