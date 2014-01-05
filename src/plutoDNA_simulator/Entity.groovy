package plutoDNA_simulator

class Entity implements IEntity {
	
	private def DNA
	private def physical
	
	private def instance
	private def updating
	private def running
	
	private def world
	
	private def static initial_physical = [
		"coordinate" : [0, 0],
		"maxspeed" : 300 // this will be defined or overwritten by dna physical
		]
	
	
	public Entity(world) {
		this.DNA = ""	
		this.running = false
		this.updating = false
		this.world = world
		this.physical = Entity.initial_physical
	}
	
	public Entity(DNA, world) {
		this.DNA = DNA
		this.running = false
		this.updating = false
		this.world = world
		this.physical = Entity.initial_physical
	}

	@Override
	public void AI() {
		def world_ref = this.world
		def entity_ref = this
		
		def func1 = DNAIntelligence.AI_functions["choose_direction"]
		def func2 = DNAIntelligence.AI_functions["choose_speed"]
		def func3 = DNAIntelligence.AI_functions["move"]
		func1(world_ref, entity_ref)
		func2(world_ref, entity_ref)
		func3(world_ref, entity_ref)
		
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
	public def getPhysical() {
		return this.physical
	}

}
