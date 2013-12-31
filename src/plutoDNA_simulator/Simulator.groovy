package plutoDNA_simulator
import groovy.util.logging.*

import java.util.logging.FileHandler

@Log
class Simulator {

	private def window
	private def world
	
	public Simulator() {
		log.addHandler(new FileHandler("logs/simulator.log"))
		
		log.info '[OBJ-Simulator] instantiated'

		this.world = new World(Assets.globalConfig.world.width, Assets.globalConfig.world.height)
		this.window = new SimulatorWindow()
	}
	
	public start() {
		this.world.start()
		this.window.start()
	}
	
	public static void main (String [] args) {
			
			Assets.prepare()
			new Simulator().start()
			
	}
}
