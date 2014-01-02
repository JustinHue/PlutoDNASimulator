package plutoDNA_simulator

import java.awt.Point

class Entity implements IEntity {
	
	private def DNA
	private def instance
	private def updating
	private def running
	
	private def coordinate
	
	public Entity() {
		this.DNA = ""	
		this.running = false
		this.updating = false
		this.coordinate = new Point()
	}
	
	public Entity(DNA) {
		this.DNA = DNA
	}

	@Override
	public void AI() {
		this.updating = false
	}

	@Override
	public void update() {
		this.updating = true
		this.instance = Thread.start {	
			this.running = true
			if (this.update) this.AI()	
		}
		this.running = false
	}

	@Override
	public boolean doneUpdating() {
		return !this.updating
	}

	
}
