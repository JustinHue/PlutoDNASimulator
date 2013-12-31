package plutoDNA_simulator

import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class SimulatorSurface extends JPanel implements Runnable {
	
	private def worldToDraw
	private def instance
	private def isRunning
	private def interval
	
	public SimulatorSurface(worldToDraw) {
		super()
		this.worldToDraw = worldToDraw		
		this.instance = new Thread(this)
		this.isRunning = true
		this.interval = 1000 / Assets.globalConfig.graphics.fps
	}
	
	private void render() {
		
		Graphics2D g2d = (Graphics2D) this.getGraphics()
		

	}
		


	@Override
	public void run() {


		def start = System.nanoTime();
		def end = 0;
		
		while (this.isRunning) {
			
			this.render()			
			end = System.nanoTime();
			if (end - start )
		}
		
	}
	
	public start() {
		this.instance.start()
	}
}
