package plutoDNA_simulator

enum DirectionEnum {
	UP(0), LEFT(1), DOWN(2), RIGHT(3), NONE(4)
	DirectionEnum(value) { this.value = value }
	private final def value
	def value() { return value }
}
