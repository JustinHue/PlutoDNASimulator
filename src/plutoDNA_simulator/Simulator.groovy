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
	def menu
	
	def instance
	def running
	def currentScene
	
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
		def windowActualSize = this.window.getSize()
		println this.window.getSize()
		this.menu = new SimulatorMenu((int)windowActualSize.getWidth(), (int)windowActualSize.getHeight())
		this.manipulator = new SimulatorManipulator(this.world, this.window)
		
		this.running = true
		this.currentScene = SceneEnum.MENU_SCENE
	}
	
	public start() {
		
		Trace.setLogLevel(Trace.LOG_LEVEL.ERROR)
		Trace.error("Simulator","LOG START")
		Trace.error("Simulator","LOG END")
		
		this.instance = Thread.start {
			
			def delta_time = this.FPS / this.SECOND

			this.window.start()
		    this.menu.update(delta_time)
			
			def start = System.nanoTime();
			def end = 0;
			
			while (this.running) {
				if (end - start >= this.SECOND / this.FPS * this.NANOSECOND) {
					switch (this.currentScene) {
						case SceneEnum.MENU_SCENE:
							if (this.menu.doneUpdating()) {
								// Render Menu
								def menuBuffer = DrawFactory.renderMenu(this.menu, this.WINDOW_WIDTH, this.WINDOW_HEIGHT)
								this.window.drawToBuffer(menuBuffer)
								// Update Menu
								this.menu.keysDown(this.window.getKeysDown())
								this.menu.keysUp(this.window.getKeysUp())
								this.menu.keysPressed(this.window.getKeysPressed())
								this.menu.mousePressed(this.window.getMousePressed())
								this.menu.mouseReleased(this.window.getMouseReleased())
								this.menu.mouseDown(this.window.getMouseDown())
								this.menu.mouseUp(this.window.getMouseUp())
								this.menu.update(delta_time)
							}
						break
						case SceneEnum.SIMULATOR_SCENE:

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
							}
						break
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
