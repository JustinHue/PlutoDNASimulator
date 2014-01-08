package plutoDNA_simulator

class PhysicsPoint implements IPhysicsPoint {

	def x, y
	
	public PhysicsPoint(x, y) {
		this.x = x
		this.y = y
	}
	
	public PhysicsPoint() {
		this.x = 0
		this.y = 0
	}

	@Override
	public def changeX(x) {
		this.x = x
	}

	@Override
	public def changeY(y) {
		this.y = y
	}

	@Override
	public def changePosition(x, y) {
		this.x = x
		this.y = y
	}
	
}
