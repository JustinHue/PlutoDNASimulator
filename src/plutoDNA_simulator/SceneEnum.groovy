package plutoDNA_simulator

enum SceneEnum {
	MENU_SCENE(0), SIMULATOR_SCENE(1), CREDIT_SCENE(2)
	SceneEnum(value) { this.value = value }
	private final def value
	def value() { return value }
}
