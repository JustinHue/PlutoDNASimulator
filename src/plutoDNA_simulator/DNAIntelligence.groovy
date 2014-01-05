package plutoDNA_simulator

class DNAIntelligence {

	def static AI_functions = [
		"choose_direction": {world_ref, entity_ref ->
			def physicalComponent = entity_ref.getPhysical()
			def random = new Random()
			physicalComponent["direction"] = random.nextInt(4)
		},
		"move": {world_ref, entity_ref ->
			def physicalComponent = entity_ref.getPhysical()
	
			switch (physicalComponent["direction"]) {
				case DirectionEnum.UP.value():
					physicalComponent["direction"] = DirectionEnum.UP
					break
				case DirectionEnum.DOWN.value():
					physicalComponent["direction"] = DirectionEnum.DOWN
					break
				case DirectionEnum.LEFT.value():
					physicalComponent["direction"] = DirectionEnum.LEFT
					break
				case DirectionEnum.RIGHT.value():
					physicalComponent["direction"] = DirectionEnum.RIGHT
					break
				default:

					break
			}
		},
		"choose_speed" : {world_ref, entity_ref ->
			def physicalComponent = entity_ref.getPhysical()
			def random = new Random()
			physicalComponent["speed"] = random.nextInt((physicalComponent["maxspeed"]-100) + 100)
		}
	]



}
