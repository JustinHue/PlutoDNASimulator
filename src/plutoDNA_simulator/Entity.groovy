package plutoDNA_simulator

class Entity implements IEntity {
	
	def DNA
	
	def capabilities
	def AIComponent
	
	def instance
	def updating
	def running
	
	def world
	

	public Entity(world) {
		this.DNA = ""
		this.running = false
		this.updating = false
		this.world = world
		this.setInitialCapabilities()
		this.AIComponent = ""
	}
	
	public Entity(DNA, world) {
		this.DNA = DNA
		this.running = false
		this.updating = false
		this.world = world
		this.setInitialCapabilities()
		this.AIComponent = ""
	}

	private setInitialCapabilities() {
		this.capabilities = [:]
		this.capabilities["coordinate"] = new PhysicsPoint(0, 0)
		this.capabilities["rect"] = new PhysicsRect(this.capabilities["coordinate"], new PhysicsPoint(Tile.TILE_SIZE, Tile.TILE_SIZE))
	}
	
	@Override
	public void AI() {
		def world_ref = this.world
		def entity_ref = this
		
		def func1 = DNAIntelligence.AI_functions["random_generator"]
		def func2 = DNAIntelligence.AI_functions["move"]
		def direction = func1(world_ref, entity_ref, [4])
		def speed = func1(world_ref, entity_ref, [300])
		def move = func2(world_ref, entity_ref, [direction, speed])
		
		this.updating = false
	}

	@Override
	public void update(delta_time) {
		this.updating = true
		this.instance = Thread.start {	
			this.running = true
			if (this.updating) this.AI()	
		}
		this.running = false
	}

	@Override
	public boolean doneUpdating() {
		return !this.updating
	}

	@Override
	public def getDNA() {
		return this.DNA
	}

	@Override
	public def getCapabilities() {
		return this.capabilities
	}

	@Override
	public def getRect() {
		return capabilities["rect"]
	}

	@Override
	public def getPoint() {
		return capabilities["coordinate"]
	}

}
