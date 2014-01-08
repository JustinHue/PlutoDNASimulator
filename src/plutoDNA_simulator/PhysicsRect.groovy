package plutoDNA_simulator

class PhysicsRect implements IPhysicsRect {
	
	def p1, p2
	
	public PhysicsRect(x, y, x2, y2) {	
		this.p1 = new PhysicsPoint(x, y)
		this.p2 = new PhysicsPoint(x2, y2)
	}
	
	public PhysicsRect(PhysicsPoint point, width, height) {
		this.p1 = point
		this.p2 = new PhysicsPoint(this.p1.getX(), this.p1.getY(),
									this.p1.getX() + width, this.p1.getY() + height)
	}
	
	public PhysicsRect(PhysicsPoint p1, PhysicsPoint p2) {
		this.p1 = p1
		this.p2 = p2
	}
	
	
	@Override
	public def getWidth() {
		return this.p2.getX() - this.p1.getX()
	}
	
	@Override
	public def getHeight() {
		return this.p2.getY() - this.p1.getY()
	}
	
	@Override
	public def getSize() {
		return [this.p2.getX() - this.p1.getX(), this.p2.getY() - this.p1.getY()]
	}

	@Override
	public def getLeft() {
		return this.p1.getX()
	}

	@Override
	public def getTop() {
		return this.p1.getY()
	}

	@Override
	public def getRight() {
		return this.p2.getX()
	}

	@Override
	public def getBottom() {
		return this.p2.getY()
	}

	@Override
	public def setLeft(left) {
		this.p1.changeX(left)
	}

	@Override
	public def setTop(top) {
		this.p1.changeY(top)
	}

	@Override
	public def setRight(right) {
		this.p2.changeX(right)
	}

	@Override
	public def setBottom(bottom) {
		this.p2.changeY(bottom)
	}

	

	
}
