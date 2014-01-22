package scene

abstract class Scene implements IScene {

	def instance = null
	def running = false
	def updating = false
	
	def components = []
	
	def width = 800
	def height = 600
	
	def exit = false
	def quit = false
	
	@Override
	public def addComponent(component) {
		this.components.add(component) 
	}
	@Override
	public def removeComponent(component) {
		this.components.remove(component)
	}
	
	@Override
	public def update(deltaTime) {
		this.updating = true
		this.running = true
		this.instance = Thread.start {
			this.AI()
			this.updating = false
		}
		this.running = false
	}
	
	abstract def AI()
	
}
