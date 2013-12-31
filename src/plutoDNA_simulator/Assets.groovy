package plutoDNA_simulator

class Assets {

	public static globalConfig
	
	public static prepare() {
		this.globalConfig = new ConfigSlurper().parse(new File('global.ini').toURL())
	}
	
}
