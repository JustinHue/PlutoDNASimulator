package plutoDNA_simulator

class Entity {
	
	private def DNA
	private def instance
	private def running
	private def updating
	
	public Entity() {
		this.DNA = ""	
		this.running = true
	}
	
	public Entity(DNA) {
		this.DNA = DNA
	}
	
	private AI() {
		// Reads DNA Sequence, start to finish.

		
		
		this.updating = false
	}
	
	public start() {
		
		this.instance = Thread.start {
			while (this.running) {
				
				if (this.updating) this.AI()
				
			} 
		}
		
	}
	
	public stop() {
		this.running = false	
	}
	
	public doUpdate() {
		this.updating = true
	}
	
}
