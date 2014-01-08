package plutoDNA_simulator

class PhysicsHandler {

	def static rectangle_boundary_collision(inrect, outrect) {
		def collision_direction = DirectionEnum.NONE
		if (inrect.getLeft() < outrect.getLeft()) {
			inrect.setLeft(outrect.getLeft())
			collision_direction = DirectionEnum.LEFT
		}
		if (inrect.getTop() < outrect.getTop()) {
			inrect.setTop(outrect.getTop())
			collision_direction = DirectionEnum.UP
		}
		if (inrect.getRight() > outrect.getRight()) {
			inrect.setRight(outrect.getRight())
			collision_direction = DirectionEnum.RIGHT
		}
		if (inrect.getBottom() > outrect.getBottom()) {
			inrect.setBottom(outrect.getBottom())
			collision_direction = DirectionEnum.DOWN
		}
		return collision_direction
	}
	
	def static point_boundary_collision(point, rect) {
		def collision_direction = DirectionEnum.NONE
		if (point.getX() < rect.getLeft()) {
			point.changeX(rect.getLeft())
			collision_direction = DirectionEnum.LEFT
		}
		if (point.getY() < rect.getTop()) {
			point.changeY(rect.getTop())
			collision_direction = DirectionEnum.UP
		}
		if (point.getX() > rect.getRight()) {
			point.changeX(rect.getRight())
			collision_direction = DirectionEnum.RIGHT
		}
		if (point.getY() > rect.getBottom()) {
			point.changeY(rect.getBottom())
			collision_direction = DirectionEnum.DOWN
		}
		return collision_direction
	}
	
										
	def static point_boundary_collision(point, out_width, out_height) {
		def collision_direction = DirectionEnum.NONE
		if (point.getX() < 0) {
			point.changeX(0)
			collision_direction = DirectionEnum.LEFT
		}
		if (point.getY() < 0) {
			point.changeY(0)
			collision_direction = DirectionEnum.UP
		}
		if (point.getX() > out_width) {
			point.changeX(out_width)
			collision_direction = DirectionEnum.RIGHT
		}
		if (point.getY() > out_height) {
			point.changeY(out_height)
			collision_direction = DirectionEnum.DOWN
		}
		return collision_direction
	}
	
	// Move Handlers
	def static move_point(point, horizontal_distance, vertical_distance) {
		point.changePosition(point.getX() + horizontal_distance,
							 point.getY() + vertical_distance)
	}
	
	def static move_point_h(point, horizontal_distance) {
		point.changeX(point.getX() + horizontal_distance)
	}
	
	def static move_point_v(point, vertical_distance) {
		point.changeY(point.getY() + vertical_distance)
	}
	
}
