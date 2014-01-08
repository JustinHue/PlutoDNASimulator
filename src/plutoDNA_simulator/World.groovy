package plutoDNA_simulator

import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.awt.Color


class World implements IWorld {

	def tiles
	def entities
	
	def instance
	
	def running
	def updating
	def deltaTime
	
	def rect
	
	public World(sizew, sizeh) {
		this.rect = new PhysicsRect(0, 0, sizew, sizeh)
		this.entities = []
		this.entities.add(new Entity(this))
		
		Random rand = new Random()
		this.tiles = new Tile[sizew][sizeh]	

		this.tiles.eachWithIndex {row, rindex ->
			row.eachWithIndex {tile, cindex ->
				this.tiles[rindex][cindex] = new Tile(-1)			
			}
		} 
		
		// ** World Generator **
		def generated = false
		
		// seed tiles with random environment tile 
		// All tiles generated will branch from these seeded tiles
		def seedmax = 10
		def seedcount = 0
		
		while (seedcount != seedmax) {
			def randomtile = rand.nextInt(3)
			def randomx = rand.nextInt(this.getWidthTI() - 1)
			def randomy = rand.nextInt(this.getHeightTI() - 1)
			println randomx + " " + randomy
			this.tiles[randomy][randomx] = new Tile(randomtile)

			// branch from tile
			def doneBranching = false
			def dirtSpawnChance = 0
			//def waterSpawnChance = 70
	
			while (!doneBranching) {
				
				// Check left Adjacent tile
				def nTile = null
				if (randomx-1 >= 0) {
					def spawnChance = rand.nextInt(100)
					if (spawnChance < dirtSpawnChance) {
						nTile = new Tile(Tile.TILE_DIRT)
					} else {
						nTile = new Tile(Tile.TILE_WATER)
					}
					if (this.tiles[randomy-1][randomx].getValue() == -1) {
						this.tiles[randomy][randomx-1] = nTile
					}
				}
				// Check Right Adjacent tile
				if (randomx+1 < this.getWidthTI() - 1) {
					def spawnChance = rand.nextInt(100)
					if (spawnChance < dirtSpawnChance) {
						nTile = new Tile(Tile.TILE_DIRT)
					} else {
						nTile = new Tile(Tile.TILE_WATER)
					}
					if (this.tiles[randomy-1][randomx].getValue() == -1) {
						this.tiles[randomy][randomx+1] = nTile
					}
				}
				// Check Top Adjacent tile
				if (randomy-1 >= 0) {
					def spawnChance = rand.nextInt(100)
					if (spawnChance < dirtSpawnChance) {
						nTile = new Tile(Tile.TILE_DIRT)
					} else {
						nTile = new Tile(Tile.TILE_WATER)
					}
					if (this.tiles[randomy-1][randomx].getValue() == -1) {
						this.tiles[randomy-1][randomx] = nTile
					}
				}
				// Check Bottom Adjacent tile
				if (randomy+1 < this.getHeightTI() - 1) {
					def spawnChance = rand.nextInt(100)
					if (spawnChance < dirtSpawnChance) {
						nTile = new Tile(Tile.TILE_DIRT)
					} else {
						nTile = new Tile(Tile.TILE_WATER)
					}
					if (this.tiles[randomy-1][randomx].getValue() == -1) {
						this.tiles[randomy+1][randomx] = nTile
					}
				}
				// Also stop if no new tiles were made
				if (!nTile) {
					doneBranching = true
				} else {
					// Pick Random Side to branch off of (or stop)
					def randomSide = rand.nextInt(4)
					if (randomSide == 0) {
						randomy -= 1
					} else if (randomSide == 1) {
						randomx -= 1
					} else if (randomSide == 2) {
						randomx += 1
					} else if (randomSide == 3) {
						randomy += 1
					} 
					if (randomy < 0 || randomy > this.getHeightTI() - 1 ||
						randomx < 0 || randomx > this.getHeightTI() - 1) {
						doneBranching = true
					}
				}
			}
			seedcount ++
		}
		
		this.tiles.eachWithIndex {row, rindex ->
			row.eachWithIndex {tile, cindex ->
				if (this.tiles[rindex][cindex].getValue() == -1) this.tiles[rindex][cindex] = new Tile(Tile.TILE_DIRT)
			}
		}
		
		this.running = false
		this.updating = false

	}
	


	@Override
	public void AI() {
		Random rand = new Random()
		for (row in this.tiles) {
			for (tile in row) {
		
			}
		}
		for (entity in this.entities) {
			// Update Entity
			entity.update()
		}
		def allEntitiesUpdated = false
		while (!allEntitiesUpdated) {
			allEntitiesUpdated = true
			for (entity in this.entities) {
				if (!entity.doneUpdating()) {
					allEntitiesUpdated = false
				}
			}
		}
		for (entity in this.entities) {

			def capabilities = entity.getCapabilities()
			def entityCoordinate = capabilities["coordinate"]
			def entitySpeed = capabilities["speed"]
			def entityDirection = capabilities["direction"]
			def entityRect = entity.getRect()
			def worldRect = this.getInRect()

			
			// Move entities if they want to move
			
			switch (entityDirection) {
				case DirectionEnum.UP.value():
					PhysicsHandler.move_point_v(entityCoordinate, -entitySpeed * this.deltaTime)
					break
				case DirectionEnum.DOWN.value():
					PhysicsHandler.move_point_v(entityCoordinate, entitySpeed * this.deltaTime)
					break
				case DirectionEnum.LEFT.value():
					PhysicsHandler.move_point_h(entityCoordinate, -entitySpeed * this.deltaTime)
					break
				case DirectionEnum.RIGHT.value():
					PhysicsHandler.move_point_h(entityCoordinate, entitySpeed * this.deltaTime)
					break
				default:
	
					break
			}
			
			PhysicsHandler.rectangle_boundary_collision(entityRect, worldRect)

		}
		this.updating = false
	}


	@Override
	public def update(delta_time) {
		this.deltaTime = delta_time
		this.updating = true
		this.running = true
		this.instance = Thread.start {
			if (this.updating) this.AI()
		}
		this.running = false
	}


	@Override
	public boolean doneUpdating() {
		return !this.updating
	}


	// Getters
	
	@Override
	public getSize() {
		return [this.tiles.size() * Tile.TILE_SIZE, 
			    this.tiles[0].size() * Tile.TILE_SIZE]
	}

	@Override
	public def getWidth() {
		return this.tiles.size() * Tile.TILE_SIZE
	}

	@Override
	public def getHeight() {
		return this.tiles[0].size() * Tile.TILE_SIZE
	}
	
	@Override
	public def getSizeTI() {
		return [this.tiles.size(), this.tiles[0].size() ]
	}


	@Override
	public def getWidthTI() {
		return this.tiles.size()
	}



	@Override
	public def getHeightTI() {
		return this.tiles[0].size()
	}


	@Override
	public getTile(x, y) {
		return this.tiles[x][y]
	}



	@Override
	public def addEntity(entity) {
		this.entities.add(entity)
	}



	@Override
	public def getEntities() {
		return this.entities
	}


	@Override
	public def removeEntity(entity) {
		this.entities.remove(entity)
	}



	@Override
	public def getInRect() {
		return this.rect
	}




}
