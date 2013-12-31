package plutoDNA_simulator


class Simulator {

	private def window
	private def world
	
	public Simulator() {
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
