package plutoDNA_simulator

class DNAIntelligence {

	def static AI_functions = [
		"choose_direction": {world_ref, entity_ref, args_list ->
			def random = new Random()
			args_list["direction"] = random.nextInt(4)
		},
		"move": {world_ref, entity_ref, args_list ->
			def physicalComponent = entity_ref.getPhysical()
			def entityCoordinate = physicalComponent.get("coordinate")
			def deltaTime = entity_ref.getDeltaTime()
			
			switch (args_list["direction"]) {
				case DirectionEnum.UP.value():
					entityCoordinate[1] -= args_list["speed"] * deltaTime
					physicalComponent["direction"] = DirectionEnum.UP
					break
				case DirectionEnum.DOWN.value():
					entityCoordinate[1] += args_list["speed"] * deltaTime
					physicalComponent["direction"] = DirectionEnum.DOWN
					break
				case DirectionEnum.LEFT.value():
					entityCoordinate[0] -= args_list["speed"] * deltaTime
					physicalComponent["direction"] = DirectionEnum.LEFT
					break
				case DirectionEnum.RIGHT.value():
					entityCoordinate[0] += args_list["speed"] * deltaTime
					physicalComponent["direction"] = DirectionEnum.RIGHT
					break
				default:

					break
			}
			physicalComponent["coordinate"] = entityCoordinate
		},
		"choose_speed" : {world_ref, entity_ref, args_list ->
			def random = new Random()
			def physicalComponent = entity_ref.getPhysical()
			args_list["speed"] = random.nextInt((physicalComponent["maxspeed"]-100) + 100)
		}
	]



}
