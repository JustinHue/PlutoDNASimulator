package plutoDNA_simulator

import java.awt.image.BufferedImage
import scene.MenuScene

class Simulator {

	// Simulator Constants grabbed from global.ini
	def FPS 
	def SECOND
	def NANOSECOND
	def WINDOW_WIDTH
	def WINDOW_HEIGHT
	def WORLD_WIDTH 
	def WORLD_HEIGHT
	
	def WINDOW_INWIDTH 
	def WINDOW_INHEIGHT
	
	def window
	def world
	def manipulator
	def menuScene = new SimulatorMenuScene()
	
	def instance
	def running
	def currentScene
	
	public Simulator() {
		// Set constants
		this.FPS = Assets.globalConfig.graphics.fps
		this.SECOND = Assets.globalConfig.time.second
		this.NANOSECOND = Assets.globalConfig.time.nanosecond
		this.WORLD_WIDTH = Assets.globalConfig.world.width
		this.WORLD_HEIGHT = Assets.globalConfig.world.height
		
		this.world = new World(this.WORLD_WIDTH, this.WORLD_HEIGHT)

		this.window = new SimulatorWindow()
		def windowActualSize = this.window.getSize()
				
		//this.menu = new SimulatorMenu((int)windowActualSize.getWidth(), (int)windowActualSize.getHeight())
		this.manipulator = new SimulatorManipulator(this.world, this.window)
		
		this.running = true
		this.currentScene = SceneEnum.MENU_SCENE
		def insize = window.getContentPane().getSize()
		def fsize = window.getSize()
		WINDOW_INWIDTH = (int)insize.getWidth()
		WINDOW_INHEIGHT = (int)insize.getHeight()
		WINDOW_WIDTH = (int)fsize.getWidth()
		WINDOW_HEIGHT = (int)fsize.getHeight()

	}
	
	public start() {
		
		Trace.setLogLevel(Trace.LOG_LEVEL.ERROR)
		Trace.error("Simulator","LOG START")
		Trace.error("Simulator","LOG END")
		
		this.instance = Thread.start {
			
			def delta_time = this.FPS / this.SECOND

			this.window.start()
		    this.menuScene.update(delta_time)
			
			def start = System.nanoTime();
			def end = 0;
			
			while (this.running) {
				if (end - start >= this.SECOND / this.FPS * this.NANOSECOND) {
					if (menuScene.exit) {
						currentScene = SceneEnum.SIMULATOR_SCENE 
					} 
					if (menuScene.quit) {
						stop()
						window.setVisible(false)
						window.dispose()
						System.exit(0)
					}
					switch (this.currentScene) {
						case SceneEnum.MENU_SCENE:
							if (!this.menuScene.updating) {
								// Render Menu
								def menuBuffer = DrawFactory.renderScene(menuScene)
								this.window.drawToBuffer(menuBuffer)
								// Update Menu								
								menuScene.keysDown(this.window.getKeysDown())
								menuScene.keysUp(this.window.getKeysUp())
								menuScene.keysPressed(this.window.getKeysPressed())
								menuScene.mousePressed(this.window.getMousePressed())
								menuScene.mouseReleased(this.window.getMouseReleased())
								menuScene.mouseDown(this.window.getMouseDown())
								menuScene.mouseUp(this.window.getMouseUp())
								menuScene.update(delta_time)
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
