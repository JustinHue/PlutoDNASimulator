package plutoDNA_simulator

class DNAIntelligence {

	def static AI_functions = [
		"random_generator": {world_ref, entity_ref, args_list -> 
			return (new Random()).nextInt((int)args_list[0])
		},
		"move": {world_ref, entity_ref, args_list ->
			def capabilities = entity_ref.getCapabilities()
			capabilities["direction"] = (int)args_list[0]
			capabilities["speed"] = (int)args_list[1]
			return [(int) args_list[0], (int) args_list[1]]
		}
	]



}
