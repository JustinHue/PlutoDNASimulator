package plutoDNA_simulator

class Entity implements IEntity {
	
	private def DNA
	
	private def capabilities
	private def AIComponent
	
	private def instance
	private def updating
	private def running
	
	private def world
	
	private def static INITIAL_CAPABILITIES = [
		"coordinate" : [0, 0],
		]
	
	
	public Entity(world) {
		this.DNA = ""	
		this.running = false
		this.updating = false
		this.world = world
		this.capabilities = Entity.INITIAL_CAPABILITIES
		this.AIComponent = ""
	}
	
	public Entity(DNA, world) {
		this.DNA = DNA
		this.running = false
		this.updating = false
		this.world = world
		this.capabilities = Entity.INITIAL_CAPABILITIES
		this.AIComponent = ""
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

}
