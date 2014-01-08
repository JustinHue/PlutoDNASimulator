package plutoDNA_simulator

class Main {

	public static void main (String [] args) {
		
		Assets.prepare()
		def simulator = new Simulator().start()

	}
	
}
